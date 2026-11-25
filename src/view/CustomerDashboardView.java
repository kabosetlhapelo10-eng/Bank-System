package view;

import dao.AccountDAO;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Account;
import model.Customer;
import model.InterestBearing;

public class CustomerDashboardView {
   private final Stage stage;
   private final Customer customer;
   private final ComboBox<String> accountSelector = new ComboBox();
   private final TextField amountField = new TextField();
   private final Label messageLabel = new Label();

   public CustomerDashboardView(Stage stage, Customer customer) {
      this.stage = stage;
      this.customer = customer;
      this.createUI();
   }

   private void createUI() {
      String var10002 = this.customer.getFirstName();
      Label welcome = new Label("Welcome, " + var10002 + " " + this.customer.getSurname());
      welcome.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");
      Button logoutBtn = new Button("Logout");
      logoutBtn.setOnAction((ex) -> new LoginView(this.stage));
      HBox header = new HBox((double)20.0F, new Node[]{welcome, logoutBtn});
      header.setAlignment(Pos.CENTER_LEFT);
      header.setPadding(new Insets((double)15.0F));
      Label accountsTitle = new Label("My Accounts");
      accountsTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
      HBox accountsRow = new HBox((double)15.0F);
      accountsRow.setPadding(new Insets((double)10.0F));

      try {
         AccountDAO accountDAO = new AccountDAO();

         for(Account acc : accountDAO.findByCustomerId(this.customer.getCustomerId())) {
            String typeName = acc.getClass().getSimpleName().replace("Account", "");
            String extra = acc instanceof InterestBearing ? "Interest bearing" : "No interest";
            String balanceText = String.format("BWP %.2f", acc.getBalance());
            VBox card = this.createAccountCard(typeName + " Account", acc.getAccountNumber(), balanceText, extra);
            accountsRow.getChildren().add(card);
            ObservableList var10000 = this.accountSelector.getItems();
            String var10001 = acc.getAccountNumber();
            var10000.add(var10001 + " - " + typeName);
         }

         if (!this.accountSelector.getItems().isEmpty()) {
            this.accountSelector.setValue((String)this.accountSelector.getItems().get(0));
         }
      } catch (SQLException e) {
         e.printStackTrace();
         accountsRow.getChildren().add(new Label("Error loading accounts"));
      }

      Label actionTitle = new Label("Deposit / Withdraw");
      actionTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
      this.accountSelector.setPrefWidth((double)200.0F);
      this.amountField.setPromptText("Enter amount");
      this.amountField.setPrefWidth((double)150.0F);
      Button depositBtn = new Button("Deposit");
      Button withdrawBtn = new Button("Withdraw");
      depositBtn.setStyle("-fx-background-color: #1a73e8; -fx-text-fill: white;");
      withdrawBtn.setStyle("-fx-background-color: #d93025; -fx-text-fill: white;");
      depositBtn.setOnAction((ex) -> {
         String amt = this.amountField.getText().trim();
         if (!amt.isEmpty()) {
            this.messageLabel.setText("Deposit of BWP " + amt + " successful.");
            this.messageLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
            this.amountField.clear();
         }

      });
      withdrawBtn.setOnAction((ex) -> {
         String amt = this.amountField.getText().trim();
         if (!amt.isEmpty()) {
            this.messageLabel.setText("Withdrawal of BWP " + amt + " processed.");
            this.messageLabel.setStyle("-fx-text-fill: #d93025; -fx-font-weight: bold;");
            this.amountField.clear();
         }

      });
      HBox actionBox = new HBox((double)10.0F, new Node[]{this.accountSelector, this.amountField, depositBtn, withdrawBtn});
      actionBox.setAlignment(Pos.CENTER_LEFT);
      actionBox.setPadding(new Insets((double)10.0F));
      Label txTitle = new Label("Recent Transaction");
      txTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
      TextArea txArea = new TextArea();
      txArea.setEditable(false);
      txArea.setPrefHeight((double)100.0F);
      txArea.setText("Transaction history will appear here");
      VBox transactionBox = new VBox((double)8.0F, new Node[]{txTitle, txArea});
      transactionBox.setPadding(new Insets((double)10.0F));
      VBox root = new VBox((double)12.0F, new Node[]{header, new Separator(), accountsTitle, accountsRow, new Separator(), actionTitle, actionBox, this.messageLabel, new Separator(), transactionBox});
      root.setPadding(new Insets((double)20.0F));
      root.setStyle("-fx-background-color: #f4f6f8;");
      Scene scene = new Scene(root, (double)950.0F, (double)620.0F);
      this.stage.setTitle("Banking System - Dashboard");
      this.stage.setScene(scene);
      this.stage.show();
   }

   private VBox createAccountCard(String type, String number, String balance, String extra) {
      Label typeLabel = new Label(type);
      typeLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
      Label numberLabel = new Label(number);
      Label balanceLabel = new Label(balance);
      balanceLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #1a73e8; -fx-font-weight: bold;");
      Label extraLabel = new Label(extra);
      extraLabel.setStyle("-fx-text-fill: #555;");
      VBox card = new VBox((double)6.0F, new Node[]{typeLabel, numberLabel, balanceLabel, extraLabel});
      card.setPadding(new Insets((double)15.0F));
      card.setStyle("-fx-background-color: white; -fx-background-radius: 8; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 6, 0, 0, 2);");
      card.setPrefWidth((double)260.0F);
      return card;
   }
}
