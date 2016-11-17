package ru.teamrocket.csrsysteamdesktop.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Window;
import ru.teamrocket.csrsysteamdesktop.Model.Characteristic;
import ru.teamrocket.csrsysteamdesktop.Model.Offer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import ru.teamrocket.csrsysteamdesktop.Model.OfferProperty;
import ru.teamrocket.csrsysteamdesktop.Model.User;
import ru.teamrocket.csrsysteamdesktop.Service.OfferServiceImpl;
import ru.teamrocket.csrsysteamdesktop.Service.UserServiceImpl;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Alexander on 07.11.2016.
 */
public class AddOfferController implements Initializable {
    // TODO-Kate: 17.11.2016  Перенести все закомментированное в другой контроллер
/*    @FXML
    private TableView<OfferProperty> offersTable;
    @FXML
    private TableColumn<OfferProperty, String> nameCol;
    @FXML
    private TableColumn<OfferProperty, String> descriptionCol;
    @FXML
    private TableColumn<OfferProperty, Integer> activationPriceCol;
    @FXML
    private TableColumn<OfferProperty, Integer> monthlyPriceCol;
    @FXML
    private TableColumn<OfferProperty, List<Characteristic>> characteristicsCol;
    private ObservableList<OfferProperty> offers = FXCollections.observableArrayList();*/
    @FXML
    private TextField nameField;
    @FXML
    private TextField actPriceField;
    @FXML
    private TextField monPriceField;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private TextArea characteristicsArea;

    public void initialize(URL location, ResourceBundle resources) {
        /*initData();

        nameCol.setCellValueFactory(new PropertyValueFactory<OfferProperty, String>("name"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<OfferProperty, String>("description"));
        activationPriceCol.setCellValueFactory(new PropertyValueFactory<OfferProperty, Integer>("activationPrice"));
        monthlyPriceCol.setCellValueFactory(new PropertyValueFactory<OfferProperty, Integer>("monthlyPrice"));

        offersTable.setItems(offers);*/
    }

  /*  private void initData() {
        Offer offer = new Offer();
        OfferProperty offerProperty = offer.composeProperty();
        offers.add(offerProperty);
        OfferServiceImpl offerService = new OfferServiceImpl();
        offerService.toJsonFile(offer);
    }*/
  @FXML
  private void createAction(ActionEvent event) {
      Window window = ((Node) event.getTarget()).getScene().getWindow();

      if(inputValidate(window)){
          Offer offer = new Offer(
                  nameField.getText(),
                  Integer.parseInt(actPriceField.getText()),
                  Integer.parseInt(monPriceField.getText()),
                  descriptionArea.getText()
          );
          new OfferServiceImpl().save(offer);
          clearAction();
      }
  }

    @FXML
    private void clearAction() {
        nameField.setText(null);
        actPriceField.setText(null);
        monPriceField.setText(null);
        descriptionArea.setText(null);
        characteristicsArea.setText(null);
    }

    private boolean inputValidate(Window window) {
        String errorMessage = "";

        if (nameField.getText() == null){
            errorMessage += "Invalid name\n";
        }
        if (actPriceField.getText() == null){
            errorMessage += "Invalid activation price.\n";
        }else{
            try{
                Integer.parseInt(actPriceField.getText());
            } catch (NumberFormatException e){
                errorMessage +="Invalid activation price.\n";
            }
        }

        if (monPriceField.getText() == null){
            errorMessage += "Invalid monthly price.\n";
        }else{
        try{
            Integer.parseInt(monPriceField.getText());
        } catch (NumberFormatException e){
            errorMessage +="Invalid monthly price.\n";
        }
    }
        if(descriptionArea.getText() == null){
            errorMessage += "Invalid description.\n";
        }

        if(characteristicsArea.getText() == null){
            errorMessage += "Invalid characteristics.\n";
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
}
