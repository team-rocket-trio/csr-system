package ru.teamrocket.csrsysteamdesktop.Model;

import java.util.List;

/**
 * Created by Alexander on 07.11.2016.
 */
public class Product implements SimpleModel {

    private int id;
    private int characteristicsId;
    private int characteristicsValueId;
    private int maxValueNumber;
    private int characteristicsValueIdList;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCharacteristicsId() {
        return characteristicsId;
    }

    public void setCharacteristicsId(int characteristicsId) {
        this.characteristicsId = characteristicsId;
    }

    public int getCharacteristicsValueId() {
        return characteristicsValueId;
    }

    public void setCharacteristicsValueId(int characteristicsValueId) {
        this.characteristicsValueId = characteristicsValueId;
    }
}