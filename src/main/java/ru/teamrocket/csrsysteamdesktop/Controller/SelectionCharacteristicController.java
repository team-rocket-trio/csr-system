package ru.teamrocket.csrsysteamdesktop.Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ru.teamrocket.csrsysteamdesktop.Model.Characteristic;
import ru.teamrocket.csrsysteamdesktop.Model.Offer;
import ru.teamrocket.csrsysteamdesktop.Service.CharacteristicServiceImpl;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Alexander on 28.11.2016.
 */
public class SelectionCharacteristicController implements Initializable {

    @FXML
    private TableView<Characteristic> characteristicTableView;
    @FXML
    private TableColumn<Characteristic, String> nameColumn;
    @FXML
    private TableColumn<Characteristic, String> typeColumn;
    @FXML
    private TableColumn<Characteristic, Integer> activationPriceColumn;
    @FXML
    private TableColumn<Characteristic, Integer> monthlyPriceColumn;
    @FXML
    private TableColumn<Characteristic, String> valueColumn;

    private ObservableList<Characteristic> characteristicObservableList;
    private CharacteristicServiceImpl characteristicService;

    private List<Characteristic> characteristicsList;

    private RootController rootController;

    private Offer offer;
    private List<Characteristic> characteristics;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //TODO-Alexander: Тоже вынести!
        characteristicService = new CharacteristicServiceImpl();
        characteristicObservableList = FXCollections.observableArrayList(characteristicService.findAll());

        characteristicTableView.setItems(characteristicObservableList);

        nameColumn.setCellValueFactory(cellData -> cellData.getValue().composeCharacteristicProperty().nameProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().composeCharacteristicProperty().typeProperty());
        activationPriceColumn.setCellValueFactory(cellData -> cellData.getValue().composeCharacteristicProperty().activationPriceProperty().asObject());
        monthlyPriceColumn.setCellValueFactory(cellData -> cellData.getValue().composeCharacteristicProperty().monthlyPriceProperty().asObject());
        valueColumn.setCellValueFactory(cellData -> this.getValueProperty(cellData.getValue()));

    }

    public void setRootController(RootController rootController) {
        this.rootController = rootController;
    }

    public void setCharacteristicsList(List<Characteristic> characteristicsList) {
        this.characteristicsList = characteristicsList;

    }

    //TODO-Alexander: Вынести в Util
    public StringProperty getValueProperty(Characteristic characteristic) {
        switch (characteristic.getType()) {
            case Number:
                return new SimpleStringProperty("min = " + characteristic.getMinValueNumber() + "; max = " + characteristic.getMaxValueNumber());
            case Text:
                return new SimpleStringProperty(characteristic.getValueText());
            case List:
                String string =  String.join(", ", characteristic.getValueList());
                return new SimpleStringProperty(string);
            default:
                return new SimpleStringProperty("");
        }
    }

    @FXML
    private void handleSelect(ActionEvent event) {
        if(offer == null) {
            if (characteristicTableView.getSelectionModel().getSelectedItem() != null) {
                characteristicsList.add(characteristicTableView.getSelectionModel().getSelectedItem());
                rootController.handlerOnAddOffer(this.characteristicsList);
            } else {
                //TODO-Alexander: Внести изменения.
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error: select characteristic");
                alert.setHeaderText("Characteristic is not selected.");
                alert.setContentText("Please select a characteristic.");
                alert.showAndWait();
            }
        }
        else {
            if (characteristicTableView.getSelectionModel().getSelectedItem() != null) {
                characteristics.add(characteristicTableView.getSelectionModel().getSelectedItem());
                rootController.handlerOnBackToOffer(this.offer, this.characteristics);
            } else {
                //TODO-Alexander: Внести изменения.
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error: select characteristic");
                alert.setHeaderText("Characteristic is not selected.");
                alert.setContentText("Please select a characteristic.");
                alert.showAndWait();
            }
        }
    }

    public void setActionUpdate(Offer offer, List<Characteristic> characteristics) {
        this.offer = offer;
        this.characteristics = characteristics;
    }
}
