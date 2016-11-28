package ru.teamrocket.csrsysteamdesktop.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ru.teamrocket.csrsysteamdesktop.Main;
import ru.teamrocket.csrsysteamdesktop.Model.Product;
import ru.teamrocket.csrsysteamdesktop.Model.User;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kate on 28.11.2016.
 */
public class ProductServiceImpl implements ProductService{
    private final Path pathFile = Paths.get(Main.pathData + "/Users.json");
    private final Type listType = new TypeToken<List<Product>>() {}.getType();
    private List<Product> productsList;
    private User user;

    public ProductServiceImpl(){
        String userFile = readFile();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        if (userFile == null) {
            productsList = new ArrayList();
        } else {
            productsList = gson.fromJson(userFile, listType);
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

    @Override
    public List<Product> findAll() {
        return productsList;
    }

    @Override
    public void save(Product product) {

    }

    @Override
    public void delete(int index) {

    }
}
