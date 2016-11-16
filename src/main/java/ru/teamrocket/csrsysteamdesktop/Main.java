package ru.teamrocket.csrsysteamdesktop;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;

public class Main extends ApplicationCSRSystem {

    public static final String pathData = new File("").getAbsolutePath() + "/data/";

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

        //TODO-Alexander: Вынести в отдельную функцию
        File dataFolder = new File(Main.pathData);

        if(!dataFolder.exists()){
            dataFolder.mkdir();
        }

        launchApp(Main.class, args);
    }
}
