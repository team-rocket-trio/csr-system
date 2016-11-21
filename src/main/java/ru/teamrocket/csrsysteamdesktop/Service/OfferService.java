package ru.teamrocket.csrsysteamdesktop.Service;

import ru.teamrocket.csrsysteamdesktop.Model.Offer;

import java.util.List;

/**
 * Created by Kate on 13.11.2016.
 */

public interface OfferService {
    List<Offer> findAll();
    void save(Offer user);
}
