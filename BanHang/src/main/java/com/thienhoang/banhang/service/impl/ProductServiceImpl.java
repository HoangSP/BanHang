package com.thienhoang.banhang.service.impl;

import com.thienhoang.banhang.dao.ProductDao;
import com.thienhoang.banhang.model.Product;
import com.thienhoang.banhang.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product add(Product product) {
        productDao.save(product);
        return product;
    }

    @Override
    public void update(Product product) {
        Product currentProduct = get(product.getId());
        if (currentProduct != null){
            currentProduct.setName(product.getName());
            productDao.save(currentProduct);
        }
    }

    @Override
    public void delete(int id) {
        productDao.deleteById(id);
    }

    @Override
    public Product get(int id) {
        return productDao.findById(id).orElse(null);
    }

    @Override
    public List<Product> getAll() {
        return productDao.findAll();
    }

    @Override
    public int count() {
        return productDao.countByName();
    }

    @Override
    public List<Product> search(String name) {
        return productDao.search("%"+name + "%");
    }
}
