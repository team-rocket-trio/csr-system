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
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Kate on 21.11.2016.
 */
public class AddCharacteristicController implements Initializable {
    @FXML
    private TextField charNameField;
    @FXML
    private TextField charValueField;

    private List<Characteristic> characteristicsList;

    private RootController rootController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setCharacteristicsList(List<Characteristic> characteristicsList) {
        this.characteristicsList = characteristicsList;
    }

    public void setRootController(RootController rootController) {
        this.rootController = rootController;
    }

    @FXML
    private void createAction(ActionEvent event) {
        Window window = ((Node) event.getTarget()).getScene().getWindow();

       /* if (inputValidate(window)) {
            Iterator<Characteristic> iterator = characteristicsList.iterator();
            if(!characteristicsList.isEmpty())
            while (iterator.hasNext()) {
                Characteristic characteristic = iterator.next();
                if (characteristic.getName().equals(charNameField.getText())) {
                    characteristic.setValue(characteristic.getValue() + ", " + charValueField.getText());
                } else {
                    characteristicsList.add(new Characteristic(charNameField.getText(), charValueField.getText()));
                    break;
                }
            }
            else
                characteristicsList.add(new Characteristic(charNameField.getText(), charValueField.getText()));
        }*/

        if (inputValidate(window)) {
            characteristicsList.add(new Characteristic(charNameField.getText(), charValueField.getText()));
            rootController.handlerOnAddOffer(this.characteristicsList);
        }
    }

    @FXML
    private void clearAction() {
        charNameField.setText(null);
        charValueField.setText(null);
    }

    @FXML
    private void handlerOnBack(ActionEvent event) {
        rootController.handlerOnAddOffer(this.characteristicsList);
    }

    private boolean inputValidate(Window window) {
        String errorMessage = "";

        if (charNameField.getText() == null) {
            errorMessage += "Invalid name\n";
        }
        if (charValueField.getText() == null) {
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
