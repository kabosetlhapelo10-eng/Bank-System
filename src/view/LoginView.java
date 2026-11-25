package view;

import dao.CustomerDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Customer;

public class LoginView {
   private final Stage stage;
   private final TextField idField = new TextField();
   private final PasswordField passwordField = new PasswordField();
   private final Label messageLabel = new Label();

   public LoginView(Stage stage) {
      this.stage = stage;
      this.createUI();
   }

   private void createUI() {
      Label title = new Label("Banking System Login");
      title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
      GridPane grid = new GridPane();
      grid.setAlignment(Pos.CENTER);
      grid.setHgap((double)10.0F);
      grid.setVgap((double)12.0F);
      grid.setPadding(new Insets((double)30.0F));
      grid.add(title, 0, 0, 2, 1);
      grid.add(new Label("Customer ID:"), 0, 1);
      this.idField.setPromptText("e.g. C001");
      grid.add(this.idField, 1, 1);
      grid.add(new Label("Password:"), 0, 2);
      this.passwordField.setPromptText("password123");
      grid.add(this.passwordField, 1, 2);
      Button loginButton = new Button("Login");
      loginButton.setStyle("-fx-background-color: #1a73e8; -fx-text-fill: white; -fx-font-weight: bold;");
      loginButton.setPrefWidth((double)120.0F);
      grid.add(loginButton, 1, 3);
      this.messageLabel.setStyle("-fx-text-fill: red;");
      grid.add(this.messageLabel, 1, 4);
      loginButton.setOnAction((e) -> this.handleLogin());
      Scene scene = new Scene(grid, (double)420.0F, (double)280.0F);
      this.stage.setTitle("Banking System - Login");
      this.stage.setScene(scene);
      this.stage.show();
   }

   private void handleLogin() {
      String customerId = this.idField.getText().trim();
      String password = this.passwordField.getText();
      if (!customerId.isEmpty() && !password.isEmpty()) {
         try {
            CustomerDAO customerDAO = new CustomerDAO();
            Customer customer = customerDAO.findById(customerId);
            if (customer != null && password.equals("password123")) {
               this.messageLabel.setText("");
               new CustomerDashboardView(this.stage, customer);
            } else {
               this.messageLabel.setText("Invalid Customer ID or password");
            }
         } catch (Exception ex) {
            this.messageLabel.setText("Error: " + ex.getMessage());
            ex.printStackTrace();
         }

      } else {
         this.messageLabel.setText("Please enter ID and password");
      }
   }
}
