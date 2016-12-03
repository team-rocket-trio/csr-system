package ru.teamrocket.csrsysteamdesktop.Controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;
import javafx.util.StringConverter;
import ru.teamrocket.csrsysteamdesktop.Model.Characteristic;
import ru.teamrocket.csrsysteamdesktop.Model.Offer;
import ru.teamrocket.csrsysteamdesktop.Model.Product;
import ru.teamrocket.csrsysteamdesktop.Model.User;
import ru.teamrocket.csrsysteamdesktop.Service.CharacteristicServiceImpl;
import ru.teamrocket.csrsysteamdesktop.Service.OfferServiceImpl;
import ru.teamrocket.csrsysteamdesktop.Service.UserServiceImpl;
import ru.teamrocket.csrsysteamdesktop.Utils.ErrorValidateAlert;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Alexander on 28.11.2016.
 */
public class SelectionProductsController implements Initializable {

    private RootController rootController;
    private int idSelectUser;

    @FXML
    private ChoiceBox<Offer> choiceBoxOffer;
    @FXML
    private ChoiceBox<Characteristic> choiceBoxCharacteristics;

    private TextField textFieldForNumberAndText;
    private ChoiceBox<String> choiceBoxForListChar;

    @FXML
    private Label offerName;
    @FXML
    private Label offerActivationPrice;
    @FXML
    private Label offerMonthlyPrice;
    @FXML
    private Label offerDescription;


