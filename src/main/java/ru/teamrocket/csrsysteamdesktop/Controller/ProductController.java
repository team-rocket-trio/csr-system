package ru.teamrocket.csrsysteamdesktop.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class ProductController implements Initializable {
    private RootController rootController;
    private ProductServiceImpl productService;
    private User user;
    private ObservableList<Product> products;

    @FXML private TableView<Product> productsTableView;
    @FXML Label fNameLabel;
    @FXML Label mNameLabel;
    @FXML Label lNameLabel;
    @FXML Label phoneNumberLabel;
    @FXML Label addressLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productService = new ProductServiceImpl();

        productsTableView.setItems(products);
    }

    public void setUser(User user) {
        this.user = user;

        fNameLabel.setText(user.getFirstName());
        mNameLabel.setText(user.getMiddleName());
        lNameLabel.setText(user.getLastName());
        phoneNumberLabel.setText(user.getPhoneNumber());
        addressLabel.setText(user.getAddress());

        products = FXCollections.observableArrayList(user.getProducts());
    }

    public void setRootController(RootController rootController) {
        this.rootController = rootController;
    }

    @FXML
    private void handleOnDeleteProduct(){

    }

    @FXML
    private void handleOnAddProduct(){

    }

}
