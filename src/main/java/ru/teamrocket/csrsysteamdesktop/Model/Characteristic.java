package ru.teamrocket.csrsysteamdesktop.Model;

/**
 * Created by Alexander Shreyner on 07.11.2016.
 */
public class Characteristic implements SimpleModel {

    private int id;
    private String name;
    private String activationPrice;
    private String monthtyPrice;
    private String value;

    public Characteristic(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getActivationPrice() {
        return activationPrice;
    }

    public void setActivationPrice(String activationPrice) {
        this.activationPrice = activationPrice;
    }

    public String getMonthtyPrice() {
        return monthtyPrice;
    }

    public void setMonthtyPrice(String monthtyPrice) {
        this.monthtyPrice = monthtyPrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public CharacteristicProperty composCharacteristicToProppery() {
        return new CharacteristicProperty(this);
    }

    @Override
    public String toString() {
        return name + ": " + value;
    }
}
