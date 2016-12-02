package ru.teamrocket.csrsysteamdesktop.Service;

import ru.teamrocket.csrsysteamdesktop.Model.Characteristic;
import java.util.List;

/**
 * Created by Alexander on 28.11.2016.
 */

public interface CharacteristicService {

    List<Characteristic> findAll();
    Characteristic findById(int id);
    void save(Characteristic characteristic);
    void delete(int id);
    void update(int id, Characteristic characteristic);
}
