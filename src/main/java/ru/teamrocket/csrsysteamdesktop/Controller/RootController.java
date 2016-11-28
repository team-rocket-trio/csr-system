package ru.teamrocket.csrsysteamdesktop.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import ru.teamrocket.csrsysteamdesktop.Model.Characteristic;
import ru.teamrocket.csrsysteamdesktop.Model.User;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RootController implements Initializable {

    @FXML
    private BorderPane borderPane;

    //TODO-Alexander: Снять ID borderPane с главного элемента.
    //TODO-Alexander: Добавить Default BorderPane;
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("location = [" + location + "], resources = [" + resources + "]");
    }


    public void handlerOnAddUser() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/template/AddUser.fxml"));

            Parent parent = loader.load();
            borderPane.setCenter(parent);

            AddUserController addUserController = loader.getController();
            addUserController.setRootController(this);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void handlerOnUsers(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/template/Users.fxml"));

            Parent parent = loader.load();
            borderPane.setCenter(parent);

            UsersController usersController = loader.getController();
            usersController.setRootController(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handlerOnOffers(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/template/Offers.fxml"));

            Parent parent = loader.load();
            borderPane.setCenter(parent);

            OffersController offersController = loader.getController();
            offersController.setRootController(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handlerOnAddOffer(List<Characteristic> characteristicList) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/template/AddOffer.fxml"));

            Parent parent = loader.load();
            borderPane.setCenter(parent);

            AddOfferController addOfferController = loader.getController();
            addOfferController.setRootController(this);
            addOfferController.setCharacteristics(characteristicList);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handlerOnAddCharacteristic(List<Characteristic> characteristicList) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/template/AddCharacteristic.fxml"));

            Parent parent = loader.load();
            borderPane.setCenter(parent);

            AddCharacteristicController addCharacteristicController = loader.getController();
            addCharacteristicController.setRootController(this);
            addCharacteristicController.setCharacteristicsList(characteristicList);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handlerOnProducts(User user){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/template/Products.fxml"));

            Parent parent = loader.load();
            borderPane.setCenter(parent);

            ProductController productController = loader.getController();
            productController.setRootController(this);
            productController.setUser(user);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setScene(String nameResurce) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(nameResurce));
            borderPane.setCenter(parent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
