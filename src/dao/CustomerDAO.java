package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Customer;

public class CustomerDAO {
   public void save(Customer customer) throws SQLException {
      String sql = "INSERT INTO customers (customer_id, first_name, surname, address, password) VALUES (?, ?, ?, ?, ?)";
      Connection conn = DatabaseUtil.getConnection();

      try {
         PreparedStatement ps = conn.prepareStatement(sql);

         try {
            ps.setString(1, customer.getCustomerId());
            ps.setString(2, customer.getFirstName());
            ps.setString(3, customer.getSurname());
            ps.setString(4, customer.getAddress());
            ps.setString(5, "password123");
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

   public Customer findById(String customerId) throws SQLException {
      String sql = "SELECT * FROM customers WHERE customer_id = ?";
      Connection conn = DatabaseUtil.getConnection();

      Customer var6;
      label67: {
         try {
            PreparedStatement ps = conn.prepareStatement(sql);

            label69: {
               try {
                  ps.setString(1, customerId);
                  ResultSet rs = ps.executeQuery();
                  if (!rs.next()) {
                     break label69;
                  }

                  var6 = new Customer(rs.getString("customer_id"), rs.getString("first_name"), rs.getString("surname"), rs.getString("address"), rs.getString("password"));
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
               break label67;
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

         return null;
      }

      if (conn != null) {
         conn.close();
      }

      return var6;
   }
}
