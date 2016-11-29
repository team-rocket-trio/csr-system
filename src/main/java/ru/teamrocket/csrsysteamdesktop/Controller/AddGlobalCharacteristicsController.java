package ru.teamrocket.csrsysteamdesktop.Controller;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    @FXML
    private Label labelChar;

    private Button addButton;
    private Button deleteButton;
    private ListView<String> list;

    private Characteristic characteristic;
    private int idCharacteristic;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addButton = new Button("+");
        addButton.setPrefWidth(30);
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

        deleteButton = new Button("-");
        deleteButton.setPrefWidth(30);
        deleteButton.setLayoutX(valueField.getLayoutX() + valueField.getPrefWidth() + 10);
        deleteButton.setLayoutY(list.getLayoutY());
        deleteButton.setOnAction((event -> {
                    if(list.getSelectionModel().getSelectedItem() != null){
                        list.getItems().remove(list.getSelectionModel().getSelectedIndex());
                    }
                })
        );
        choiceBox.getSelectionModel()
                .selectedItemProperty()
                .addListener( (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                     switch(newValue) {
                         case "Text":
                             anchorPane.getChildren().remove(addButton);
                             anchorPane.getChildren().remove(list);
                             anchorPane.getChildren().remove(deleteButton);
                             break;
                         case "Number":
                             anchorPane.getChildren().remove(addButton);
                             anchorPane.getChildren().remove(list);
                             anchorPane.getChildren().remove(deleteButton);
                             break;
                         case "List":
                             anchorPane.getChildren().add(list);
                             anchorPane.getChildren().add(addButton);
                             anchorPane.getChildren().add(deleteButton);
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
            if (this.characteristic == null) {
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
            } else {
                switch (choiceBox.getSelectionModel().getSelectedItem()) {
                    case "Text":
                        this.characteristic = new CharacteristicText(this.nameField.getText(), this.valueField.getText());
                        this.characteristic.setActivationPrice(this.activationPriceField.getText());
                        this.characteristic.setMonthlyPrice(this.monthlyPriceField.getText());
                        break;
                    case "Number":
                        this.characteristic = new CharacteristicNumber(this.nameField.getText(), Integer.valueOf(this.valueField.getText()));
                        this.characteristic.setActivationPrice(this.activationPriceField.getText());
                        this.characteristic.setMonthlyPrice(this.monthlyPriceField.getText());
                        break;
                    case "List":
                        this.characteristic = new CharacteristicList(this.nameField.getText(), new ArrayList<>(list.getItems()));
                        this.characteristic.setActivationPrice(this.activationPriceField.getText());
                        this.characteristic.setMonthlyPrice(this.monthlyPriceField.getText());
                        break;
                }
                new CharacteristicServiceImpl().update(this.idCharacteristic, this.characteristic);
                rootController.handlerOnCharacteristics();
            }
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

    public void setActionUpdate(int idCharacteristic, Characteristic characteristic) {
        this.characteristic = characteristic;
        this.idCharacteristic = idCharacteristic;

        nameField.setText(characteristic.getName());
        activationPriceField.setText(characteristic.getActivationPrice());
        monthlyPriceField.setText(characteristic.getMonthlyPrice());
        choiceBox.setValue(characteristic.getType());
        if(characteristic instanceof CharacteristicText){
            CharacteristicText characteristicText = (CharacteristicText) characteristic;
            valueField.setText(characteristicText.getValue());
        }
        else
            if(characteristic instanceof CharacteristicNumber){
                CharacteristicNumber characteristicNumber = (CharacteristicNumber) characteristic;
                valueField.setText(Integer.toString(characteristicNumber.getValue()));
            }
            else
                if(characteristic instanceof CharacteristicList){
                    CharacteristicList characteristicList = (CharacteristicList) characteristic;
                    list.setItems(FXCollections.observableArrayList(characteristicList.getValues()));
                }
        labelChar.setText("Edit global characteristic");
    }

}
