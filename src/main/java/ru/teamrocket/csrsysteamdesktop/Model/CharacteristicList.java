package ru.teamrocket.csrsysteamdesktop.Model;

import java.util.List;

/**
 * Created by Kate on 29.11.2016.
 */
public class CharacteristicList extends Characteristic{
    private List<String> value;

    public CharacteristicList(String name, List<String> values) {
        super(name, "List");
        this.value = values;
    }

    public List<String> getValues() {
        return value;
    }

    public void setValues(List<String> values) {
        this.value = values;
    }

    public String getValuesString(){
        StringBuilder stringBuilder = new StringBuilder();

        for (String s : value)
            stringBuilder.append(s +", ");

        return stringBuilder.toString();
    }

}
