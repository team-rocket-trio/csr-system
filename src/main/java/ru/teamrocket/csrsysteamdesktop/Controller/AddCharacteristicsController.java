package ru.teamrocket.csrsysteamdesktop.Controller;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Window;
import ru.teamrocket.csrsysteamdesktop.Model.Characteristic;
import ru.teamrocket.csrsysteamdesktop.Service.CharacteristicServiceImpl;
import ru.teamrocket.csrsysteamdesktop.Utils.ErrorValidateAlert;
import ru.teamrocket.csrsysteamdesktop.Utils.TypeCharacteristic;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Alexander on 28.11.2016.
 */
public class AddCharacteristicsController implements Initializable {

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
    private ChoiceBox<TypeCharacteristic> choiceBoxTypeChar;

    @FXML
    private AnchorPane anchorPaneForList;
    @FXML
    private AnchorPane anchorPaneForButtom;

    private Button addButton;
    private Button deleteButton;
    private ListView<String> listViewForValue;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeChoiceBox(this.choiceBoxTypeChar);

        //TODO-Alexander: Вынести это отсюда!
        addButton = new Button("+");
        addButton.setPrefWidth(30);
        addButton.setOnAction((event -> {
                    listViewForValue.getItems().add(valueField.getText());
                    valueField.clear();
                })
        );

        deleteButton = new Button("-");
        deleteButton.setPrefWidth(30);
        deleteButton.setLayoutY(anchorPaneForButtom.getLayoutY() + 30);
        deleteButton.setOnAction((event -> {
                    if (listViewForValue.getSelectionModel().getSelectedItem() != null) {
                        listViewForValue.getItems().remove(listViewForValue.getSelectionModel().getSelectedIndex());
                    }
                })
        );

        this.listViewForValue = new ListView<String>();
        this.listViewForValue.setPrefWidth(270);
        this.listViewForValue.setPrefHeight(60);
        this.listViewForValue.setEditable(true);

    }

    public void setRootController(RootController rootController) {
        this.rootController = rootController;
    }

    private void initializeChoiceBox(ChoiceBox<TypeCharacteristic> choiceBoxTypeChar) {
        choiceBoxTypeChar.setItems(FXCollections.observableArrayList(TypeCharacteristic.values()));
        choiceBoxTypeChar.getSelectionModel().selectFirst();
    }


    @FXML
    private void handleChangeChoiceBox(ActionEvent event) {

        switch (choiceBoxTypeChar.getSelectionModel().getSelectedItem()) {
            case List:

                this.anchorPaneForList.getChildren().add(this.listViewForValue);
                this.anchorPaneForButtom.getChildren().addAll(this.addButton, this.deleteButton);

                break;
            case Number:

                this.anchorPaneForList.getChildren().remove(this.listViewForValue);
                this.anchorPaneForButtom.getChildren().removeAll(this.addButton, this.deleteButton);

                break;
            case Text:

                this.anchorPaneForList.getChildren().remove(this.listViewForValue);
                this.anchorPaneForButtom.getChildren().removeAll(this.addButton, this.deleteButton);

                break;
            default:
                break;
        }

    }

    private void setListView() {

    }

    @FXML
    private void handleSave(ActionEvent event) {
        Window window = ((Node) event.getTarget()).getScene().getWindow();

        if (inputValidate(window)) {
            Characteristic characteristic = new Characteristic(
                    this.nameField.getText(),
                    Integer.parseInt(this.activationPriceField.getText()),
                    Integer.parseInt(this.monthlyPriceField.getText()),
                    this.choiceBoxTypeChar.getValue()
            );

            switch (this.choiceBoxTypeChar.getValue()) {
                case Number:
                    characteristic.setValueNumber(Integer.parseInt(this.valueField.getText()));
                    break;
                case Text:
                    characteristic.setValueText(this.valueField.getText());
                    break;
                case List:
                    characteristic.setValueList(this.listViewForValue.getItems());
                    break;
                default:
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

        switch (this.choiceBoxTypeChar.getValue()) {
            case List:
                if (this.listViewForValue.getItems().size() == 0) {
                    errorMessage += "No item in Value\n";
                }
                break;
            case Text:
                if (this.valueField.getText().length() == 0) {
                    errorMessage += "No valid Value\n";
                }
                break;
            case Number:
                if (this.valueField.getText() == null || this.valueField.getText().length() == 0) {
                    errorMessage += "No valid value\n";
                } else {
                    try {
                        Long.parseLong(this.valueField.getText());
                    } catch (NumberFormatException e) {
                        errorMessage += "No valid value\n";
                    }
                }
                break;
            default:
                break;
        }

        if (activationPriceField.getText() == null || activationPriceField.getText().length() == 0) {
            errorMessage += "No valid activation price\n";
        } else {
            try {
                Long.parseLong(activationPriceField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid activation price\n";
            }
        }

        if (monthlyPriceField.getText() == null || monthlyPriceField.getText().length() == 0) {
            errorMessage += "No valid monthty price\n";
        } else {
            try {
                Long.parseLong(monthlyPriceField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid monthty price\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            new ErrorValidateAlert(window, errorMessage).show();
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
//        this.characteristic = characteristic;
//        this.idCharacteristic = idCharacteristic;
//
//        nameField.setText(characteristic.getName());
//        activationPriceField.setText(characteristic.getActivationPrice());
//        monthlyPriceField.setText(characteristic.getMonthlyPrice());
//        choiceBox.setValue(characteristic.getType());
//        if(characteristic instanceof CharacteristicText){
//            CharacteristicText characteristicText = (CharacteristicText) characteristic;
//            valueField.setText(characteristicText.getValue());
//        }
//        else
//            if(characteristic instanceof CharacteristicNumber){
//                CharacteristicNumber characteristicNumber = (CharacteristicNumber) characteristic;
//                valueField.setText(Integer.toString(characteristicNumber.getValue()));
//            }
//            else
//                if(characteristic instanceof CharacteristicList){
//                    CharacteristicList characteristicList = (CharacteristicList) characteristic;
//                    list.setItems(FXCollections.observableArrayList(characteristicList.getValues()));
//                }
//        labelChar.setText("Edit global characteristic");
    }

}
