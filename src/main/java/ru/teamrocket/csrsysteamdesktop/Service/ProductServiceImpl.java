package ru.teamrocket.csrsysteamdesktop.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.teamrocket.csrsysteamdesktop.Main;
import ru.teamrocket.csrsysteamdesktop.Model.Product;
import ru.teamrocket.csrsysteamdesktop.Model.SimpleModel;
import ru.teamrocket.csrsysteamdesktop.Model.User;

import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kate on 28.11.2016.
 */
public class ProductServiceImpl extends AbstractSimpleService implements ProductService {


    private final Path pathFile = Paths.get(Main.pathData + "/Products.json");
    private final Type listType = new TypeToken<List<Product>>() {
    }.getType();
    private List<Product> productList;

    public ProductServiceImpl() {
        String productFile = readFile();

        if (productFile == null) {
            productList = new ArrayList<Product>();
        } else {
            productList = new Gson().fromJson(productFile, listType);
        }
    }

    @Override
    public Path getPathFile() {
        return pathFile;
    }

    @Override
    public List<Product> getLocalList() {
        return this.productList;
    }

    @Override
    public List<Product> findAll() {
        return this.productList;
    }

    @Override
    public Product findId(int id) {
        return productList
                .stream()
                .filter(product1 -> product1.getId() == id)
                .findFirst()
                .get();
    }

    @Override
    public void save(Product product) {
        product.setId(this.generateId());

        productList.add(product);
        writeFile(productList);
    }

    @Override
    public void delete(int id) {
        productList.remove(this.findId(id));
        writeFile(productList);
    }

    @Override
    public void update(int id, Product product) {
        this.delete(id);

        productList.add(product);
        writeFile(productList);
    }
}
