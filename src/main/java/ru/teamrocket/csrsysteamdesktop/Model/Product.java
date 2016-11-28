package ru.teamrocket.csrsysteamdesktop.Model;

import java.util.List;

/**
 * Created by Alexander on 07.11.2016.
 */
public class Product {
    private Offer offer;
    private List<Characteristic> characteristics;


    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public List<Characteristic> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<Characteristic> characteristics) {
        this.characteristics = characteristics;
    }
}
