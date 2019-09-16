package com.thienhoang.banhang.service;

import com.thienhoang.banhang.model.Product;

import java.util.List;

public interface ProductService {

    Product add(Product product);
    void update(Product product);
    void delete(int id);
    Product get(int id);
    List<Product> getAll();
    int count();
    List<Product> search(String name);
}
