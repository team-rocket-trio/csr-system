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

    private int idUser;
    private User user;

    public ProductServiceImpl(int idUser) {
        this.idUser = idUser;

        this.user = new UserServiceImpl().findId(this.idUser);
    }

    @Override
    public List<Product> findAll() {
        return this.user.getProducts();
    }

    @Override
    public void save(Product product) {
        this.user.setProduct(product);
        new UserServiceImpl().update(this.idUser, this.user);
    }

    @Override
    public void delete(int idUser) {
        this.user.deleteProduct(idUser);
    }
}
