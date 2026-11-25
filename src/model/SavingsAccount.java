package model;

import java.time.LocalDate;

public class SavingsAccount extends Account implements InterestBearing {
   private static final double INTEREST_RATE = 5.0E-4;

   public SavingsAccount(String accountNumber, double balance, String branch, LocalDate dateOpened) {
      super(accountNumber, balance, branch, dateOpened);
   }

   public void deposit(double amount) {
      super.deposit(amount);
   }

   public void withdraw(double amount) {
      throw new UnsupportedOperationException("Savings model.Account does not allow withdrawals.");
   }

   public double calculateMonthlyInterest() {
      return this.getBalance() * 5.0E-4;
   }

   public void payInterest() {
      double interest = this.calculateMonthlyInterest();
      this.setBalance(this.getBalance() + interest);
   }
}
