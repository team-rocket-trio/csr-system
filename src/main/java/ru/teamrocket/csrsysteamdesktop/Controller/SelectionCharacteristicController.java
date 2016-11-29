package ru.teamrocket.csrsysteamdesktop.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ru.teamrocket.csrsysteamdesktop.Model.Characteristic;
import ru.teamrocket.csrsysteamdesktop.Model.CharacteristicText;
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
    private TableColumn<Characteristic, String> activationPriceColumn;
    @FXML
    private TableColumn<Characteristic, String> monthlyPriceColumn;
    @FXML
    private TableColumn<Characteristic, String> valueColumn;

    private ObservableList<Characteristic> characteristicObservableList;
    private CharacteristicServiceImpl characteristicService;

    private List<Characteristic> characteristicsList;

    private RootController rootController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        characteristicService = new CharacteristicServiceImpl();
        characteristicObservableList = FXCollections.observableArrayList(characteristicService.findAll());

        characteristicTableView.setItems(characteristicObservableList);
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().composeCharacteristicProperty(cellData.getValue()).nameProperty());
        activationPriceColumn.setCellValueFactory(cellData -> cellData.getValue().composeCharacteristicProperty(cellData.getValue()).activationPriceProperty());
        monthlyPriceColumn.setCellValueFactory(cellData -> cellData.getValue().composeCharacteristicProperty(cellData.getValue()).monthlyPriceProperty());
        valueColumn.setCellValueFactory(cellData -> cellData.getValue().composeCharacteristicProperty(cellData.getValue()).valueProperty());
    }

    public void setRootController(RootController rootController) {
        this.rootController = rootController;
    }

    public void setCharacteristicsList(List<Characteristic> characteristicsList) {
        this.characteristicsList = characteristicsList;
    }

    @FXML
    private void handleSelect(ActionEvent event){
        if(characteristicTableView.getSelectionModel().getSelectedItem() != null) {
            characteristicsList.add(characteristicTableView.getSelectionModel().getSelectedItem());
            rootController.handlerOnAddOffer(this.characteristicsList);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error deleting user");
            alert.setHeaderText("User is not selected.");
            alert.setContentText("Please select a user.");
            alert.showAndWait();
        }
    }

}
