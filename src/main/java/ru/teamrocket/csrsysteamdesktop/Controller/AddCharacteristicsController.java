package ru.teamrocket.csrsysteamdesktop.Controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;
import ru.teamrocket.csrsysteamdesktop.Model.Characteristic;
import ru.teamrocket.csrsysteamdesktop.Service.CharacteristicServiceImpl;
import ru.teamrocket.csrsysteamdesktop.Utils.ErrorValidateAlert;
import ru.teamrocket.csrsysteamdesktop.Utils.TypeCharacteristic;

import java.net.URL;
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
    private AnchorPane anchorPaneForButton;
    @FXML
    private Label labelChar;

    private Button addButton;
    private Button deleteButton;
    private TextField maxField;
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
        deleteButton.setLayoutY(anchorPaneForButton.getLayoutY() + 30);
        deleteButton.setOnAction((event -> {
                    if (listViewForValue.getSelectionModel().getSelectedItem() != null) {
                        listViewForValue.getItems().remove(listViewForValue.getSelectionModel().getSelectedIndex());
                    }
                })
        );

        maxField = new TextField();

        anchorPaneForList.setLeftAnchor(maxField, 0.0);
        anchorPaneForList.setRightAnchor(maxField, 0.0);

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
    private void handleChangeChoiceBox() {

        switch (choiceBoxTypeChar.getSelectionModel().getSelectedItem()) {
            case List:

                valueField.setPromptText("");
                this.anchorPaneForList.getChildren().remove(this.maxField);
                this.anchorPaneForList.getChildren().addAll(this.listViewForValue);
                this.anchorPaneForButton.getChildren().addAll(this.addButton, this.deleteButton);

                break;
            case Number:

                maxField.setPromptText("max");
                valueField.setPromptText("min");
                this.anchorPaneForList.getChildren().add(this.maxField);
                this.anchorPaneForList.getChildren().removeAll(this.listViewForValue);
                this.anchorPaneForButton.getChildren().removeAll(this.addButton, this.deleteButton);

                break;
            case Text:

                valueField.setPromptText("");
                this.anchorPaneForList.getChildren().removeAll(this.listViewForValue, this.maxField);
                this.anchorPaneForButton.getChildren().removeAll(this.addButton, this.deleteButton);

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
                        characteristic.setMinValueNumber(Integer.parseInt(this.valueField.getText()));
                        characteristic.setMaxValueNumber(Integer.parseInt(this.maxField.getText()));
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
                        this.characteristic.setMinValueNumber(Integer.parseInt(this.valueField.getText()));
                        this.characteristic.setMaxValueNumber(Integer.parseInt(this.maxField.getText()));
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
                    errorMessage += "Invalid Value\n";
                }
                break;
            case Number:
                if (this.valueField.getText() == null || this.maxField.getText() == null) {
                    errorMessage += "Invalid value\n";
                } else {
                    try {
                        Long.parseLong(this.valueField.getText());
                        Long.parseLong(this.maxField.getText());
                        if(Integer.valueOf(this.valueField.getText()) > Integer.valueOf(this.maxField.getText()))
                            errorMessage += "Minimum value cannot be greater than maximum value\n";
                    } catch (NumberFormatException e) {
                        errorMessage += "Invalid value\n";
                    }
                }
                break;
            default:
                break;
        }

        if (activationPriceField.getText() == null || activationPriceField.getText().length() == 0) {
            errorMessage += "Invalid activation price\n";
        } else {
            try {
                Long.parseLong(activationPriceField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Invalid activation price\n";
            }
        }

        if (monthlyPriceField.getText() == null || monthlyPriceField.getText().length() == 0) {
            errorMessage += "Invalid monthly price\n";
        } else {
            try {
                Long.parseLong(monthlyPriceField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Invalid monthly price\n";
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
                handleChangeChoiceBox();
                valueField.setText(Integer.toString(characteristic.getMinValueNumber()));
                maxField.setText(Integer.toString(characteristic.getMaxValueNumber()));
            }
            else
                if(characteristic.getType().equals(TypeCharacteristic.List)){
                    listViewForValue.setItems(FXCollections.observableArrayList(characteristic.getValueList()));
                }
        labelChar.setText("Edit global characteristic");
    }

}
