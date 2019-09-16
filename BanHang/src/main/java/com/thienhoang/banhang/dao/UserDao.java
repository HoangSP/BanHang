package com.thienhoang.banhang.dao;

import com.thienhoang.banhang.model.User;

import java.util.Collection;
import java.util.List;


public interface UserDao  {

    User add(User user);

    void update(User user);

    void delete(User user);

    User get(int id);

    List<User> search(String fullName, int page, int max);

    long count(String fullname);

    Collection<User> getAll();
}
