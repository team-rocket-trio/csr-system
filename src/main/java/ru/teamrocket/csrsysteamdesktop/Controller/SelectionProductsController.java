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
import ru.teamrocket.csrsysteamdesktop.Service.ProductServiceImpl;
import ru.teamrocket.csrsysteamdesktop.Service.UserServiceImpl;
import ru.teamrocket.csrsysteamdesktop.Utils.ErrorValidateAlert;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Alexander on 28.11.2016.
 */
public class SelectionProductsController implements Initializable {

    private RootController rootController;
    private int idSelectUser;
    private User selectUser;

    @FXML
    private ChoiceBox<Offer> choiceBoxOffer;
    @FXML
    private ChoiceBox<Characteristic> choiceBoxCharacteristics;

    private TextField textField = new TextField();
    private TextField numberTextField = new TextField();
    private ChoiceBox<String> choiceBoxForListChar = new ChoiceBox<String>();

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

    public void setIdSelectUser(int idSelectUser, User user) {
        this.idSelectUser = idSelectUser;
        this.selectUser = user;
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
                getAnchorPaneForChoiceBoxCharValue.getChildren().clear();
                getAnchorPaneForChoiceBoxCharValue.getChildren().add(textField);
                break;
            case Number:
                this.setLableInAnchorPane(anchorPaneForLableCharValue, "Value Number");
                getAnchorPaneForChoiceBoxCharValue.getChildren().clear();
                getAnchorPaneForChoiceBoxCharValue.getChildren().add(numberTextField);
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


    private void setChoiceBoxForValue(AnchorPane anchorPane, ChoiceBox<String> stringChoiceBox, List<String> stringList) {
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
            UserServiceImpl userService = new UserServiceImpl();
            User currentUser = userService.findId(this.idSelectUser);

            switch (currentCharacteristic.getType()) {
                case List:
                    if(this.choiceBoxForListChar.getSelectionModel().getSelectedItem() != null) {
                        product.setListValue(this.choiceBoxForListChar.getSelectionModel().getSelectedItem());
                    }
                    else {
                        product.setListValue(currentCharacteristic.getValueList().get(0));
                    }
                    break;
                case Text:
                    if(this.textField.getText() != null) {
                        product.setTextValue(this.textField.getText());
                    }
                    else {
                        product.setTextValue(currentCharacteristic.getValueText());
                    }
                    break;
                case Number:
                    if(this.numberTextField.getText() != null &&
                            (Long.parseLong(this.numberTextField.getText()) > currentCharacteristic.getMinValueNumber() &&
                            Long.parseLong(this.numberTextField.getText()) < currentCharacteristic.getMaxValueNumber())) {
                                 product.setNumberValue(Integer.parseInt(this.numberTextField.getText()));
                                 userService.update(currentUser.getId(), currentUser);
                    }
                    else if(this.numberTextField.getText() != null &&
                            (Long.parseLong(this.numberTextField.getText()) < currentCharacteristic.getMinValueNumber() ||
                            Long.parseLong(this.numberTextField.getText()) > currentCharacteristic.getMaxValueNumber())){
                                new ErrorValidateAlert(window, "Your number is out of range.").show();
                    }
                    else if(this.numberTextField.getText() == null){
                        product.setNumberValue(currentCharacteristic.getMinValueNumber());
                    }
                    break;
                default:
                    break;
            }

            int newIdProduct = new ProductServiceImpl().save(product);

            currentUser.addProduct(newIdProduct);
            userService.update(currentUser.getId(), currentUser);

//            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Value is saved.");
//            alert.setHeaderText("Value is saved");
//            String s ="Your value of " + choiceBoxCharacteristics.getSelectionModel().getSelectedItem().getName() + " is saved.";
//            alert.setContentText(s);
//            alert.showAndWait();

            rootController.handlerOnProducts(idSelectUser, selectUser);
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
                    if (this.textField.getText() == null) {
                        errorMessage += "Please textField";
                    }
                    break;
                case Number:
                    if (this.numberTextField.getText() == null) {
                        errorMessage += "Invalid Number\n";
                    }
                    else {
                        try {
                            Long.parseLong(this.numberTextField.getText());
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
