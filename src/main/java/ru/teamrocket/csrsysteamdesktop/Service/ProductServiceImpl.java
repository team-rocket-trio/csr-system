package ru.teamrocket.csrsysteamdesktop.Service;

import ru.teamrocket.csrsysteamdesktop.Model.Product;
import ru.teamrocket.csrsysteamdesktop.Model.User;

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
    public Product findAll() {
        return this.user.getProduct();
    }

    @Override
    public void save(Product product) {
        this.user.setProduct(product);
        new UserServiceImpl().update(this.idUser, this.user);
    }

}
