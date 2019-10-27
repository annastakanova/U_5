package com.epam.by.dao;

import com.epam.by.beans.User;

import java.util.List;

public interface UserDao{

    User getById(Integer id);

    List<User> getAll();

    User create(User user);

    Integer getLastId();

    void delete(Integer id);
}
