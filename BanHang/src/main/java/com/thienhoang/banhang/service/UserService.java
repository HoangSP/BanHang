package com.thienhoang.banhang.service;

import java.util.List;

public interface UserService {

    void add(UserService user);

    void update(UserService user);

    void delete(int id);

    UserService get(int id);

    List<UserService> search(String name);
}
