package ru.teamrocket.csrsysteamdesktop.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Window;
import ru.teamrocket.csrsysteamdesktop.Model.Characteristic;
import ru.teamrocket.csrsysteamdesktop.Model.Offer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import ru.teamrocket.csrsysteamdesktop.Service.OfferServiceImpl;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Alexander on 07.11.2016.
 */

public class AddOfferController implements Initializable {
    @FXML
    private TextField nameField;
    @FXML
    private TextField actPriceField;
    @FXML
    private TextField monPriceField;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private TableView<Characteristic> characteristicTableView;
    @FXML
    private TableColumn<Characteristic, String> nameColumn;
    @FXML
    private TableColumn<Characteristic, String> valueColumn;
    private RootController rootController;
    private ObservableList<Characteristic> observableCharacteristic;
    private List<Characteristic> characteristics;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setCharacteristic(List<Characteristic> characteristics) {
        this.characteristics = characteristics;

        observableCharacteristic = FXCollections.observableArrayList(characteristics);

        characteristicTableView.setItems(observableCharacteristic);

//        nameColumn.setCellValueFactory(cellData -> cellData.getValue().composeCharacteristicProperty(cellData.getValue()).nameProperty());
//        valueColumn.setCellValueFactory(cellData -> cellData.getValue().composeCharacteristicProperty(cellData.getValue()).valueProperty());
    }

    public void setRootController(RootController rootController) {
        this.rootController = rootController;
    }

    @FXML
    private void createAction(ActionEvent event) {
        Window window = ((Node) event.getTarget()).getScene().getWindow();

        if (inputValidate(window)) {
            Offer offer = new Offer(
                    nameField.getText(),
                    Integer.parseInt(actPriceField.getText()),
                    Integer.parseInt(monPriceField.getText()),
                    descriptionArea.getText(),
                    characteristics
            );
            new OfferServiceImpl().save(offer);

            rootController.handlerOnOffers(event);
        }
    }

    @FXML
    private void clearAction() {
        nameField.setText(null);
        actPriceField.setText(null);
        monPriceField.setText(null);
        descriptionArea.setText(null);
    }

    private boolean inputValidate(Window window) {
        String errorMessage = "";

        if (nameField.getText() == null) {
            errorMessage += "Invalid name\n";
        }
        if (actPriceField.getText() == null) {
            errorMessage += "Invalid activation price.\n";
        } else {
            try {
                Integer.parseInt(actPriceField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Invalid activation price.\n";
            }
        }

        if (monPriceField.getText() == null) {
            errorMessage += "Invalid monthly price.\n";
        } else {
            try {
                Integer.parseInt(monPriceField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Invalid monthly price.\n";
            }
        }
        if (descriptionArea.getText() == null) {
            errorMessage += "Invalid description.\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {

            //TODO-Alexander: Вынести в отдельный класс
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
    private void addCharacteristicAction(ActionEvent event) {
        rootController.handlerOnAddCharacteristic(characteristics);
    }
}
