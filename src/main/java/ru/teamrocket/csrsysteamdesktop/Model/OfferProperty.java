/**
 * Created by Alexander Shreyner on 05.11.2016.
 */

package ru.teamrocket.csrsysteamdesktop.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

public class OfferProperty {

    private final StringProperty name;
    private final IntegerProperty activationPrice;
    private final IntegerProperty monthlyPrice;
    private final StringProperty description;
    private final BooleanProperty status;
    private final ListProperty<Characteristic> characteristics;

    public OfferProperty(
            String name,
            Integer activationPrice,
            Integer monthlyPrice,
            String description,
            Boolean status,
            ObservableList<Characteristic> characteristics
    ) {
        this.name = new SimpleStringProperty(name);
        this.activationPrice = new SimpleIntegerProperty(activationPrice);
        this.monthlyPrice = new SimpleIntegerProperty(monthlyPrice);
        this.description = new SimpleStringProperty(description);
        this.status = new SimpleBooleanProperty(status);
        this.characteristics = new SimpleListProperty<Characteristic>(characteristics);
    }

    public Offer composeOffer(){
        String name = getName();
        Integer activationPrice = getActivationPrice();
        Integer monthlyPrice = getMonthlyPrice();
        String description = getDescription();
        Boolean status = isStatus();
        ObservableList<Characteristic> characteristics = FXCollections.observableArrayList(getCharacteristics());

        return new Offer(name, activationPrice, monthlyPrice, description, status, characteristics);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getActivationPrice() {
        return activationPrice.get();
    }

    public IntegerProperty activationPriceProperty() {
        return activationPrice;
    }

    public void setActivationPrice(int activationPrice) {
        this.activationPrice.set(activationPrice);
    }

    public int getMonthlyPrice() {
        return monthlyPrice.get();
    }

    public IntegerProperty monthlyPriceProperty() {
        return monthlyPrice;
    }

    public void setMonthlyPrice(int monthlyPrice) {
        this.monthlyPrice.set(monthlyPrice);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public boolean isStatus() {
        return status.get();
    }

    public BooleanProperty statusProperty() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status.set(status);
    }

    public ObservableList<Characteristic> getCharacteristics() {return characteristics;}
}
