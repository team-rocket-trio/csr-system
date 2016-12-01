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
        valueColumn.setCellValueFactory(cellData -> this.getValueProppery(cellData.getValue()));
    }

    public void setRootController(RootController rootController) {
        this.rootController = rootController;
    }

    public void setCharacteristicsList(List<Characteristic> characteristicsList) {
        this.characteristicsList = characteristicsList;
    }

    //TODO-Alexander: Вынести в Util
    public StringProperty getValueProppery(Characteristic characteristic) {
        switch (characteristic.getType()) {
            case Number:
                return new SimpleStringProperty(Integer.toString(characteristic.getMinValueNumber()));
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

    @FXML
    private void handleSelect(ActionEvent event) {
        if (characteristicTableView.getSelectionModel().getSelectedItem() != null) {
            characteristicsList.add(characteristicTableView.getSelectionModel().getSelectedItem());
            rootController.handlerOnAddOffer(this.characteristicsList);
        } else {
            //TODO-Alexander: Внести изменения.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error select user");
            alert.setHeaderText("User is not selected.");
            alert.setContentText("Please select a *.");
            alert.showAndWait();
        }
    }

}
