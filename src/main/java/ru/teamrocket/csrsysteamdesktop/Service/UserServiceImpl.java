package ru.teamrocket.csrsysteamdesktop.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.teamrocket.csrsysteamdesktop.Main;
import ru.teamrocket.csrsysteamdesktop.Model.User;

import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 16.11.2016.
 */
public class UserServiceImpl extends AbstractSimpleService implements UserService {

    private final Path pathFile = Paths.get(Main.pathData + "/Users.json");
    private final Type listType = new TypeToken<List<User>>() {
    }.getType();
    private List<User> userList;


    public UserServiceImpl() {
        String userFile = readFile();

        if (userFile == null) {
            userList = new ArrayList<User>();
        } else {
            userList = new Gson().fromJson(userFile, listType);
        }

    }

    @Override
    public Path getPathFile() {
        return this.pathFile;
    }

    @Override
    public List<User> getLocalList() {
        return userList;
    }

    @Override
    public void save(User user) {
        user.setId(this.generateId());

        userList.add(user);
        writeFile(userList);
    }

    @Override
    public void delete(int id) {
        userList.remove(this.findId(id));
        writeFile(userList);
    }

    @Override
    public void update(int index, User user) {
        this.delete(index);
        
        userList.add(user);
        writeFile(userList);
    }

    @Override
    public User findId(int id) {
        return userList
                .stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .get();
    }

    @Override
    public List<User> findAll() {
        return userList;
    }
}
