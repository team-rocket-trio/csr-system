package ru.teamrocket.csrsysteamdesktop.Model;

/**
 * Created by Alexander Shreyner on 07.11.2016.
 */
public class CharacteristicText extends Characteristic{
    private String value;

    public CharacteristicText(String name, String value) {
        super(name, "Text");
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
