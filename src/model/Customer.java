package model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
   private String customerId;
   private String firstName;
   private String surname;
   private String address;
   private String password;
   private List<Account> accounts;

   public Customer(String customerId, String firstName, String surname, String address, String password) {
      this.customerId = customerId;
      this.firstName = firstName;
      this.surname = surname;
      this.address = address;
      this.password = password;
      this.accounts = new ArrayList();
   }

   public void register() {
      System.out.println("model.Customer " + this.firstName + " " + this.surname + " registered successfully.");
   }

   public boolean login(String password) {
      return this.password.equals(password);
   }

   public void addAccount(Account account) {
      if (account == null) {
         throw new IllegalArgumentException("model.Account cannot be null.");
      } else {
         this.accounts.add(account);
      }
   }

   public List<Account> getAccounts() {
      return this.accounts;
   }

   public String getCustomerId() {
      return this.customerId;
   }

   public String getFirstName() {
      return this.firstName;
   }

   public String getSurname() {
      return this.surname;
   }

   public String getAddress() {
      return this.address;
   }
}
