package ru.teamrocket.csrsysteamdesktop.Service;

import ru.teamrocket.csrsysteamdesktop.Model.User;

/**
 * Created by Alexander on 16.11.2016.
 */
public class UserServiceImpl implements UserService {

    public void save(User user) {
        System.out.println("Save in Gson: " + user.toString());
    }
}
