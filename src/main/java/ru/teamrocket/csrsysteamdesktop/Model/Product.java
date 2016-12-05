package ru.teamrocket.csrsysteamdesktop.Model;

import ru.teamrocket.csrsysteamdesktop.Service.CharacteristicServiceImpl;
import ru.teamrocket.csrsysteamdesktop.Service.OfferServiceImpl;

import java.util.List;

/**
 * Created by Alexander on 07.11.2016.
 */
public class Product implements SimpleModel {

    private int id;
    private int offerId;
    private int characteristicsId;

    private String textValue;
    private int numberValue;
    private String listValue;

    public Product(int offerId, int characteristicsId) {
        this.offerId = offerId;
        this.characteristicsId = characteristicsId;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public int getCharacteristicsId() {
        return characteristicsId;
    }

    public void setCharacteristicsId(int characteristicsId) {
        this.characteristicsId = characteristicsId;
    }

    public String getTextValue() {
        return textValue;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }

    public int getNumberValue() {
        return numberValue;
    }

    public void setNumberValue(int numberValue) {
        this.numberValue = numberValue;
    }

    public String getListValue() {
        return listValue;
    }

    public void setListValue(String listValue) {
        this.listValue = listValue;
    }

    public Offer getOffer(){
        return new OfferServiceImpl().findId(this.getOfferId());
    }

    public Characteristic getCharacteristic(){
        return new CharacteristicServiceImpl().findById(this.getCharacteristicsId());
    }
}
