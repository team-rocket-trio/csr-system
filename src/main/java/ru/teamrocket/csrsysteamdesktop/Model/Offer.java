package ru.teamrocket.csrsysteamdesktop.Model;

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
    private List<Integer> characteristicsId;

    public Offer(
            String name,
            Integer activationPrice,
            Integer monthlyPrice,
            String description,
            List<Integer> characteristicsId
    ) {
        this.name = name;
        this.activationPrice = activationPrice;
        this.monthlyPrice = monthlyPrice;
        this.description = description;
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

    public OfferProperty composeOfferProperty() {
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

    public List<Integer> getCharacteristicsId() {
        return characteristicsId;
    }

    public void setCharacteristicsId(List<Integer> characteristicsId) {
        this.characteristicsId = characteristicsId;
    }
}
