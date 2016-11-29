package ru.teamrocket.csrsysteamdesktop.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ru.teamrocket.csrsysteamdesktop.Main;
import ru.teamrocket.csrsysteamdesktop.Model.Characteristic;
import ru.teamrocket.csrsysteamdesktop.Model.CharacteristicList;
import ru.teamrocket.csrsysteamdesktop.Model.CharacteristicNumber;
import ru.teamrocket.csrsysteamdesktop.Model.CharacteristicText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 28.11.2016.
 */
public class CharacteristicServiceImpl implements CharacteristicService {
    private final Path pathFile = Paths.get(Main.pathData + "/Characteristics.json");
    private  Type listType = new TypeToken<ArrayList<Characteristic>>(){}.getType();
    private List<Characteristic> characteristicList;

    public CharacteristicServiceImpl(){
        String characteristicFile = readFile();
        RuntimeTypeAdapterFactory<Characteristic> runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory
                .of(Characteristic.class, "type")
                .registerSubtype(CharacteristicText.class, "Text")
                .registerSubtype(CharacteristicNumber.class, "Number")
                .registerSubtype(CharacteristicList.class, "List");
        Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(runtimeTypeAdapterFactory).create();

        if (characteristicFile == null) {
            characteristicList = new ArrayList();
        } else {
            characteristicList = gson.fromJson(characteristicFile, listType);
        }
    }

    public String readFile() {
        try {
            return new String(Files.readAllBytes(pathFile));
        } catch (IOException e) {
            System.out.print("Could not read file.");
            return null;
        }
    }

    //TODO-Alexander: Вынести в Util класс
    public void writeFile(List<Characteristic> characteristicList) {
        File file = new File(pathFile.toString());
        RuntimeTypeAdapterFactory<Characteristic> runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory
                .of(Characteristic.class, "type")
                .registerSubtype(CharacteristicText.class, "Text")
                .registerSubtype(CharacteristicNumber.class, "Number")
                .registerSubtype(CharacteristicList.class, "List");
        Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(runtimeTypeAdapterFactory).create();
        //Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String content = gson.toJson(characteristicList);

        try (FileOutputStream fop = new FileOutputStream(file)) {
            if (!file.exists()) {
                file.createNewFile();
            }

            byte[] contentInByte = content.getBytes();

            fop.write(contentInByte);
            fop.flush();
            fop.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Characteristic characteristic) {
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
