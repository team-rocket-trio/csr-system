package ru.teamrocket.csrsysteamdesktop.Controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import ru.teamrocket.csrsysteamdesktop.Model.Characteristic;
import ru.teamrocket.csrsysteamdesktop.Model.Offer;
import ru.teamrocket.csrsysteamdesktop.Service.CharacteristicServiceImpl;
import ru.teamrocket.csrsysteamdesktop.Service.OfferServiceImpl;

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

    @FXML
    private ChoiceBox<Offer> choiceBoxOffer;
    @FXML
    private ChoiceBox<Characteristic> choiceBoxCharacteristics;

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

        switch (selectedCharacteristic.getType()){
            case Text:
                this.setLableInAnchorPane(anchorPaneForLableCharValue, "Value Text");
                this.setLableInAnchorPane(getAnchorPaneForChoiceBoxCharValue, selectedCharacteristic.getValueText());
                break;
            case Number:
                this.setLableInAnchorPane(anchorPaneForLableCharValue, "Value Number");
                this.setInputInnAnchorPane(getAnchorPaneForChoiceBoxCharValue);
                break;
            case List:
                this.setLableInAnchorPane(anchorPaneForLableCharValue, "Select in List");
                this.setChoiceBoxForValue(getAnchorPaneForChoiceBoxCharValue, selectedCharacteristic.getValueList());
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

    private void setInputInnAnchorPane(AnchorPane anchorPane){
        anchorPane.getChildren().clear();

        TextField textField = new TextField();

        anchorPane.getChildren().add(textField);
    }

    private void setChoiceBoxForValue(AnchorPane anchorPane, List<String> stringList){
        anchorPane.getChildren().clear();

        ChoiceBox<String> stringChoiceBox = new ChoiceBox<String>();
        stringChoiceBox.setItems(FXCollections.observableArrayList(stringList));

        anchorPane.getChildren().add(stringChoiceBox);
    }
}
