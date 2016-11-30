package ru.teamrocket.csrsysteamdesktop.Utils;

import javafx.scene.control.Alert;
import javafx.stage.Window;

/**
 * Created by Alexander on 30.11.2016.
 */
public class ErrorValidateAlert {
    private Window window;
    private Alert alert;

    public ErrorValidateAlert(Window window, String errorMessage) {

        this.alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(window);
        alert.setTitle("Invalid Fields");
        alert.setHeaderText("Please correct invalid fields");
        alert.setContentText(errorMessage);
    }

    public void show() {
        alert.showAndWait();
    }
}
