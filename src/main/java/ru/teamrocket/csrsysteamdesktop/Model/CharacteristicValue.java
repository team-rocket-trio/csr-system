package ru.teamrocket.csrsysteamdesktop.Model;

/**
 * Created by Kate on 28.11.2016.
 */
public class CharacteristicValue {
    private CharacteristicText characteristicText;

    public CharacteristicValue(CharacteristicText characteristicText){
        this.characteristicText = characteristicText;
    }

    public CharacteristicText getCharacteristicText() {
        return characteristicText;
    }

    public void setCharacteristicText(CharacteristicText characteristicText) {
        this.characteristicText = characteristicText;
    }
}
