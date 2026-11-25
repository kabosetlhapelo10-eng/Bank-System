package controller;

import dao.AccountDAO;
import dao.TransactionDAO;
import java.time.LocalDateTime;
import model.Account;
import model.Transaction;
import view.CustomerDashboardView;

public class DashboardController {
   private final CustomerDashboardView view;
   private final AccountDAO accountDAO = new AccountDAO();
   private final TransactionDAO transactionDAO = new TransactionDAO();

   public DashboardController(CustomerDashboardView view) {
      this.view = view;
   }

   public void deposit(Account account, double amount) {
      try {
         account.deposit(amount);
         this.accountDAO.updateBalance(account.getAccountNumber(), account.getBalance());
         this.transactionDAO.save(new Transaction("TXN" + System.currentTimeMillis(), "DEPOSIT", amount, LocalDateTime.now(), account));
      } catch (Exception e) {
         e.printStackTrace();
      }

   }

   public void withdraw(Account account, double amount) {
      try {
         account.withdraw(amount);
         this.transactionDAO.save(new Transaction("TXN" + System.currentTimeMillis(), "WITHDRAWAL", amount, LocalDateTime.now(), account));
      } catch (Exception e) {
         e.printStackTrace();
      }

   }
}
