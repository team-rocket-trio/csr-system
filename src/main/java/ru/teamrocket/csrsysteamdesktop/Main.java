package ru.teamrocket.csrsysteamdesktop;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends ApplicationCSRSystem {

    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane root = FXMLLoader.load(getClass().getResource("/layout/RootLayout.fxml"));

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();

    }

    @FXML
    public void stop() {

    }

    //TODO-kotofeyskaya: Лучший комментарий в вашей жизни.
    public static void main(String[] args) {
        launchApp(Main.class, args);
    }
}
