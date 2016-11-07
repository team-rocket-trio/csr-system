package ru.teamrocket.csrSysteamDesktop;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends ApplicationCSRSystem {

    @Override
    public void start(Stage primaryStage) throws Exception{

        BorderPane root = FXMLLoader.load(getClass().getResource("/layout/RootLayout.fxml"));

        //TODO-Alexander: Вынести отсюда это! Start#1!
        Parent addUser = FXMLLoader.load(getClass().getResource("/AddUser.fxml"));
        root.setCenter(addUser);
        //TODO-Alexander: End#1!

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();

    }

    //TODO-kotofeyskaya: Лучший комментарий в вашей жизни.
    public static void main(String[] args) {
        launchApp(Main.class, args);
    }
}
