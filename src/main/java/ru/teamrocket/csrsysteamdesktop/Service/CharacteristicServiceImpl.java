package ru.teamrocket.csrsysteamdesktop.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ru.teamrocket.csrsysteamdesktop.Main;
import ru.teamrocket.csrsysteamdesktop.Model.Characteristic;

import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alexander on 28.11.2016.
 */
public class CharacteristicServiceImpl extends AbstractSimpleService implements CharacteristicService {

    private final Path pathFile = Paths.get(Main.pathData + "/Characteristics.json");
    private  Type listType = new TypeToken<ArrayList<Characteristic>>(){}.getType();
    private List<Characteristic> characteristicList;

    public CharacteristicServiceImpl(){

        String characteristicFile = readFile();
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
        characteristic.setId(this.generateId());

        characteristicList.add(characteristic);
        writeFile(characteristicList);
    }

    @Override
    public List<Characteristic> findAll() {
        return characteristicList;
    }

    @Override
    public Characteristic findId(int id) {
        return characteristicList
                .stream()
                .filter(characteristicItem -> characteristicItem.getId() == id)
                .findFirst()
                .get();
    }

    @Override
    public List<Characteristic> findByIds(List<Integer> ids) {
        return characteristicList.stream()
                .filter(characteristic -> ids.contains(characteristic.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(int id) {
        characteristicList.remove(this.findId(id));

        writeFile(characteristicList);
    }

    @Override
    public void update(int id, Characteristic characteristic) {

        this.delete(id);
        characteristicList.add(characteristic);

        writeFile(characteristicList);
    }
}
