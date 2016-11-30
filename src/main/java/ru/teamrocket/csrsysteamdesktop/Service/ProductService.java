package ru.teamrocket.csrsysteamdesktop.Service;

import ru.teamrocket.csrsysteamdesktop.Model.Product;

import java.util.List;

/**
 * Created by Kate on 28.11.2016.
 */
public interface ProductService{

    List<Product> findAll();
    Product findId(int id);
    void save(Product product);
    void delete(int id);
    void update(int id, Product product);

}
