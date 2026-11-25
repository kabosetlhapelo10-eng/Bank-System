package model;

import java.time.LocalDate;

public abstract class Account {
   private String accountNumber;
   private double balance;
   private String branch;
   private LocalDate dateOpened;

   public Account(String accountNumber, double balance, String branch, LocalDate dateOpened) {
      this.accountNumber = accountNumber;
      this.balance = balance;
      this.branch = branch;
      this.dateOpened = dateOpened;
   }

   public void deposit(double amount) {
      if (amount <= (double)0.0F) {
         throw new IllegalArgumentException("Deposit amount must be greater than zero.");
      } else {
         this.balance += amount;
      }
   }

   public void withdraw(double amount) {
      if (amount <= (double)0.0F) {
         throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
      } else if (amount > this.balance) {
         throw new IllegalArgumentException("Insufficient funds.");
      } else {
         this.balance -= amount;
      }
   }

   public double getBalance() {
      return this.balance;
   }

   public String getAccountNumber() {
      return this.accountNumber;
   }

   public String getBranch() {
      return this.branch;
   }

   public LocalDate getDateOpened() {
      return this.dateOpened;
   }

   protected void setBalance(double balance) {
      this.balance = balance;
   }
}
