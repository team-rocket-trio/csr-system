package ru.teamrocket.csrsysteamdesktop.Controller;

import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import ru.teamrocket.csrsysteamdesktop.Model.User;
import ru.teamrocket.csrsysteamdesktop.Service.UserServiceImpl;
import java.net.URL;
import java.util.ResourceBundle;

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

        nameColumn.setCellValueFactory(cellData -> cellData.getValue().composUserToProppery().getAllName());
        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().composUserToProppery().phoneNumberProperty());

        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneColumn.setCellFactory(TextFieldTableCell.forTableColumn());

//        phoneColumn.setOnEditCommit((TableColumn.CellEditEvent<User, String> data) -> {
//                data.getTableView()
//                    .getItems()
//                    .get(data.getTablePosition().getRow())
//                    .setPhoneNumber(data.getNewValue());
//                int index = data.getTableView().getSelectionModel().getSelectedIndex();
//                User editedUser = data.getTableView().getItems().get(index);
//                new UserServiceImpl().edit(editedUser, index);
//        });
    }

    @FXML
    private void handleOnCreateUser(ActionEvent event){
        rootController.handlerOnAddUser();
    }

    @FXML
    private void handleOnDeleteUser(){
        if(userTableView.getSelectionModel().getSelectedItem() != null) {
            User selectedUser = userTableView.getSelectionModel().getSelectedItem();
            int index = userTableView.getSelectionModel().getSelectedIndex();
            userTableView.getItems().remove(selectedUser);
            new UserServiceImpl().delete(index);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error deleting user");
            alert.setHeaderText("User is not selected.");
            alert.setContentText("Please select a user.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleOnDetailsUsers(){
        if(userTableView.getSelectionModel().getSelectedItem() != null) {
            rootController.handlerOnProducts(
                    userTableView.getSelectionModel().getSelectedIndex(),
                    userTableView.getSelectionModel().getSelectedItem()
            );
        }
    }

}
