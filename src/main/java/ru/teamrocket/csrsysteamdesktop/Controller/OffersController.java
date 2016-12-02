package ru.teamrocket.csrsysteamdesktop.Controller;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ru.teamrocket.csrsysteamdesktop.Model.Characteristic;
import ru.teamrocket.csrsysteamdesktop.Model.Offer;
import ru.teamrocket.csrsysteamdesktop.Service.CharacteristicServiceImpl;
import ru.teamrocket.csrsysteamdesktop.Service.OfferServiceImpl;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Kate on 20.11.2016.
 */
public class OffersController implements Initializable{
    @FXML private TableView<Offer> offersTableView;
    @FXML private TableColumn<Offer, String> nameColumn;
    @FXML private TableColumn<Offer, String> descriptionColumn;
    @FXML private TableColumn<Offer, Integer> actPriceColumn;
    @FXML private TableColumn<Offer, Integer> monPriceColumn;
    @FXML private TableColumn<Offer, String> characteristicsColumn;
    private ObservableList<Offer> offers;
    private OfferServiceImpl offersService;
    private RootController rootController;

    public void setRootController(RootController rootController) {
        this.rootController = rootController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        offersService = new OfferServiceImpl();
        offers = FXCollections.observableArrayList(offersService.findAll());

        offersTableView.setItems(offers);

        nameColumn.setCellValueFactory(data -> data.getValue().composeOfferProperty().nameProperty());
        actPriceColumn.setCellValueFactory(data -> data.getValue().composeOfferProperty().activationPriceProperty().asObject());
        monPriceColumn.setCellValueFactory(data -> data.getValue().composeOfferProperty().monthlyPriceProperty().asObject());
        descriptionColumn.setCellValueFactory(data -> data.getValue().composeOfferProperty().descriptionProperty());

        characteristicsColumn.setCellValueFactory((TableColumn.CellDataFeatures<Offer, String> data) -> {
            List<Integer> characteristics = data.getValue().getCharacteristicsId();
            CharacteristicServiceImpl service = new CharacteristicServiceImpl();
            List<Characteristic> list = new ArrayList<Characteristic>();
            for (int id: characteristics) {
                Characteristic c = service.findById(id);
                list.add(c);
            }
            String c = list
                       .stream()
                       .map(item -> item.toString())
                        .reduce("", (acc, item) -> acc + item + "\n");
            return new ReadOnlyStringWrapper(c);
        });
    }



    @FXML
    private void handleOnCreateOffer(){
        rootController.handlerOnAddOffer(new ArrayList<Characteristic>());
    }

    @FXML
    private void handleOnEditOffer(){
        if(offersTableView.getSelectionModel().getSelectedItem() != null){
            rootController.handlerOnEditOffer(
                    offersTableView.getSelectionModel().getSelectedItem().getId(),
                    offersTableView.getSelectionModel().getSelectedItem()
            );
        }
    }

    @FXML
    private void handleOnDeleteOffer(){
        if(offersTableView.getSelectionModel().getSelectedItem() != null) {
            Offer selectedOffer = offersTableView.getSelectionModel().getSelectedItem();
            int index = offersTableView.getSelectionModel().getSelectedIndex();
            offersTableView.getItems().remove(selectedOffer);
            new OfferServiceImpl().delete(index);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error deleting offer");
            alert.setHeaderText("Offer is not selected.");
            alert.setContentText("Please select an offer.");
            alert.showAndWait();
        }
    }
}
