package dao;

import java.time.LocalDate;
import model.ChequeAccount;
import model.Customer;
import model.InvestmentAccount;
import model.SavingsAccount;

public class DataSeeder {
   public static void seedIfEmpty() {
      CustomerDAO customerDAO = new CustomerDAO();
      AccountDAO accountDAO = new AccountDAO();

      try {
         if (customerDAO.findById("C001") != null) {
            return;
         }

         String[][] people = new String[][]{{"C001", "Kabo", "Setlhapelo", "Gaborone"}, {"C002", "Neo", "Kgosi", "Francistown"}, {"C003", "Lesedi", "Sebina", "Maun"}, {"C004", "Naledi", "Phiri", "Palapye"}, {"C005", "Tshepo", "Dube", "Serowe"}, {"C006", "Boitumelo", "Seretse", "Kasane"}, {"C007", "Kagiso", "Mothibi", "Lobatse"}, {"C008", "Mpho", "Tau", "Molepolole"}, {"C009", "Onkabetse", "Ramotswe", "Mochudi"}, {"C010", "Gorata", "Ndlovu", "Jwaneng"}};

         for(int i = 0; i < people.length; ++i) {
            String[] p = people[i];
            Customer customer = new Customer(p[0], p[1], p[2], p[3], "password123");
            customerDAO.save(customer);
            SavingsAccount savings = new SavingsAccount("SAV" + String.format("%03d", i + 1), (double)(1000 + i * 200), "Gaborone Main", LocalDate.now());
            accountDAO.save(savings, p[0]);
            if (i % 2 == 0) {
               InvestmentAccount investment = new InvestmentAccount("INV" + String.format("%03d", i + 1), (double)(2000 + i * 300), "Gaborone Main", LocalDate.now());
               accountDAO.save(investment, p[0]);
            }

            if (i % 3 == 0) {
               ChequeAccount cheque = new ChequeAccount("CHQ" + String.format("%03d", i + 1), (double)(3000 + i * 150), "Gaborone Main", LocalDate.now(), "Sample Employer Ltd", "Gaborone");
               accountDAO.save(cheque, p[0]);
            }
         }

         System.out.println("Successfully seeded 10 customers.");
      } catch (Exception e) {
         System.out.println("Seeding failed: " + e.getMessage());
         e.printStackTrace();
      }

   }
}
