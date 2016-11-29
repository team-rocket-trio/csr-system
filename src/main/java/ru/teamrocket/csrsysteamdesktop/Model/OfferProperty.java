/**
 * Created by Alexander Shreyner on 05.11.2016.
 */

package ru.teamrocket.csrsysteamdesktop.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.List;

public class OfferProperty {

    private final StringProperty name;
    private final IntegerProperty activationPrice;
    private final IntegerProperty monthlyPrice;
    private final StringProperty description;
    private final List<Characteristic> characteristic;

    public OfferProperty(
            String name,
            Integer activationPrice,
            Integer monthlyPrice,
            String description,
            List<Characteristic> characteristic
    ) {
        this.name = new SimpleStringProperty(name);
        this.activationPrice = new SimpleIntegerProperty(activationPrice);
        this.monthlyPrice = new SimpleIntegerProperty(monthlyPrice);
        this.description = new SimpleStringProperty(description);
        this.characteristic = new ArrayList<>(characteristic);
    }

    public OfferProperty(Offer offer) {
        this.name = new SimpleStringProperty(offer.getName());
        this.activationPrice = new SimpleIntegerProperty(offer.getActivationPrice());
        this.monthlyPrice = new SimpleIntegerProperty(offer.getMonthlyPrice());
        this.description = new SimpleStringProperty(offer.getDescription());
        this.characteristic = new ArrayList<>(FXCollections.observableArrayList(offer.getCharacteristic()));
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

    public List<Characteristic> getCharacteristic() {return characteristic;}

}
