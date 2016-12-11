package ru.teamrocket.csrsysteamdesktop.Controller;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Window;
import ru.teamrocket.csrsysteamdesktop.Model.Characteristic;
import ru.teamrocket.csrsysteamdesktop.Model.Offer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import ru.teamrocket.csrsysteamdesktop.Service.CharacteristicServiceImpl;
import ru.teamrocket.csrsysteamdesktop.Service.OfferServiceImpl;
import ru.teamrocket.csrsysteamdesktop.Utils.ErrorValidateAlert;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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
    @FXML
    private Label textOffer;

    private RootController rootController;

    private ObservableList<Characteristic> observableCharacteristic;

    private List<Characteristic> characteristics;

    private Offer offer;
    private int idOffer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setCharacteristic(List<Characteristic> characteristics) {
        this.characteristics = characteristics;

        observableCharacteristic = FXCollections.observableArrayList(characteristics);

        characteristicTableView.setItems(observableCharacteristic);

        nameColumn.setCellValueFactory(cellData -> cellData.getValue().composeCharacteristicProperty().nameProperty());

        valueColumn.setCellValueFactory((TableColumn.CellDataFeatures<Characteristic, String> data) -> {
            String s = "";
            switch (data.getValue().getType()){
                case Text:
                    s = data.getValue().getValueText();
                    break;
                case Number:
                    s = "min = " + data.getValue().getMinValueNumber() + "; max = " + data.getValue().getMaxValueNumber();
                    break;
                case List:
                    s = String.join(", ", data.getValue().getValueList());
                    break;
            }
            return new ReadOnlyStringWrapper(s);
        });
    }

    public void setRootController(RootController rootController) {
        this.rootController = rootController;
    }

    @FXML
    private void createAction(ActionEvent event) {
        Window window = ((Node) event.getTarget()).getScene().getWindow();

        if (inputValidate(window)) {
            if(this.offer == null) {
                Offer offer = new Offer(
                        nameField.getText(),
                        Integer.parseInt(actPriceField.getText()),
                        Integer.parseInt(monPriceField.getText()),
                        descriptionArea.getText(),
                        characteristics.stream().map(characteristic -> characteristic.getId()).collect(Collectors.toList())
                );
                new OfferServiceImpl().save(offer);
                rootController.handlerOnOffers(event);
            }
            else {
                this.offer.setName(nameField.getText());
                this.offer.setActivationPrice(Integer.valueOf(actPriceField.getText()));
                this.offer.setMonthlyPrice(Integer.valueOf(monPriceField.getText()));
                this.offer.setDescription(descriptionArea.getText());
                List<Integer> characteristicsId = new ArrayList<>();
                for(Characteristic c : characteristicTableView.getItems()){
                    characteristicsId.add(c.getId());
                }
                this.offer.setCharacteristicsId(characteristicsId);
                new OfferServiceImpl().update(idOffer, offer);
                rootController.handlerOnOffers(event);
            }
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
            new ErrorValidateAlert(window, errorMessage).show();

            return false;
        }
    }

    @FXML
    private void addCharacteristicAction(ActionEvent event) {
        if(this.offer == null)
            rootController.handlerOnAddCharacteristic(characteristics);
        else
            rootController.handlerOnSelectCharacteristic(offer, characteristics);
    }

    public void removeCharacteristicAction(ActionEvent event) {
        if(characteristicTableView.getSelectionModel().getSelectedItem() != null){
            characteristicTableView.getItems().remove(characteristicTableView.getSelectionModel().getSelectedItem());
        }
    }

    public void setActionUpdate(int idOffer, Offer offer) {
        this.idOffer = idOffer;
        this.offer = offer;

        nameField.setText(offer.getName());
        actPriceField.setText(Integer.toString(offer.getActivationPrice()));
        monPriceField.setText(Integer.toString(offer.getMonthlyPrice()));
        descriptionArea.setText(offer.getDescription());

        List<Integer> characteristicsId = offer.getCharacteristicsId();
        CharacteristicServiceImpl service = new CharacteristicServiceImpl();
        List<Characteristic> characteristicsList = new ArrayList<Characteristic>();
        for (int id: characteristicsId) {
            Characteristic c = service.findById(id);
            characteristicsList.add(c);
        }
        observableCharacteristic = FXCollections.observableArrayList(characteristicsList);
        characteristicTableView.setItems(observableCharacteristic);
        this.characteristics = characteristicsList;

        nameColumn.setCellValueFactory(cellData -> cellData.getValue().composeCharacteristicProperty().nameProperty());

        valueColumn.setCellValueFactory((TableColumn.CellDataFeatures<Characteristic, String> data) -> {
            String s = "";
            switch (data.getValue().getType()){
                case Text:
                    s = data.getValue().getValueText();
                    break;
                case Number:
                    s = "min = " + data.getValue().getMinValueNumber() + "; max = " + data.getValue().getMaxValueNumber();
                    break;
                case List:
                    s = String.join(", ", data.getValue().getValueList());
                    break;
            }
            return new ReadOnlyStringWrapper(s);
        });

        textOffer.setText("Edit Offer");
    }

    public void setActionSelected(int idOffer, Offer offer) {
        this.idOffer = idOffer;
        this.offer = offer;

        nameField.setText(offer.getName());
        actPriceField.setText(Integer.toString(offer.getActivationPrice()));
        monPriceField.setText(Integer.toString(offer.getMonthlyPrice()));
        descriptionArea.setText(offer.getDescription());

        observableCharacteristic = FXCollections.observableArrayList(characteristics);
        characteristicTableView.setItems(observableCharacteristic);

        nameColumn.setCellValueFactory(cellData -> cellData.getValue().composeCharacteristicProperty().nameProperty());

        valueColumn.setCellValueFactory((TableColumn.CellDataFeatures<Characteristic, String> data) -> {
            String s = "";
            switch (data.getValue().getType()){
                case Text:
                    s = data.getValue().getValueText();
                    break;
                case Number:
                    s = "min = " + data.getValue().getMinValueNumber() + "; max = " + data.getValue().getMaxValueNumber();
                    break;
                case List:
                    s = String.join(", ", data.getValue().getValueList());
                    break;
            }
            return new ReadOnlyStringWrapper(s);
        });

        textOffer.setText("Edit Offer");
    }
}
