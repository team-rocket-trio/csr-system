package ru.teamrocket.csrsysteamdesktop.Controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ru.teamrocket.csrsysteamdesktop.Model.Offer;
import ru.teamrocket.csrsysteamdesktop.Model.Product;
import ru.teamrocket.csrsysteamdesktop.Model.User;
import ru.teamrocket.csrsysteamdesktop.Service.CharacteristicServiceImpl;
import ru.teamrocket.csrsysteamdesktop.Service.OfferServiceImpl;
import ru.teamrocket.csrsysteamdesktop.Service.ProductServiceImpl;
import ru.teamrocket.csrsysteamdesktop.Service.UserServiceImpl;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Kate on 28.11.2016.
 */
public class DetailsUsersController implements Initializable {

    private User user;
    private int idUser;

    private RootController rootController;
    private ProductServiceImpl productService;
    private ObservableList<Product> products;

    @FXML
    private TableView<Product> productsTableView;
    @FXML
    private TableColumn<Product, String> nameColumn;
    @FXML
    private TableColumn<Product, Integer> actPriceColumn;
    @FXML
    private TableColumn<Product, Integer> monPriceColumn;
    @FXML
    private TableColumn<Product, String> characteristicsNameColumn;
    @FXML
    private TableColumn<Product, String> selectCharacteristicsColumn;

    @FXML
    private Label fNameLabel;
    @FXML
    private Label mNameLabel;
    @FXML
    private Label lNameLabel;
    @FXML
    private Label phoneNumberLabel;
    @FXML
    private Label addressLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setUserForUpdate(int idUser) {
        this.idUser = idUser;
        this.user = new UserServiceImpl().findId(idUser);

        fNameLabel.setText(user.getFirstName());
        mNameLabel.setText(user.getMiddleName());
        lNameLabel.setText(user.getLastName());
        phoneNumberLabel.setText(user.getPhoneNumber());
        addressLabel.setText(user.getAddress());

        products = FXCollections.observableArrayList(user.getProducts());

        productsTableView.setItems(products);

        nameColumn.setCellValueFactory((TableColumn.CellDataFeatures<Product, String> data) -> {
            OfferServiceImpl offerService = new OfferServiceImpl();
            Offer offer = offerService.findId(data.getValue().getId());
            return new ReadOnlyStringWrapper(offer.getName());
        });
        actPriceColumn.setCellValueFactory((TableColumn.CellDataFeatures<Product, Integer> data) -> {
            OfferServiceImpl offerService = new OfferServiceImpl();
            Offer offer = offerService.findId(data.getValue().getId());
            return new ReadOnlyObjectWrapper<>(offer.getActivationPrice());
        });
        monPriceColumn.setCellValueFactory((TableColumn.CellDataFeatures<Product, Integer> data) -> {
            OfferServiceImpl offerService = new OfferServiceImpl();
            Offer offer = offerService.findId(data.getValue().getId());
            return new ReadOnlyObjectWrapper<>(offer.getMonthlyPrice());
        });

        characteristicsNameColumn.setCellValueFactory(cellData ->
                new CharacteristicServiceImpl().findById(cellData.getValue().getCharacteristicsId()).composeCharacteristicProperty().nameProperty()
        );

        selectCharacteristicsColumn.setCellValueFactory((TableColumn.CellDataFeatures<Product, String> data) -> {
//            OfferServiceImpl offerService = new OfferServiceImpl();
//            Offer offer = offerService.findId(data.getValue().getId());
//            List<Integer> characteristics = offer.getCharacteristicsId();
            if (data.getValue().getNumberValue() != 0) {
                return new SimpleStringProperty(Integer.toString(data.getValue().getNumberValue()));
            }
            if (data.getValue().getTextValue() != null) {
                return new SimpleStringProperty(data.getValue().getTextValue());
            }

            if (data.getValue().getListValue() != null) {
                return new SimpleStringProperty(data.getValue().getListValue());
            }

            return new SimpleStringProperty("No Select");
        });
    }

    public void setRootController(RootController rootController) {
        this.rootController = rootController;
    }

    @FXML
    private void handleOnDeleteProduct() {

    }

    @FXML
    private void handleOnAddProduct() {
        this.rootController.handlerOnAddProduct(this.idUser, this.user);
    }

    @FXML
    private void handleOnEditUser() {
        this.rootController.handlerOnEditUser(this.idUser, this.user);
    }

    @FXML
    private void handleOnSave(ActionEvent event) {
        rootController.handlerOnProducts(this.idUser, this.user);
        rootController.handlerOnUsers(event);
    }

}
