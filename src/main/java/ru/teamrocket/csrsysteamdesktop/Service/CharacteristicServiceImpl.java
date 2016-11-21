package ru.teamrocket.csrsysteamdesktop.Service;

import ru.teamrocket.csrsysteamdesktop.Model.Characteristic;

import java.util.List;

/**
 * Created by Kate on 21.11.2016.
 */
public class CharacteristicServiceImpl implements CharacteristicService {
    private List<Characteristic> characteristicList;

    public void save(Characteristic characteristic) {
        characteristicList.add(characteristic);
    }

    public List<Characteristic> findAll() {
        return characteristicList;
    }
}
