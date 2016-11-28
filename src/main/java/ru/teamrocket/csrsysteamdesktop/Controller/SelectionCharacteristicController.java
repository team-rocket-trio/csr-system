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
import ru.teamrocket.csrsysteamdesktop.Model.User;
import ru.teamrocket.csrsysteamdesktop.Service.CharacteristicServiceImpl;
import ru.teamrocket.csrsysteamdesktop.Service.UserServiceImpl;

import java.net.URL;
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
    private TableColumn<Characteristic, String> monthtyPriceColumn;
    @FXML
    private TableColumn<Characteristic, String> valueColumn;

    private ObservableList<Characteristic> characteristicObservableList;
    private CharacteristicServiceImpl characteristicService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        characteristicService = new CharacteristicServiceImpl();
        characteristicObservableList = FXCollections.observableArrayList(characteristicService.findAll());

        characteristicTableView.setItems(characteristicObservableList);

        nameColumn.setCellValueFactory(cellData -> cellData.getValue().composCharacteristicToProppery().nameProperty());
        activationPriceColumn.setCellValueFactory(cellData -> cellData.getValue().composCharacteristicToProppery().activationPriceProperty());
        monthtyPriceColumn.setCellValueFactory(cellDate -> cellDate.getValue().composCharacteristicToProppery().monthtyPriceProperty());
        valueColumn.setCellValueFactory(cellData -> cellData.getValue().composCharacteristicToProppery().valueProperty());

    }

    @FXML
    private void handleSelect(ActionEvent event){
        if(characteristicTableView.getSelectionModel().getSelectedItem() != null) {
            System.out.println("Selection!!!!");
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
