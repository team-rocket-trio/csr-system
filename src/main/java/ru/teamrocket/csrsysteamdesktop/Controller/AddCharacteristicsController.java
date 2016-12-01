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
    @FXML
    private Label labelChar;

    private Button addButton;
    private Button deleteButton;
    private ListView<String> listViewForValue;

    private Characteristic characteristic;
    private int idCharacteristic;



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
            if(this.characteristic == null) {
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
            else{
                this.characteristic.setName(this.nameField.getText());
                this.characteristic.setActivationPrice(Integer.valueOf(this.activationPriceField.getText()));
                this.characteristic.setMonthlyPrice(Integer.valueOf(this.monthlyPriceField.getText()));
                this.characteristic.setType(this.choiceBoxTypeChar.getValue());
                switch (this.choiceBoxTypeChar.getValue()) {
                    case Number:
                        this.characteristic.setValueNumber(Integer.parseInt(this.valueField.getText()));
                        break;
                    case Text:
                        this.characteristic.setValueText(this.valueField.getText());
                        break;
                    case List:
                        this.characteristic.setValueList(this.listViewForValue.getItems());
                        break;
                    default:
                        break;
                }
                new CharacteristicServiceImpl().update(idCharacteristic, characteristic);
                rootController.handlerOnCharacteristics();
            }
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
        this.characteristic = characteristic;
        this.idCharacteristic = idCharacteristic;

        nameField.setText(characteristic.getName());
        activationPriceField.setText(Integer.toString(characteristic.getActivationPrice()));
        monthlyPriceField.setText(Integer.toString(characteristic.getMonthlyPrice()));
        choiceBoxTypeChar.setValue(characteristic.getType());
        if(characteristic.getType().equals(TypeCharacteristic.Text)){
            valueField.setText(characteristic.getValueText());
        }
        else
            if(characteristic.getType().equals(TypeCharacteristic.Number)){
                valueField.setText(Integer.toString(characteristic.getValueNumber()));
            }
            else
                if(characteristic.getType().equals(TypeCharacteristic.List)){
                    listViewForValue.setItems(FXCollections.observableArrayList(characteristic.getValueList()));
                }
        labelChar.setText("Edit global characteristic");
    }

}
