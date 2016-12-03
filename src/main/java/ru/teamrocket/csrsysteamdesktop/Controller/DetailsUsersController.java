package ru.teamrocket.csrsysteamdesktop.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import ru.teamrocket.csrsysteamdesktop.Model.Product;
import ru.teamrocket.csrsysteamdesktop.Model.User;
import ru.teamrocket.csrsysteamdesktop.Service.ProductServiceImpl;
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

    public void setUserForUpdate(int idUser, User user) {
        this.user = user;
        this.idUser = idUser;

        fNameLabel.setText(user.getFirstName());
        mNameLabel.setText(user.getMiddleName());
        lNameLabel.setText(user.getLastName());
        phoneNumberLabel.setText(user.getPhoneNumber());
        addressLabel.setText(user.getAddress());

//        products = FXCollections.observableArrayList(user.getProduct());

        productsTableView.setItems(products);
    }

    public void setRootController(RootController rootController) {
        this.rootController = rootController;
    }

    @FXML
    private void handleOnDeleteProduct(){

    }

    @FXML
    private void handleOnAddProduct(){
        this.rootController.handlerOnAddProduct(this.idUser);
    }

    @FXML
    private void handleOnEditUser(){
        this.rootController.handlerOnEditUser(this.idUser, this.user);
    }

    @FXML
    private void handleOnSave(ActionEvent event){
        rootController.handlerOnProducts(this.idUser, this.user);
        rootController.handlerOnUsers(event);
    }

}
