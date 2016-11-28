package ru.teamrocket.csrsysteamdesktop.Service;

import ru.teamrocket.csrsysteamdesktop.Model.User;

import java.util.List;

/**
 * Created by Alexander on 16.11.2016.
 */

public interface UserService {
    List<User> findAll();
    User findId(int id);
    void save(User user);
    void delete(int index);
    void update(int index, User user);
}
