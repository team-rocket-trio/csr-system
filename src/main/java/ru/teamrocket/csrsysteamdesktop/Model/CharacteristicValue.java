package ru.teamrocket.csrsysteamdesktop.Model;

/**
 * Created by Kate on 28.11.2016.
 */
public class CharacteristicValue {
    private Characteristic characteristic;

    public CharacteristicValue(Characteristic characteristic){
        this.characteristic = characteristic;
    }

    public Characteristic getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(Characteristic characteristic) {
        this.characteristic = characteristic;
    }
}
