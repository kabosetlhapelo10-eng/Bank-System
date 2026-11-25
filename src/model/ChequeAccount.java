package model;

import java.time.LocalDate;

public class ChequeAccount extends Account {
   private String employerName;
   private String employerAddress;

   public ChequeAccount(String accountNumber, double balance, String branch, LocalDate dateOpened, String employerName, String employerAddress) {
      super(accountNumber, balance, branch, dateOpened);
      if (employerName != null && !employerName.trim().isEmpty()) {
         if (employerAddress != null && !employerAddress.trim().isEmpty()) {
            this.employerName = employerName;
            this.employerAddress = employerAddress;
         } else {
            throw new IllegalArgumentException("Employer address is required.");
         }
      } else {
         throw new IllegalArgumentException("Employer name is required.");
      }
   }

   public void deposit(double amount) {
      super.deposit(amount);
   }

   public void withdraw(double amount) {
      super.withdraw(amount);
   }

   public String getEmployerName() {
      return this.employerName;
   }

   public String getEmployerAddress() {
      return this.employerAddress;
   }
}
