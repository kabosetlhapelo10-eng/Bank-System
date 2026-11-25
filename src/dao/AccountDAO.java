package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.ChequeAccount;
import model.InvestmentAccount;
import model.SavingsAccount;

public class AccountDAO {
   public void save(Account account, String customerId) throws SQLException {
      String sql = "    INSERT INTO accounts (account_number, customer_id, account_type, balance, branch, employer_name, employer_address)\n    VALUES (?, ?, ?, ?, ?, ?, ?)\n";
      Connection conn = DatabaseUtil.getConnection();

      try {
         PreparedStatement ps = conn.prepareStatement(sql);

         try {
            ps.setString(1, account.getAccountNumber());
            ps.setString(2, customerId);
            ps.setString(3, account.getClass().getSimpleName());
            ps.setDouble(4, account.getBalance());
            ps.setString(5, account.getBranch());
            if (account instanceof ChequeAccount ca) {
               ps.setString(6, ca.getEmployerName());
               ps.setString(7, ca.getEmployerAddress());
            } else {
               ps.setNull(6, 12);
               ps.setNull(7, 12);
            }

            ps.executeUpdate();
         } catch (Throwable var10) {
            if (ps != null) {
               try {
                  ps.close();
               } catch (Throwable var9) {
                  var10.addSuppressed(var9);
               }
            }

            throw var10;
         }

         if (ps != null) {
            ps.close();
         }
      } catch (Throwable var11) {
         if (conn != null) {
            try {
               conn.close();
            } catch (Throwable var8) {
               var11.addSuppressed(var8);
            }
         }

         throw var11;
      }

      if (conn != null) {
         conn.close();
      }

   }

   public void updateBalance(String accountNumber, double newBalance) throws SQLException {
      String sql = "UPDATE accounts SET balance = ? WHERE account_number = ?";
      Connection conn = DatabaseUtil.getConnection();

      try {
         PreparedStatement ps = conn.prepareStatement(sql);

         try {
            ps.setDouble(1, newBalance);
            ps.setString(2, accountNumber);
            ps.executeUpdate();
         } catch (Throwable var11) {
            if (ps != null) {
               try {
                  ps.close();
               } catch (Throwable var10) {
                  var11.addSuppressed(var10);
               }
            }

            throw var11;
         }

         if (ps != null) {
            ps.close();
         }
      } catch (Throwable var12) {
         if (conn != null) {
            try {
               conn.close();
            } catch (Throwable var9) {
               var12.addSuppressed(var9);
            }
         }

         throw var12;
      }

      if (conn != null) {
         conn.close();
      }

   }

   public List<Account> findByCustomerId(String customerId) throws SQLException {
      List<Account> accounts = new ArrayList();
      String sql = "SELECT * FROM accounts WHERE customer_id = ?";
      Connection conn = DatabaseUtil.getConnection();

      try {
         PreparedStatement ps = conn.prepareStatement(sql);

         try {
            ps.setString(1, customerId);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
               String type = rs.getString("account_type");
               String accNo = rs.getString("account_number");
               double balance = rs.getDouble("balance");
               String branch = rs.getString("branch");
               if ("SavingsAccount".equals(type)) {
                  accounts.add(new SavingsAccount(accNo, balance, branch, LocalDate.now()));
               } else if ("InvestmentAccount".equals(type)) {
                  accounts.add(new InvestmentAccount(accNo, balance, branch, LocalDate.now()));
               } else if ("ChequeAccount".equals(type)) {
                  accounts.add(new ChequeAccount(accNo, balance, branch, LocalDate.now(), rs.getString("employer_name"), rs.getString("employer_address")));
               }
            }
         } catch (Throwable var14) {
            if (ps != null) {
               try {
                  ps.close();
               } catch (Throwable var13) {
                  var14.addSuppressed(var13);
               }
            }

            throw var14;
         }

         if (ps != null) {
            ps.close();
         }
      } catch (Throwable var15) {
         if (conn != null) {
            try {
               conn.close();
            } catch (Throwable var12) {
               var15.addSuppressed(var12);
            }
         }

         throw var15;
      }

      if (conn != null) {
         conn.close();
      }

      return accounts;
   }
}
