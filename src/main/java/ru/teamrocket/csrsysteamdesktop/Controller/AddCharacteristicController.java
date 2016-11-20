package ru.teamrocket.csrsysteamdesktop.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import ru.teamrocket.csrsysteamdesktop.Model.Characteristic;
import ru.teamrocket.csrsysteamdesktop.Service.CharacteristicServiceImpl;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Kate on 21.11.2016.
 */
public class AddCharacteristicController  implements Initializable {
    @FXML
    private TextField charNameField;
    @FXML
    private TextField charValueField;

    private RootController rootController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setRootController(RootController rootController) {
        this.rootController = rootController;
    }

    @FXML
    private void createAction(ActionEvent event) {
        Window window = ((Node) event.getTarget()).getScene().getWindow();

        if(inputValidate(window)){
            Characteristic characteristic = new Characteristic(charNameField.getText(),charValueField.getText());
            new CharacteristicServiceImpl().save(characteristic);
            clearAction();
        }
    }

    @FXML
    private void clearAction() {
        charNameField.setText(null);
        charValueField.setText(null);
    }

    private boolean inputValidate(Window window) {
        String errorMessage = "";

        if (charNameField.getText() == null){
            errorMessage += "Invalid name\n";
        }
        if (charValueField.getText() == null){
            errorMessage += "Invalid value\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(window);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();

            return false;
        }
    }

}
