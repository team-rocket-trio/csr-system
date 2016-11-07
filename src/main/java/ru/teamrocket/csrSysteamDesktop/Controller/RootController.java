package ru.teamrocket.csrSysteamDesktop.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RootController implements Initializable {

    @FXML
    private BorderPane borderPane;

    //TODO-Alexander: Снять ID borderPane с главного элемента.
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("location = [" + location + "], resources = [" + resources + "]");
    }

    @FXML
    private void handlerOnAddUser(ActionEvent event){
        setScene("/AddUser.fxml");
    }

    @FXML
    private void handlerOnAddOffer(ActionEvent event){
        setScene("/AddOffer.fxml");
    }

    private void setScene(String nameResurce){
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(nameResurce));
            borderPane.setCenter(parent);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
