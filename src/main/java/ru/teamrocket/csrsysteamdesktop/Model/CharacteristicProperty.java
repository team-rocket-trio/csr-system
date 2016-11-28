package ru.teamrocket.csrsysteamdesktop.Model;

/**
 * Created by Alexander Shreyner on 05.11.2016.
 */

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class CharacteristicProperty {

    private final StringProperty name;
    private final StringProperty activationPrice;
    private final StringProperty monthtyPrice;
    private final StringProperty value;

    public CharacteristicProperty(String name, String value) {
        this.name = new SimpleStringProperty(name);
        this.value = new SimpleStringProperty(value);
        this.activationPrice = new SimpleStringProperty(null);
        this.monthtyPrice = new SimpleStringProperty(null);
    }

    public CharacteristicProperty(Characteristic characteristic) {
        this.name = new SimpleStringProperty(characteristic.getName());
        this.activationPrice = new SimpleStringProperty(characteristic.getActivationPrice());
        this.monthtyPrice = new SimpleStringProperty(characteristic.getMonthtyPrice());
        this.value = new SimpleStringProperty(characteristic.getValue());
    }

    public String getActivationPrice() {
        return activationPrice.get();
    }

    public StringProperty activationPriceProperty() {
        return activationPrice;
    }

    public void setActivationPrice(String activationPrice) {
        this.activationPrice.set(activationPrice);
    }

    public String getMonthtyPrice() {
        return monthtyPrice.get();
    }

    public StringProperty monthtyPriceProperty() {
        return monthtyPrice;
    }

    public void setMonthtyPrice(String monthtyPrice) {
        this.monthtyPrice.set(monthtyPrice);
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

    public String getValue() {
        return value.get();
    }

    public StringProperty valueProperty() {
        return value;
    }

    public void setValue(String value) {
        this.value.set(value);
    }
}
