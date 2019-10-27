package com.epam.by.dao.daoImpl;

import com.epam.by.dao.UserDao;
import com.epam.by.service.FileService;
import com.epam.by.constant.Constant;
import com.epam.by.beans.User;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private FileService fileService = new FileService();

    @Override
    public User getById(Integer id) {
        User user = null;
        List<User> userList = getAll();
        for (User usr : userList) {
            if (usr.getId() == id) {
                user = usr;
            }
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        final List<String> userValues = fileService.getFileContent(Constant.USER_FILE);
        final List<User> list = new ArrayList<>();
        for (int i = 0; i < userValues.size(); i = i + 3) {
            User user = new User();
            user.setId(Integer.parseInt(userValues.get(i)));
            user.setLogin(userValues.get(i + 1));
            user.setPassword(userValues.get(i + 2));
            list.add(user);
        }
        return list;
    }

    @Override
    public User create(User user) {
        List<User> userList = getAll();
        FileOutputStream outputStream;
        Integer userId = getLastId() + 1;
        try {
            outputStream = new FileOutputStream(Constant.USER_FILE);
            StringBuilder outputString = new StringBuilder();
            for (User usr : userList) {
                outputString.append(usr.toString()).append("\n");
            }
            user.setId(userId);
            outputString.append(user.toString());
            outputStream.write(outputString.toString().getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public Integer getLastId() {
        Integer lastId;
        List<String> fileContent = fileService.getFileContent(Constant.USER_FILE);
        if (!fileContent.isEmpty()) {
            lastId = Integer.valueOf(fileContent.get(fileContent.size()-3));
        } else {
            lastId = 0;
        }
        return lastId;
    }

    @Override
    public void delete(Integer id) {
        List<User> list = getAll();
        FileOutputStream outputStream;
        try {
            outputStream = new FileOutputStream(Constant.USER_FILE);
            StringBuilder outputString = new StringBuilder();
            list.remove(getById(id));
            for (User user : list) {
                outputString.append(user.toString()).append("\n");
            }
            outputStream.write(outputString.toString().getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
