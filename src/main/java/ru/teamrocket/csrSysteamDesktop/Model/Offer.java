package ru.teamrocket.csrSysteamDesktop.Model;

import java.util.List;

/**
 * Created by Alexander Shreyner on 07.11.2016.
 */
public class Offer {

    private String name;
    private Integer activationPrice;
    private Integer monthlyPrice;
    private String description;
    private Boolean status;
    private List<Characteristic> characteristics;

    public Offer(
            String name,
            Integer activationPrice,
            Integer monthlyPrice,
            String description,
            Boolean status,
            List<Characteristic> characteristics
    ) {
        this.name = name;
        this.activationPrice = activationPrice;
        this.monthlyPrice = monthlyPrice;
        this.description = description;
        this.status = status;
        this.characteristics = characteristics;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<Characteristic> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<Characteristic> characteristics) {
        this.characteristics = characteristics;
    }
}