    @FXML
    private AnchorPane anchorPaneForLableCharValue;
    @FXML
    private AnchorPane getAnchorPaneForChoiceBoxCharValue;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.initializeChoiceBox(choiceBoxOffer, new OfferServiceImpl());
    }

    public void setRootController(RootController rootController) {
        this.rootController = rootController;
    }

    public void setIdSelectUser(int idSelectUser) {
        this.idSelectUser = idSelectUser;
    }

    private void initializeChoiceBox(ChoiceBox<Offer> choiceBoxOffer, OfferServiceImpl offerService) {
        choiceBoxOffer.setItems(FXCollections.observableArrayList(offerService.findAll()));

        choiceBoxOffer.setConverter(new StringConverter<Offer>() {
            @Override
            public String toString(Offer object) {
                return object.getName();
            }

            @Override
            public Offer fromString(String string) {
                return null;
            }
        });

        choiceBoxOffer.setOnAction(this::handleOfferOnAction);
    }

    private void initializeChoiceBoxForCharacteristic(
            ChoiceBox<Characteristic> characteristicChoiceBox,
            CharacteristicServiceImpl characteristicService,
            List<Integer> integerList
    ) {
        characteristicChoiceBox.setItems(
                FXCollections.observableArrayList(characteristicService.findByIds(
                        integerList
                )));

        characteristicChoiceBox.setConverter(new StringConverter<Characteristic>() {
            @Override
            public String toString(Characteristic object) {
                return object.getName();
            }

            @Override
            public Characteristic fromString(String string) {
                return null;
            }
        });

        characteristicChoiceBox.setOnAction(this::handleCharacteristicOnAction);
    }


    private void handleCharacteristicOnAction(ActionEvent event) {
        Characteristic selectedCharacteristic = choiceBoxCharacteristics.getSelectionModel().getSelectedItem();

        switch (selectedCharacteristic.getType()) {
            case Text:
                this.setLableInAnchorPane(anchorPaneForLableCharValue, "Value Text");
                this.setInputInnAnchorPane(getAnchorPaneForChoiceBoxCharValue, textFieldForNumberAndText);
                break;
            case Number:
                this.setLableInAnchorPane(anchorPaneForLableCharValue, "Value Number");
                this.setInputInnAnchorPane(getAnchorPaneForChoiceBoxCharValue, textFieldForNumberAndText);
                break;
            case List:
                this.setLableInAnchorPane(anchorPaneForLableCharValue, "Select in List");
                this.setChoiceBoxForValue(getAnchorPaneForChoiceBoxCharValue, this.choiceBoxForListChar, selectedCharacteristic.getValueList());
                break;
            default:
                break;
        }

    }

    private void handleOfferOnAction(ActionEvent event) {
        Offer selectionOffer = choiceBoxOffer.getSelectionModel().getSelectedItem();

        this.offerName.setText(selectionOffer.getName());
        this.offerActivationPrice.setText(selectionOffer.getActivationPrice().toString());
        this.offerMonthlyPrice.setText(selectionOffer.getMonthlyPrice().toString());
        this.offerDescription.setText(selectionOffer.getDescription());

        this.initializeChoiceBoxForCharacteristic(
                this.choiceBoxCharacteristics,
                new CharacteristicServiceImpl(),
                selectionOffer.getCharacteristicsId()
        );
    }

    private void setLableInAnchorPane(AnchorPane anchorPane, String text) {
        anchorPane.getChildren().clear();

        Label label = new Label();
        label.setText(text);

        anchorPane.getChildren().add(label);
    }

    private void setInputInnAnchorPane(AnchorPane anchorPane, TextField textFieldFor) {
        if (textFieldFor == null) {
            textFieldFor = new TextField();
        }
        anchorPane.getChildren().clear();


        anchorPane.getChildren().add(textFieldFor);
    }

    private void setChoiceBoxForValue(AnchorPane anchorPane, ChoiceBox<String> stringChoiceBox, List<String> stringList) {
        if (stringChoiceBox == null) {
            stringChoiceBox = new ChoiceBox<String>();
        }
        anchorPane.getChildren().clear();

        stringChoiceBox.setItems(FXCollections.observableArrayList(stringList));
        anchorPane.getChildren().add(stringChoiceBox);
    }

    @FXML
    private void handleOnSelect(ActionEvent event) {
        Window window = ((Node) event.getTarget()).getScene().getWindow();


        if (inputValidate(window)) {
            Product product = new Product(
                    this.choiceBoxOffer.getSelectionModel().getSelectedItem().getId(),
                    this.choiceBoxCharacteristics.getSelectionModel().getSelectedItem().getId()
            );
            Characteristic currentCharacteristic = this.choiceBoxCharacteristics.getSelectionModel().getSelectedItem();

            switch (currentCharacteristic.getType()) {
                case List:
                    product.setListValue(this.choiceBoxForListChar.getSelectionModel().getSelectedItem());
                    break;
                case Text:
                    product.setTextValue(this.textFieldForNumberAndText.getText());
                    break;
                case Number:
                    product.setNumberValue(Integer.parseInt(this.textFieldForNumberAndText.getText()));
                    break;
                default:
                    break;
            }
            UserServiceImpl userService = new UserServiceImpl();
            User currentUser = userService.findId(this.idSelectUser);
            currentUser.addProduct(product);
            userService.update(currentUser.getId(), currentUser);

            System.out.println("Success Save");
        }

    }

    private boolean inputValidate(Window window) {

        String errorMessage = "";

        if (choiceBoxOffer.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Please Select Offer";
        }

        if (choiceBoxCharacteristics.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Please Select Characteristics";
        } else {
            switch (this.choiceBoxCharacteristics.getSelectionModel().getSelectedItem().getType()) {
                case List:
                    if (this.choiceBoxForListChar != null && this.choiceBoxForListChar.getSelectionModel().getSelectedItem() == null) {
                        errorMessage += "Please select";
                    }
                    break;
                case Text:
                    //TODO-Alexander: написать нормальное описание ошибки
                    if (this.textFieldForNumberAndText.getText().length() != 0) {
                        errorMessage += "Please textFieldForNumberAndText";
                    }
                    break;
                case Number:
                    if (this.textFieldForNumberAndText.getText() == null || this.textFieldForNumberAndText.getText() == null) {
                        errorMessage += "Invalid Number\n";
                    } else {
                        try {
                            Long.parseLong(this.textFieldForNumberAndText.getText());
                        } catch (NumberFormatException e) {
                            errorMessage += "Invalid Number\n";
                        }
                    }
                    break;
                default:
                    break;
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            new ErrorValidateAlert(window, errorMessage).show();
            return false;

        }
    }
}
