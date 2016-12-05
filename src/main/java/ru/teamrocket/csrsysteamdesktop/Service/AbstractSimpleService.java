package ru.teamrocket.csrsysteamdesktop.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.teamrocket.csrsysteamdesktop.Model.Characteristic;
import ru.teamrocket.csrsysteamdesktop.Model.SimpleModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Alexander on 28.11.2016.
 */
public abstract class AbstractSimpleService {

    public abstract Path getPathFile();

    public abstract <T extends SimpleModel> List<T> getLocalList();

    public int generateId() {
        if (getLocalList().size() == 0) {
            return 0;
        } else {
            int maxId = Collections.max(getLocalList(), new Comparator<SimpleModel>() {
                @Override
                public int compare(SimpleModel o1, SimpleModel o2) {
                    if (o1.getId() > o2.getId()) {
                        return 1;
                    } else if (o1.getId() > o2.getId()) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            }).getId();

            return maxId + 1;
        }
    }

    public String readFile() {
        try {
            return new String(Files.readAllBytes(getPathFile()));
        } catch (IOException e) {
            System.out.print("Could not read file.");
            return null;
        }
    }

    public void writeFile(List list) {
        File file = new File(getPathFile().toString());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String content = gson.toJson(list);

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

}
