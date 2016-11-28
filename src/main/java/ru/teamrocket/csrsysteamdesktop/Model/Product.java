package ru.teamrocket.csrsysteamdesktop.Model;

/**
 * Created by Alexander on 07.11.2016.
 */
public class Product {
    private CharacteristicValue characteristicValue;

    public Product(CharacteristicValue characteristicValue){

        this.characteristicValue = characteristicValue;
    }

    public CharacteristicValue getCharacteristicValue() {
        return characteristicValue;
    }

    public void setCharacteristicValue(CharacteristicValue characteristicValue) {
        this.characteristicValue = characteristicValue;
    }
}
