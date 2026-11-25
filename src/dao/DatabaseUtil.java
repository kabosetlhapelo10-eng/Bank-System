package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtil {
   private static final String URL = "jdbc:h2:./bankingdb";

   public static Connection getConnection() throws SQLException {
      return DriverManager.getConnection("jdbc:h2:./bankingdb", "sa", "");
   }

   private static void createTables() throws SQLException {
      Connection conn = getConnection();

      try {
         Statement stmt = conn.createStatement();

         try {
            stmt.execute("    CREATE TABLE IF NOT EXISTS customers (\n        customer_id VARCHAR(20) PRIMARY KEY,\n        first_name VARCHAR(50),\n        surname VARCHAR(50),\n        address VARCHAR(100),\n        password VARCHAR(100)\n    )\n");
            stmt.execute("    CREATE TABLE IF NOT EXISTS accounts (\n        account_number VARCHAR(20) PRIMARY KEY,\n        customer_id VARCHAR(20),\n        account_type VARCHAR(30),\n        balance DOUBLE,\n        branch VARCHAR(50),\n        employer_name VARCHAR(100),\n        employer_address VARCHAR(150)\n    )\n");
         } catch (Throwable var6) {
            if (stmt != null) {
               try {
                  stmt.close();
               } catch (Throwable var5) {
                  var6.addSuppressed(var5);
               }
            }

            throw var6;
         }

         if (stmt != null) {
            stmt.close();
         }
      } catch (Throwable var7) {
         if (conn != null) {
            try {
               conn.close();
            } catch (Throwable var4) {
               var7.addSuppressed(var4);
            }
         }

         throw var7;
      }

      if (conn != null) {
         conn.close();
      }

   }

   static {
      try {
         Class.forName("org.h2.Driver");
         createTables();
      } catch (Exception e) {
         e.printStackTrace();
      }

   }
}
