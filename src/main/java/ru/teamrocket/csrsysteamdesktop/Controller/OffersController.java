package ru.teamrocket.csrsysteamdesktop.Controller;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ru.teamrocket.csrsysteamdesktop.Model.Characteristic;
import ru.teamrocket.csrsysteamdesktop.Model.Offer;
import ru.teamrocket.csrsysteamdesktop.Model.OfferProperty;
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
    @FXML
    private TableView<Offer> offersTableView;
    @FXML
    private TableColumn<Offer, String> nameColumn;
    @FXML
    private TableColumn<Offer, String> descriptionColumn;
    @FXML
    private TableColumn<Offer, Integer> actPriceColumn;
    @FXML
    private TableColumn<Offer, Integer> monPriceColumn;
    @FXML
    private TableColumn<Offer, Characteristic> characteristicsColumn;

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

        nameColumn.setCellValueFactory(cellData -> cellData.getValue().composeOfferProperty().nameProperty());
        actPriceColumn.setCellValueFactory(cellData -> cellData.getValue().composeOfferProperty().activationPriceProperty().asObject());
        monPriceColumn.setCellValueFactory(cellData -> cellData.getValue().composeOfferProperty().monthlyPriceProperty().asObject());
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().composeOfferProperty().descriptionProperty());
    }

    @FXML
    private void handleOnCreateOffer(ActionEvent event){
        rootController.handlerOnAddOffer(new ArrayList<Characteristic>());
    }
}
