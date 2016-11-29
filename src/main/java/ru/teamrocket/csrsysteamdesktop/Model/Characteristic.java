package ru.teamrocket.csrsysteamdesktop.Model;

/**
 * Created by Alexander Shreyner on 07.11.2016.
 */

public class Characteristic implements SimpleModel {
    private int id;
    private String name;
    private String activationPrice;
    private String monthlyPrice;
    private String type;

    public Characteristic(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public Characteristic(String name, String activationPrice, String monthlyPrice, String type) {
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

    public String getActivationPrice() {
        return activationPrice;
    }

    public void setActivationPrice(String activationPrice) {
        this.activationPrice = activationPrice;
    }

    public String getMonthlyPrice() {
        return monthlyPrice;
    }

    public void setMonthlyPrice(String monthlyPrice) {
        this.monthlyPrice = monthlyPrice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CharacteristicProperty composeCharacteristicProperty(Characteristic c){
        CharacteristicProperty characteristicProperty = null;
        if (c instanceof CharacteristicText){
            CharacteristicText characteristicText = (CharacteristicText) c;
            characteristicProperty = new CharacteristicProperty(characteristicText);
        }
        else
            if(c instanceof CharacteristicNumber){
                CharacteristicNumber characteristicNumber = (CharacteristicNumber) c;
                characteristicProperty = new CharacteristicProperty(characteristicNumber);
            }
            else
                if(c instanceof CharacteristicList){
                    CharacteristicList characteristicList = (CharacteristicList) c;
                    characteristicProperty = new CharacteristicProperty(characteristicList);
                }
        return characteristicProperty;
    }

}
