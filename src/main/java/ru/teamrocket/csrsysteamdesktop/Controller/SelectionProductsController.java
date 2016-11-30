package ru.teamrocket.csrsysteamdesktop.Controller;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Alexander on 28.11.2016.
 */
public class SelectionProductsController implements Initializable {

    private RootController rootController;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setRootController(RootController rootController) {
        this.rootController = rootController;
    }
}
