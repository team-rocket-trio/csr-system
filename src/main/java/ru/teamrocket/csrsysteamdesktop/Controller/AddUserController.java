package ru.teamrocket.csrsysteamdesktop.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Window;
import ru.teamrocket.csrsysteamdesktop.Model.Product;
import ru.teamrocket.csrsysteamdesktop.Model.User;
import ru.teamrocket.csrsysteamdesktop.Service.UserServiceImpl;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Alexander on 07.11.2016.
 */
public class AddUserController implements Initializable {
    @FXML
    private TextField firstName;
    @FXML
    private TextField middleName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField address;
    @FXML
    private Text textUser;
    private RootController rootController;
    private int idUser;
    private User user;

    public void setRootController(RootController rootController) {
        this.rootController = rootController;
    }

    //TODO-Alexander: добавить возможность передачи сцены в контроллер.
    //http://code.makery.ch/library/javafx-8-tutorial/ru/part3/#-
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setActionUpdate(int idUser, User user){
        this.idUser = idUser;
        this.user = user;

        firstName.setText(this.user.getFirstName());
        middleName.setText(this.user.getMiddleName());
        lastName.setText(this.user.getLastName());
        phoneNumber.setText(this.user.getPhoneNumber());
        address.setText(this.user.getAddress());

        textUser.setText("Edit User");
    }

    private boolean inputValidate(Window window) {
        String errorMessage = "";

        if (firstName.getText() == null || firstName.getText().length() == 0){
            errorMessage += "No valid First Name\n";
        }
        if (middleName.getText() == null || middleName.getText().length() == 0){
            errorMessage += "No valid Middle Name\n";
        }
        if (lastName.getText() == null || lastName.getText().length() == 0){
            errorMessage += "No valid Last Name\n";
        }

        if(phoneNumber.getText() == null || phoneNumber.getText().length() == 0){
            errorMessage += "No valid PhoneNumber\n";
        }else{
            try{
                Long.parseLong(phoneNumber.getText());
            } catch (NumberFormatException e){
                errorMessage +="No valid PhoneNumber\n";
            }
        }

        if (address.getText() == null || address.getText().length() == 0){
            errorMessage += "No valid address\n";
        }


        if (errorMessage.length() == 0) {
            return true;
        } else {

            //TODO-Alexander: Вынести в отдельный класс
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(window);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();

            return false;
        }
    }

    @FXML
    private void handlerSave(ActionEvent event) {
        Window window = ((Node) event.getTarget()).getScene().getWindow();

        if(inputValidate(window)){

            if(this.user == null){
                User user = new User(
                        firstName.getText(),
                        middleName.getText(),
                        lastName.getText(),
                        phoneNumber.getText(),
                        address.getText()

                );
                new UserServiceImpl().save(user);
                rootController.handlerOnUsers(event);
            }else{
                this.user.setFirstName(this.firstName.getText());
                this.user.setMiddleName(this.middleName.getText());
                this.user.setLastName(this.lastName.getText());
                this.user.setPhoneNumber(this.phoneNumber.getText());
                this.user.setAddress(this.address.getText());

                new UserServiceImpl().update(this.idUser, this.user);
                rootController.handlerOnProducts(this.idUser, this.user);
            }
        }
    }

    @FXML
    private void handlerClear() {
        firstName.setText(null);
        middleName.setText(null);
        lastName.setText(null);
        phoneNumber.setText(null);
        address.setText(null);
    }

}
