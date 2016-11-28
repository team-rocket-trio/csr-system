package ru.teamrocket.csrsysteamdesktop.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander Shreyner on 07.11.2016.
 */
public class Offer implements SimpleModel {

    private int id;
    private String name;
    private Integer activationPrice;
    private Integer monthlyPrice;
    private String description;
    private List<Characteristic> characteristics;

    public Offer(){
        this.name = "Offer";
        this.activationPrice = 50;
        this.monthlyPrice = 100;
        this.description = "A brick to build the wall";
        this.characteristics = new ArrayList<Characteristic>();
        this.characteristics.add(new Characteristic("Color","brick-red"));
    }

    public Offer(
            String name,
            Integer activationPrice,
            Integer monthlyPrice,
            String description,
            List<Characteristic> characteristics
    ) {
        this.name = name;
        this.activationPrice = activationPrice;
        this.monthlyPrice = monthlyPrice;
        this.description = description;
        this.characteristics = characteristics;
    }

    @Override
    public int getId() {
        return this.id;
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

    public List<Characteristic> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<Characteristic> characteristics) {
        this.characteristics = characteristics;
    }
}
