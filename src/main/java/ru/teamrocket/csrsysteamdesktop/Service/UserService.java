package ru.teamrocket.csrsysteamdesktop.Service;

import ru.teamrocket.csrsysteamdesktop.Model.User;

import java.util.List;

/**
 * Created by Alexander on 16.11.2016.
 */

public interface UserService {

    void save(User user);
    List<User> findAll();

}
