package model;

import java.time.LocalDateTime;

public class Transaction {
   private String transactionId;
   private String type;
   private double amount;
   private LocalDateTime date;
   private Account account;

   public Transaction(String transactionId, String type, double amount, LocalDateTime date, Account account) {
      this.transactionId = transactionId;
      this.type = type;
      this.amount = amount;
      this.date = date;
      this.account = account;
   }

   public String getTransactionId() {
      return this.transactionId;
   }

   public String getType() {
      return this.type;
   }

   public double getAmount() {
      return this.amount;
   }

   public LocalDateTime getDate() {
      return this.date;
   }

   public Account getAccount() {
      return this.account;
   }
}
