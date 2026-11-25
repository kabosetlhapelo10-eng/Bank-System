package view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import model.ChequeAccount;
import model.Customer;
import model.InvestmentAccount;
import model.SavingsAccount;
import model.Transaction;

public class Main {
   public static void main(String[] args) {
      Customer customer = new Customer("C001", "Kabo", "Setlhapelo", "Gaborone", "12345");
      customer.register();
      if (customer.login("12345")) {
         System.out.println("Login successful.");
      } else {
         System.out.println("Login failed.");
      }

      SavingsAccount savings = new SavingsAccount("S001", (double)1000.0F, "Gaborone", LocalDate.now());
      InvestmentAccount investment = new InvestmentAccount("I001", (double)5000.0F, "Gaborone", LocalDate.now());
      ChequeAccount cheque = new ChequeAccount("CH001", (double)2000.0F, "Gaborone", LocalDate.now(), "ABC Company", "Gaborone");
      customer.addAccount(savings);
      customer.addAccount(investment);
      customer.addAccount(cheque);
      System.out.println("\nNumber of accounts: " + customer.getAccounts().size());
      savings.deposit((double)500.0F);
      System.out.println("\nSavings balance: BWP " + savings.getBalance());
      System.out.println("Savings interest: BWP " + savings.calculateMonthlyInterest());
      savings.payInterest();
      System.out.println("Balance after interest: BWP " + savings.getBalance());
      investment.deposit((double)1000.0F);
      investment.withdraw((double)500.0F);
      System.out.println("\nInvestment balance: BWP " + investment.getBalance());
      System.out.println("Investment interest: BWP " + investment.calculateMonthlyInterest());
      investment.payInterest();
      System.out.println("Balance after interest: BWP " + investment.getBalance());
      cheque.deposit((double)3000.0F);
      cheque.withdraw((double)1000.0F);
      System.out.println("\nCheque account balance: BWP " + cheque.getBalance());
      Transaction transaction = new Transaction("T001", "DEPOSIT", (double)500.0F, LocalDateTime.now(), savings);
      System.out.println("\nmodel.Transaction ID: " + transaction.getTransactionId());
      System.out.println("model.Transaction Type: " + transaction.getType());
      System.out.println("model.Transaction Amount: BWP " + transaction.getAmount());
      System.out.println("model.Transaction model.Account: " + transaction.getAccount().getAccountNumber());
   }
}
