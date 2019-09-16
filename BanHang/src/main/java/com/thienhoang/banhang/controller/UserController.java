package com.thienhoang.banhang.controller;

import com.thienhoang.banhang.dao.UserDao;
import com.thienhoang.banhang.exception.ResourceNotFoundException;
import com.thienhoang.banhang.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/users")
    public Collection<User> getAllNews() {
        return userDao.getAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getNewById(@PathVariable(value = "id") int id)
            throws ResourceNotFoundException {
        User user = userDao.get(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/users/search") /// ?name=abc
    public List<User> search(
            @RequestParam("fullname") String fullname,
            @RequestParam("page") int page,
            @RequestParam("max") int max) {
        return userDao.search(fullname,page, max);
    }

    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) {
        return userDao.add(user);
    }


    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") int id,
                                          @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
        User user = userDao.get(id);
        user.setId(userDetails.getId());
        user.setFullName(userDetails.getFullName());
        user.setEmail(userDetails.getEmail());
        user.setPassWord(userDetails.getPassWord());
        user.setCreatedAt(userDetails.getCreatedAt());
        user.setUpdatedAt(userDetails.getUpdatedAt());
        final User updatedUser = userDao.add(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/users/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") int id)
            throws ResourceNotFoundException {
        User user = userDao.get(id);
        userDao.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
