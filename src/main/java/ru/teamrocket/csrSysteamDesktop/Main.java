package main.java.ru.teamrocket.csrSysteamDesktop;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends ApplicationCSRSystem {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("View/layout/RootLayout.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();

    }

    //TODO-kotofeyskaya: Лучший комментарий в вашей жизни.
    public static void main(String[] args) {
        launchApp(Main.class, args);
    }
}
