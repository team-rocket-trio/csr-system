package ru.teamrocket.csrsysteamdesktop.Controller;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;
import ru.teamrocket.csrsysteamdesktop.Model.Characteristic;
import ru.teamrocket.csrsysteamdesktop.Model.CharacteristicText;
import ru.teamrocket.csrsysteamdesktop.Model.CharacteristicList;
import ru.teamrocket.csrsysteamdesktop.Model.CharacteristicNumber;
import ru.teamrocket.csrsysteamdesktop.Service.CharacteristicServiceImpl;

import java.net.URL;
import java.util.ArrayList;
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
    private TextField monthlyPriceField;
    @FXML
    private TextField valueField;
    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private AnchorPane anchorPane;

    private Button addButton;
    private ListView<String> list;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addButton = new Button("+");
        addButton.setLayoutX(valueField.getLayoutX() + valueField.getPrefWidth() + 10);
        addButton.setLayoutY(valueField.getLayoutY());
        addButton.setOnAction((event -> {
                list.getItems().add(valueField.getText());
                valueField.clear();
                })
        );

        list = new ListView<>();
        list.setLayoutX(valueField.getLayoutX());
        list.setLayoutY(valueField.getLayoutY()+ 30);
        list.setPrefHeight(150);
        list.setPrefWidth(260);
        list.setEditable(true);
        list.setCellFactory(TextFieldListCell.forListView());
        list.setOnEditCommit((ListView.EditEvent<String> t) ->  {
                list.getItems().set(t.getIndex(), t.getNewValue());
                });

        choiceBox.getSelectionModel()
                .selectedItemProperty()
                .addListener( (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                     switch(newValue) {
                         case "Text":
                             anchorPane.getChildren().remove(addButton);
                             anchorPane.getChildren().remove(list);
                             break;
                         case "Number":
                             anchorPane.getChildren().remove(addButton);
                             anchorPane.getChildren().remove(list);
                             break;
                         case "List":
                             anchorPane.getChildren().add(list);
                             anchorPane.getChildren().add(addButton);
                             break;
                     }
                 });
    }

    public void setRootController(RootController rootController) {
        this.rootController = rootController;
    }

    @FXML
    private void handleSave(ActionEvent event) {
        Window window = ((Node) event.getTarget()).getScene().getWindow();
        Characteristic characteristic = null;
        if (inputValidate(window)) {
            switch (choiceBox.getSelectionModel().getSelectedItem()) {
                case "Text":
                    characteristic = new CharacteristicText(
                            nameField.getText(),
                            valueField.getText()
                    );
                    characteristic.setActivationPrice(activationPriceField.getText());
                    characteristic.setMonthlyPrice(monthlyPriceField.getText());

                    break;
                case "Number":
                    characteristic = new CharacteristicNumber(
                            nameField.getText(),
                            Integer.valueOf(valueField.getText())
                    );
                    characteristic.setActivationPrice(activationPriceField.getText());
                    characteristic.setMonthlyPrice(monthlyPriceField.getText());
                    break;
                case "List":
                    characteristic = new CharacteristicList(
                            nameField.getText(),
                            new ArrayList<>(list.getItems())
                    );
                    characteristic.setActivationPrice(activationPriceField.getText());
                    characteristic.setMonthlyPrice(monthlyPriceField.getText());
                    break;
            }
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

        if(monthlyPriceField.getText() == null || monthlyPriceField.getText().length() == 0){
            errorMessage += "No valid monthty price\n";
        }else{
            try{
                Long.parseLong(monthlyPriceField.getText());
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
        monthlyPriceField.setText("");
        valueField.setText("");
    }

}
