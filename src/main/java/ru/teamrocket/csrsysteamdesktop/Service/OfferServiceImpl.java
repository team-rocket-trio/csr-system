package ru.teamrocket.csrsysteamdesktop.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import ru.teamrocket.csrsysteamdesktop.Main;
import ru.teamrocket.csrsysteamdesktop.Model.Offer;
import ru.teamrocket.csrsysteamdesktop.Model.SimpleModel;
import ru.teamrocket.csrsysteamdesktop.Model.User;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Kate on 13.11.2016.
 */
public class OfferServiceImpl extends AbstractSimpleService implements OfferService{
    private final Path pathFile = Paths.get(Main.pathData + "/Offers.json");
    private final Type listType = new TypeToken<List<Offer>>() {}.getType();
    private List<Offer> offerList;

    public OfferServiceImpl() {
        String offerFile = readFile();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        if (offerFile == null) {
            offerList = new ArrayList();
        } else {
            offerList = gson.fromJson(offerFile, listType);
        }
    }

    @Override
    public Path getPathFile() {
        return this.pathFile;
    }

    @Override
    public List<Offer> getLocalList() {
        return this.offerList;
    }

    @Override
    public void save(Offer offer) {
        offerList.add(offer);
        writeFile(offerList);
    }

    @Override
    public void delete(int index) {
        offerList.remove(index);
        writeFile(offerList);
    }

    @Override
    public List<Offer> findAll() {
        return offerList;
    }
}
