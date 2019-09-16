package com.thienhoang.banhang.service.impl;

import com.thienhoang.banhang.dao.UserDao;
import com.thienhoang.banhang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void add(UserService user) {

    }

    @Override
    public void update(UserService user) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public UserService get(int id) {
        return null;
    }

    @Override
    public List<UserService> search(String name) {
        return null;
    }
}
