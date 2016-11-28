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
 * Created by Alexander on 28.11.2016.
 */
public class AddGlobalCharacteristicsController implements Initializable {

    private RootController rootController;

    @FXML
    private TextField nameField;
    @FXML
    private TextField activationPriceField;
    @FXML
    private TextField monthtyPriceField;
    @FXML
    private TextField valueField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setRootController(RootController rootController) {
        this.rootController = rootController;
    }

    @FXML
    private void handleSave(ActionEvent event) {
        Window window = ((Node) event.getTarget()).getScene().getWindow();

        if (inputValidate(window)) {
            Characteristic characteristic = new Characteristic(
                    nameField.getText(),
                    valueField.getText()
            );
            characteristic.setActivationPrice(activationPriceField.getText());
            characteristic.setMonthtyPrice(activationPriceField.getText());

            new CharacteristicServiceImpl().save(characteristic);
            rootController.handlerOnCharacteristics();
        }
    }

    private boolean inputValidate(Window window) {
        String errorMessage = "";

        if (nameField.getText() == null) {
            errorMessage += "Invalid name\n";
        }
        if (valueField.getText() == null) {
            errorMessage += "Invalid value\n";
        }

        if(activationPriceField.getText() == null || activationPriceField.getText().length() == 0){
            errorMessage += "No valid activation price\n";
        }else{
            try{
                Long.parseLong(activationPriceField.getText());
            } catch (NumberFormatException e){
                errorMessage +="No valid activation price\n";
            }
        }

        if(monthtyPriceField.getText() == null || monthtyPriceField.getText().length() == 0){
            errorMessage += "No valid monthty price\n";
        }else{
            try{
                Long.parseLong(monthtyPriceField.getText());
            } catch (NumberFormatException e){
                errorMessage +="No valid monthty price\n";
            }
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

    @FXML
    private void handleClear(ActionEvent event) {
        nameField.setText("");
        activationPriceField.setText("");
        monthtyPriceField.setText("");
        valueField.setText("");
    }

}
