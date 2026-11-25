package model;

import java.time.LocalDate;

public class InvestmentAccount extends Account implements InterestBearing {
   private static final double MINIMUM_OPENING = (double)500.0F;
   private static final double INTEREST_RATE = 0.05;

   public InvestmentAccount(String accountNumber, double balance, String branch, LocalDate dateOpened) {
      super(accountNumber, balance, branch, dateOpened);
      if (balance < (double)500.0F) {
         throw new IllegalArgumentException("Investment model.Account requires a minimum opening balance of BWP500.00.");
      }
   }

   public void deposit(double amount) {
      super.deposit(amount);
   }

   public void withdraw(double amount) {
      super.withdraw(amount);
   }

   public double calculateMonthlyInterest() {
      return this.getBalance() * 0.05;
   }

   public void payInterest() {
      double interest = this.calculateMonthlyInterest();
      this.setBalance(this.getBalance() + interest);
   }
}
