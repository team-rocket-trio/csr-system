package ru.teamrocket.csrsysteamdesktop.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.teamrocket.csrsysteamdesktop.Main;
import ru.teamrocket.csrsysteamdesktop.Model.Offer;
import ru.teamrocket.csrsysteamdesktop.Model.User;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kate on 13.11.2016.
 */
public class OfferServiceImpl implements OfferService{
    private final Path pathFile = Paths.get(Main.pathData + "/Offers.json");
    private final Type listType = new TypeToken<List<Offer>>() {}.getType();
    private List<Offer> offerList;

    public OfferServiceImpl() {
        String offerFile = readFile();
        if (offerFile == null) {
            offerList = new ArrayList();
        } else {
            offerList = new Gson().fromJson(offerFile, listType);
        }
    }

    @Override
    public String readFile() {
        try {
            return new String(Files.readAllBytes(pathFile));
        } catch (IOException e) {
            System.out.print("Could not read file.");
            return null;
        }
    }

    //TODO-Alexander: Вынести в Util класс
    @Override
    public void writeFile(List<Offer> offerList) {
        File file = new File(pathFile.toString());
        String content = new Gson().toJson(offerList);

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
    public void save(Offer offer) {
        offerList.add(offer);
        writeFile(offerList);
    }

    @Override
    public List<Offer> findAll() {
        return offerList;
    }
}
