package ru.teamrocket.csrsysteamdesktop.Model;

/**
 * Created by Alexander Shreyner on 05.11.2016.
 */

import javafx.beans.property.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CharacteristicProperty {

    private final StringProperty name;
    private final IntegerProperty activationPrice;
    private final IntegerProperty monthlyPrice;
    private final StringProperty type;
    private final StringProperty valueText;
    private final IntegerProperty minValueNumber;
    private final IntegerProperty maxValueNumber;
    private final SimpleListProperty<String> valueListProperty;

    public CharacteristicProperty(String name) {

        this.name = new SimpleStringProperty(name);
        this.activationPrice = new SimpleIntegerProperty();
        this.monthlyPrice = new SimpleIntegerProperty();
        this.type = null;
        this.valueText = null;
        this.minValueNumber = null;
        this.maxValueNumber = null;
        this.valueListProperty = null;

    }

    public CharacteristicProperty(Characteristic characteristic) {
        this.name = new SimpleStringProperty(characteristic.getName());
        this.activationPrice = new SimpleIntegerProperty(characteristic.getActivationPrice());
        this.monthlyPrice = new SimpleIntegerProperty(characteristic.getMonthlyPrice());
        this.type = new SimpleStringProperty(characteristic.getTypeString());

        this.valueText = new SimpleStringProperty(characteristic.getValueText());
        this.minValueNumber = new SimpleIntegerProperty(characteristic.getMinValueNumber());
        this.maxValueNumber = new SimpleIntegerProperty(characteristic.getMaxValueNumber());
        this.valueListProperty = new SimpleListProperty<String>(FXCollections.observableArrayList(characteristic.getValueList()));
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

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public String getValueText() {
        return valueText.get();
    }

    public StringProperty valueTextProperty() {
        return valueText;
    }

    public void setValueText(String valueText) {
        this.valueText.set(valueText);
    }

    public int getMinValueNumber() {
        return minValueNumber.get();
    }

    public IntegerProperty minValueNumberProperty() {
        return minValueNumber;
    }

    public void setMinValueNumber(int minValueNumber) {
        this.minValueNumber.set(minValueNumber);
    }

    public int getMaxValueNumber() {
        return maxValueNumber.get();
    }

    public IntegerProperty maxValueNumberProperty() {
        return maxValueNumber;
    }

    public void setMaxValueNumber(int maxValueNumber) {
        this.maxValueNumber.set(maxValueNumber);
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

    public ObservableList<String> getValueListProperty() {
        return valueListProperty.get();
    }

    public ListProperty<String> valueListPropertyProperty() {
        return valueListProperty;
    }

    public void setValueListProperty(ObservableList<String> valueListProperty) {
        this.valueListProperty.set(valueListProperty);
    }
}
