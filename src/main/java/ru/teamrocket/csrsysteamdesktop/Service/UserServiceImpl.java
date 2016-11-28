package ru.teamrocket.csrsysteamdesktop.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ru.teamrocket.csrsysteamdesktop.Main;
import ru.teamrocket.csrsysteamdesktop.Model.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 16.11.2016.
 */
public class UserServiceImpl implements UserService {

    private final Path pathFile = Paths.get(Main.pathData + "/Users.json");
    private final Type listType = new TypeToken<List<User>>() {}.getType();
    private List<User> userList;


    public UserServiceImpl() {
        String userFile = readFile();

        if (userFile == null) {
            userList = new ArrayList<User>();
        } else {
            userList = new Gson().fromJson(userFile, listType);
        }

    }

    private String readFile() {
        try {
            return new String(Files.readAllBytes(pathFile));
        } catch (IOException e) {
            System.out.println("Create User.json");
            return null;
        }
    }

    //TODO-Alexander: Вынести в Util класс
    private void writeFile(List<User> userList) {
        File file = new File(pathFile.toString());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String content = gson.toJson(userList);

        try (FileOutputStream fop = new FileOutputStream(file)) {
            if (!file.exists()) {
                file.createNewFile();
            }

            byte[] contentInByte = content.getBytes();

            fop.write(contentInByte);
            fop.flush();
            fop.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void save(User user) {
        userList.add(user);
        writeFile(userList);
    }

    @Override
    public void delete(int index){
        userList.remove(index);
        writeFile(userList);
    }

    public void edit(User user, int index){
        userList.set(index, user);
        writeFile(userList);
    }

    @Override
    public List<User> findAll() {
        return userList;
    }
}
