package com.epam.by.service;

import com.epam.by.beans.User;
import com.epam.by.dao.UserDao;
import com.epam.by.dao.daoImpl.UserDaoImpl;

import java.util.List;

public class UserService {

    private UserDao userDao = new UserDaoImpl();

    public User authenticate(String login, String password) {
        List<User> users = userDao.getAll();
        User authenticatedUser = null;
        for (User user : users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                authenticatedUser = user;
            }
        }
        return authenticatedUser;
    }

    public User register(String login, String password) {
        User user = new User(login, password);
        return userDao.create(user);
    }

    public void deleteUser(int id) {
        userDao.delete(id);
    }
}
