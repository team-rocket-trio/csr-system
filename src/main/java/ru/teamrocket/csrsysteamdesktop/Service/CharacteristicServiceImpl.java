package ru.teamrocket.csrsysteamdesktop.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ru.teamrocket.csrsysteamdesktop.Main;
import ru.teamrocket.csrsysteamdesktop.Model.Characteristic;
import ru.teamrocket.csrsysteamdesktop.Model.SimpleModel;

import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 28.11.2016.
 */
public class CharacteristicServiceImpl extends AbstractSimpleService implements CharacteristicService {

    private final Path pathFile = Paths.get(Main.pathData + "/Characteristics.json");
    private final Type listType = new TypeToken<List<Characteristic>>() {
    }.getType();
    private List<Characteristic> characteristicList;

    public CharacteristicServiceImpl() {

        String characteristicFile = this.readFile();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        if (characteristicFile == null) {
            characteristicList = new ArrayList();
        } else {
            characteristicList = gson.fromJson(characteristicFile, listType);
        }
    }

    @Override
    public Path getPathFile() {
        return this.pathFile;
    }

    @Override
    public List<Characteristic> getLocalList() {
        return this.characteristicList;
    }

    @Override
    public void save(Characteristic characteristic) {
        characteristic.setId(characteristicList.size());

        characteristicList.add(characteristic);
        writeFile(characteristicList);
    }

    @Override
    public List<Characteristic> findAll() {
        return characteristicList;
    }

    @Override
    public void delete(int id) {
        characteristicList.remove(id);
        writeFile(characteristicList);
    }

    @Override
    public void update(int id, Characteristic characteristic) {
        characteristicList.set(id, characteristic);
        writeFile(characteristicList);
    }
}
