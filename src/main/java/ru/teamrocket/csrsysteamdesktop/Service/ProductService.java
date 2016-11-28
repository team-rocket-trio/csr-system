package ru.teamrocket.csrsysteamdesktop.Service;

import ru.teamrocket.csrsysteamdesktop.Model.Product;

import java.util.List;

/**
 * Created by Kate on 28.11.2016.
 */
public interface ProductService{
    List<Product> findAll();
    void save(Product product);
    void delete(int index);
}
