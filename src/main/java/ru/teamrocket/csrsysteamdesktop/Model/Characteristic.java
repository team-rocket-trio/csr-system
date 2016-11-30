package ru.teamrocket.csrsysteamdesktop.Model;

import ru.teamrocket.csrsysteamdesktop.Utils.TypeCharacteristic;

import java.util.List;

/**
 * Created by Alexander Shreyner on 07.11.2016.
 */

public class Characteristic implements SimpleModel {

    private int id;
    private String name;
    private int activationPrice;
    private int monthlyPrice;
    private TypeCharacteristic type;
    private int valueNumber;
    private String valueText;
    private List<String> valueList;

    public Characteristic(String name, TypeCharacteristic type) {
        this.name = name;
        this.type = type;
    }

    public Characteristic(
            String name,
            int activationPrice,
            int monthlyPrice,
            TypeCharacteristic type
    ) {
        this.name = name;
        this.activationPrice = activationPrice;
        this.monthlyPrice = monthlyPrice;
        this.type = type;
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

    public void setName(String name) {
        this.name = name;
    }

    public int getActivationPrice() {
        return activationPrice;
    }

    public void setActivationPrice(int activationPrice) {
        this.activationPrice = activationPrice;
    }

    public int getMonthlyPrice() {
        return monthlyPrice;
    }

    public void setMonthlyPrice(int monthlyPrice) {
        this.monthlyPrice = monthlyPrice;
    }

    public int getValueNumber() {
        return valueNumber;
    }

    public void setValueNumber(int valueNumber) {
        this.valueNumber = valueNumber;
    }

    public TypeCharacteristic getType() {
        return type;
    }

    public void setType(TypeCharacteristic type) {
        this.type = type;
    }

    public String getValueText() {
        return valueText;
    }

    public void setValueText(String valueText) {
        this.valueText = valueText;
    }

    public List<String> getValueList() {
        return valueList;
    }

    public void setValueList(List<String> valueList) {
        this.valueList = valueList;
    }

    public CharacteristicProperty composeCharacteristicProperty() {
        return new CharacteristicProperty(this);
    }

}
