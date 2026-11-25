package view;

import dao.DataSeeder;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {
   public void start(Stage stage) {
      DataSeeder.seedIfEmpty();
      new LoginView(stage);
   }

   public static void main(String[] args) {
      launch(args);
   }
}
