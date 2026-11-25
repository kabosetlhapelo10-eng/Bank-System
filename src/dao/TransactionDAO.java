package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import model.Transaction;

public class TransactionDAO {
   public void save(Transaction transaction) throws SQLException {
      String sql = "    INSERT INTO transactions (transaction_id, account_number, type, amount, date)\n    VALUES (?, ?, ?, ?, ?)\n";
      Connection conn = DatabaseUtil.getConnection();

      try {
         PreparedStatement ps = conn.prepareStatement(sql);

         try {
            ps.setString(1, transaction.getTransactionId());
            ps.setString(2, transaction.getAccount().getAccountNumber());
            ps.setString(3, transaction.getType());
            ps.setDouble(4, transaction.getAmount());
            ps.setTimestamp(5, Timestamp.valueOf(transaction.getDate()));
            ps.executeUpdate();
         } catch (Throwable var9) {
            if (ps != null) {
               try {
                  ps.close();
               } catch (Throwable var8) {
                  var9.addSuppressed(var8);
               }
            }

            throw var9;
         }

         if (ps != null) {
            ps.close();
         }
      } catch (Throwable var10) {
         if (conn != null) {
            try {
               conn.close();
            } catch (Throwable var7) {
               var10.addSuppressed(var7);
            }
         }

         throw var10;
      }

      if (conn != null) {
         conn.close();
      }

   }

   public void createTableIfNotExists() throws SQLException {
      String sql = "    CREATE TABLE IF NOT EXISTS transactions (\n        transaction_id VARCHAR(20) PRIMARY KEY,\n        account_number VARCHAR(20),\n        type VARCHAR(20),\n        amount DOUBLE,\n        date TIMESTAMP\n    )\n";
      Connection conn = DatabaseUtil.getConnection();

      try {
         Statement stmt = conn.createStatement();

         try {
            stmt.execute(sql);
         } catch (Throwable var8) {
            if (stmt != null) {
               try {
                  stmt.close();
               } catch (Throwable var7) {
                  var8.addSuppressed(var7);
               }
            }

            throw var8;
         }

         if (stmt != null) {
            stmt.close();
         }
      } catch (Throwable var9) {
         if (conn != null) {
            try {
               conn.close();
            } catch (Throwable var6) {
               var9.addSuppressed(var6);
            }
         }

         throw var9;
      }

      if (conn != null) {
         conn.close();
      }

   }
}
