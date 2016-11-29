package ru.teamrocket.csrsysteamdesktop.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander Shreyner on 07.11.2016.
 */
public class Offer {

    private String name;
    private Integer activationPrice;
    private Integer monthlyPrice;
    private String description;
    private List<Characteristic> characteristic;

    public Offer(
            String name,
            Integer activationPrice,
            Integer monthlyPrice,
            String description,
            List<Characteristic> characteristic
    ) {
        this.name = name;
        this.activationPrice = activationPrice;
        this.monthlyPrice = monthlyPrice;
        this.description = description;
        this.characteristic = characteristic;
    }

    public OfferProperty composeOfferProperty(){
        return new OfferProperty(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getActivationPrice() {
        return activationPrice;
    }

    public void setActivationPrice(Integer activationPrice) {
        this.activationPrice = activationPrice;
    }

    public Integer getMonthlyPrice() {
        return monthlyPrice;
    }

    public void setMonthlyPrice(Integer monthlyPrice) {
        this.monthlyPrice = monthlyPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Characteristic> getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(List<Characteristic> characteristic) {
        this.characteristic = characteristic;
    }
}
