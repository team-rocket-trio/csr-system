package main.java.ru.teamrocket.csrSysteamDesktop.Model;

/**
 * Created by Alexander Shreyner on 05.11.2016.
 */

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Characteristic {

    private final StringProperty name;
    private final StringProperty value;

    public Characteristic() {

        this.name = new SimpleStringProperty("nameCharacteristic");
        this.value = new SimpleStringProperty("Value1");

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

    public String getValue() {
        return value.get();
    }

    public StringProperty valueProperty() {
        return value;
    }

    public void setValue(String value) {
        this.value.set(value);
    }
}
