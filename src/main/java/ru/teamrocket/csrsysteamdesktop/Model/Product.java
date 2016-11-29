package ru.teamrocket.csrsysteamdesktop.Model;

import java.util.List;

/**
 * Created by Alexander on 07.11.2016.
 */
public class Product {
    private Offer offer;
    private List<CharacteristicText> characteristicTexts;

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public List<CharacteristicText> getCharacteristicTexts() {
        return characteristicTexts;
    }

    public void setCharacteristicTexts(List<CharacteristicText> characteristicTexts) {
        this.characteristicTexts = characteristicTexts;
    }
}
