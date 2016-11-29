package ru.teamrocket.csrsysteamdesktop.Model;

/**
 * Created by Alexander Shreyner on 05.11.2016.
 */

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class CharacteristicProperty {
    private final StringProperty name;
    private final StringProperty activationPrice;
    private final StringProperty monthlyPrice;
    private final StringProperty value;

    public CharacteristicProperty(String name, String value) {
        this.name = new SimpleStringProperty(name);
        this.value = new SimpleStringProperty(value);
        this.activationPrice = new SimpleStringProperty(null);
        this.monthlyPrice = new SimpleStringProperty(null);
    }

    public CharacteristicProperty(CharacteristicText characteristicText) {
        this.name = new SimpleStringProperty(characteristicText.getName());
        this.activationPrice = new SimpleStringProperty(characteristicText.getActivationPrice());
        this.monthlyPrice = new SimpleStringProperty(characteristicText.getMonthlyPrice());
        this.value = new SimpleStringProperty(characteristicText.getValue());
    }

    public CharacteristicProperty(CharacteristicNumber characteristicNumber) {
        this.name = new SimpleStringProperty(characteristicNumber.getName());
        this.activationPrice = new SimpleStringProperty(characteristicNumber.getActivationPrice());
        this.monthlyPrice = new SimpleStringProperty(characteristicNumber.getMonthlyPrice());
        this.value = new SimpleStringProperty(Integer.toString(characteristicNumber.getValue()));
    }

    public CharacteristicProperty(CharacteristicList characteristicList) {
        this.name = new SimpleStringProperty(characteristicList.getName());
        this.activationPrice = new SimpleStringProperty(characteristicList.getActivationPrice());
        this.monthlyPrice = new SimpleStringProperty(characteristicList.getMonthlyPrice());
        this.value = new SimpleStringProperty(characteristicList.getValuesString());
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

    public String getMonthlyPrice() {
        return monthlyPrice.get();
    }

    public StringProperty monthlyPriceProperty() {
        return monthlyPrice;
    }

    public void setMonthlyPrice(String monthlyPrice) {
        this.monthlyPrice.set(monthlyPrice);
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
