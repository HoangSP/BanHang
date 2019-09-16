package com.thienhoang.banhang.controller;

import com.thienhoang.banhang.model.Product;
import com.thienhoang.banhang.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/count")
    public int count(){
       return productService.count();
    }

    @GetMapping("/product")
    public List<Product> getAllNews() {
        return productService.getAll();
    }

    @GetMapping("/product/{id}")
    public Product get(@RequestParam("id") int id){
        return productService.get(id);
    }

    @PostMapping("/product")
    public Product add(@Valid @RequestBody Product product){
        return productService.add(product);
    }

    @PostMapping("/product/search")
    public List<Product> search(@RequestParam("name") String name){
        return productService.search(name);
    }

    @DeleteMapping("/product/{id}")
    public void delete(@RequestParam("id") int id){
        productService.delete(id);
    }

    @PutMapping("/product")
    public void update(@RequestBody Product product){
        productService.update(product);
    }
}
