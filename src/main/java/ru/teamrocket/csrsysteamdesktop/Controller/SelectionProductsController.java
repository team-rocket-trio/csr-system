package ru.teamrocket.csrsysteamdesktop.Controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import ru.teamrocket.csrsysteamdesktop.Model.Offer;
import ru.teamrocket.csrsysteamdesktop.Service.OfferServiceImpl;
import ru.teamrocket.csrsysteamdesktop.Utils.TypeCharacteristic;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Alexander on 28.11.2016.
 */
public class SelectionProductsController implements Initializable {

    private RootController rootController;

    @FXML
    private ChoiceBox<Offer> choiceBoxOffer;

    @FXML
    private Label offerName;
    @FXML
    private Label offerActivationPrice;
    @FXML
    private Label offerMonthlyPrice;
    @FXML
    private Label offerDescription;


    @FXML
    private AnchorPane anchorPaneForLableCharacteristic;
    @FXML
    private AnchorPane anchorPaneForChoiceBoxCharacteristic;

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
//        choiceBoxOffer.getSelectionModel().selectFirst();

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

        choiceBoxOffer.setOnAction(this::handleOnAction);
    }


    private void handleOnAction(ActionEvent event){
        Offer selectionOffer = choiceBoxOffer.getSelectionModel().getSelectedItem();

        this.offerName.setText(selectionOffer.getName());
        this.offerActivationPrice.setText(selectionOffer.getActivationPrice().toString());
        this.offerMonthlyPrice.setText(selectionOffer.getMonthlyPrice().toString());
        this.offerDescription.setText(selectionOffer.getDescription());


    }

    private void setLableInAnchorPane(AnchorPane anchorPane, String text){
//        anchorPane.getChildren().set();
    }
}
