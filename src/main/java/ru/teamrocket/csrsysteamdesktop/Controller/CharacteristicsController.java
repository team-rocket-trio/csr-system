package ru.teamrocket.csrsysteamdesktop.Controller;

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

    public void setRootController(RootController rootController) {
        this.rootController = rootController;
    }

    @FXML
    private void handleDelete(ActionEvent event){
        if(characteristicTableView.getSelectionModel().getSelectedItem() != null) {
            int idCharacteristic = characteristicTableView.getSelectionModel().getSelectedIndex();
            characteristicTableView.getItems().remove(idCharacteristic);
            new CharacteristicServiceImpl().delete(idCharacteristic);
        }
    }

    @FXML
    private void handleEdit(ActionEvent event){
        System.out.println("handleEdit");
    }

    @FXML
    private void handleOnCreate(ActionEvent event){
        rootController.handlerOnAddGlobalCharacteristics();
    }

}
