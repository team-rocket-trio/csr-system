package ru.teamrocket.csrsysteamdesktop.Model;

/**
 * Created by Kate on 29.11.2016.
 */
public class CharacteristicNumber extends Characteristic{
    private int value;

    public CharacteristicNumber(String name, int value) {
        super(name, "Number");
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
