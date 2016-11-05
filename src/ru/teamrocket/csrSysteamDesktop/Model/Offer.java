/**
 * Created by Alexander Shreyner on 05.11.2016.
 */

package ru.teamrocket.csrSysteamDesktop.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;

public class Offer {

    private final StringProperty name;
    private final IntegerProperty activationPrice;
    private final IntegerProperty monthlyPrice;
    private final StringProperty description;
    private final BooleanProperty status;
    private final ListProperty<Characteristic> characteristics;


    public Offer() {
        this.name = new SimpleStringProperty("productName");
        this.activationPrice = new SimpleIntegerProperty(123);
        this.monthlyPrice = new SimpleIntegerProperty(1234);
        this.description = new SimpleStringProperty("Description");
        this.status = new SimpleBooleanProperty(true);

        this.characteristics = new SimpleListProperty<Characteristic>();
        this.characteristics.add(new Characteristic());


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
}
