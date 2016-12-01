package ru.teamrocket.csrsysteamdesktop.Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ru.teamrocket.csrsysteamdesktop.Model.Characteristic;
import ru.teamrocket.csrsysteamdesktop.Service.CharacteristicServiceImpl;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Alexander on 28.11.2016.
 */
public class CharacteristicsController implements Initializable {
    private RootController rootController;

    @FXML
    private TableView<Characteristic> characteristicTableView;
    @FXML
    private TableColumn<Characteristic, String> nameColumn;
    @FXML
    private TableColumn<Characteristic, Integer> activationPriceColumn;
    @FXML
    private TableColumn<Characteristic, Integer> monthlyPriceColumn;
    @FXML
    private TableColumn<Characteristic, String> valueColumn;
    @FXML
    private TableColumn<Characteristic, String> typeColumn;

    private ObservableList<Characteristic> characteristicObservableList;
    private CharacteristicServiceImpl characteristicService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        characteristicService = new CharacteristicServiceImpl();
        characteristicObservableList = FXCollections.observableArrayList(characteristicService.findAll());

        characteristicTableView.setItems(characteristicObservableList);

        nameColumn.setCellValueFactory(cellData -> cellData.getValue().composeCharacteristicProperty().nameProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().composeCharacteristicProperty().typeProperty());
        activationPriceColumn.setCellValueFactory(cellData -> cellData.getValue().composeCharacteristicProperty().activationPriceProperty().asObject());
        monthlyPriceColumn.setCellValueFactory(cellData -> cellData.getValue().composeCharacteristicProperty().monthlyPriceProperty().asObject());

        valueColumn.setCellValueFactory(cellData -> this.getValueProppery(cellData.getValue()));

    }

    //TODO-Alexander: Вынести в Util
    public StringProperty getValueProppery(Characteristic characteristic) {
        switch (characteristic.getType()) {
            case Number:

                return new SimpleStringProperty("min: " + Integer.toString(characteristic.getMinValueNumber()) +
                                                "; max: " + Integer.toString(characteristic.getMaxValueNumber()));
            case Text:
                return new SimpleStringProperty(characteristic.getValueText());
            case List:
                String string = characteristic.getValueList()
                        .stream()
                        .reduce("", (s, s2) -> s + s2 + "; ");
                return new SimpleStringProperty(string);
            default:
                return new SimpleStringProperty("");
        }
    }

    public void setRootController(RootController rootController) {
        this.rootController = rootController;
    }

    @FXML
    private void handleDelete(ActionEvent event) {
        if (characteristicTableView.getSelectionModel().getSelectedItem() != null) {
            int idCharacteristic = characteristicTableView.getSelectionModel().getSelectedItem().getId();

            characteristicTableView.getItems().remove(idCharacteristic);

            new CharacteristicServiceImpl().delete(idCharacteristic);
        }
    }

    @FXML
    private void handleEdit(ActionEvent event) {
        //TODO-Alexander: Добавить редактирование
       if (characteristicTableView.getSelectionModel().getSelectedItem() != null) {
           rootController.handlerOnEditCharacteristic(
                   characteristicTableView.getSelectionModel().getSelectedItem().getId(),
                   characteristicTableView.getSelectionModel().getSelectedItem());
       }
    }

    @FXML
    private void handleOnCreate(ActionEvent event) {
        rootController.handlerOnAddGlobalCharacteristics();
    }

}
