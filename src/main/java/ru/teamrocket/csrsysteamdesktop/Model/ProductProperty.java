package ru.teamrocket.csrsysteamdesktop.Model;

import javafx.beans.property.IntegerProperty;

/**
 * Created by Kate on 06.11.2016.
 */
public class ProductProperty {


    private final IntegerProperty characteristicsId;
    private final IntegerProperty characteristicsValueId;

    public ProductProperty(IntegerProperty characteristicsId, IntegerProperty characteristicsValueId) {
        this.characteristicsId = characteristicsId;
        this.characteristicsValueId = characteristicsValueId;
    }

    public int getCharacteristicsId() {
        return characteristicsId.get();
    }

    public IntegerProperty characteristicsIdProperty() {
        return characteristicsId;
    }

    public void setCharacteristicsId(int characteristicsId) {
        this.characteristicsId.set(characteristicsId);
    }

    public int getCharacteristicsValueId() {
        return characteristicsValueId.get();
    }

    public IntegerProperty characteristicsValueIdProperty() {
        return characteristicsValueId;
    }

    public void setCharacteristicsValueId(int characteristicsValueId) {
        this.characteristicsValueId.set(characteristicsValueId);
    }
}
