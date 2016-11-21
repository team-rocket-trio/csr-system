package ru.teamrocket.csrsysteamdesktop.Controller;

import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ru.teamrocket.csrsysteamdesktop.Model.User;
import ru.teamrocket.csrsysteamdesktop.Service.UserServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Function;

/**
 * Created by Alexander on 20.11.2016.
 */
public class UsersController implements Initializable {

    @FXML
    private TableView<User> userTableView;
    @FXML
    private TableColumn<User, String> nameColumn;
    @FXML
    private TableColumn<User, String> phoneColumn;

    @FXML
    private Button buttonAddUser;

    private ObservableList<User> users;
    private UserServiceImpl userService;

    private RootController rootController;

    public void setRootController(RootController rootController) {
        this.rootController = rootController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userService = new UserServiceImpl();
        users = FXCollections.observableArrayList(userService.findAll());

        userTableView.setItems(users);

        nameColumn.setCellValueFactory(cellDate -> cellDate.getValue().composUserToProppery().getAllName());
        phoneColumn.setCellValueFactory(cellDate -> cellDate.getValue().composUserToProppery().phoneNumberProperty());
    }

    @FXML
    private void handleOnCreateUser(ActionEvent event){
        rootController.handlerOnAddUser();
    }

}
