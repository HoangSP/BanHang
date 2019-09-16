package com.thienhoang.banhang.controller;


import com.thienhoang.banhang.dao.NewDao;
import com.thienhoang.banhang.exception.ResourceNotFoundException;
import com.thienhoang.banhang.model.New;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class NewController {

    @Autowired
    private NewDao newDao;

    @GetMapping("/news")
    public List<New> getAllNews() {
        return newDao.findAll();
    }

    @GetMapping("/news/{id}")
    public ResponseEntity<New> getNewById(@PathVariable(value = "id") int id)
            throws ResourceNotFoundException {
        New aNew = newDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("News not found for this id :: " + id));
        return ResponseEntity.ok().body(aNew);
    }

    @PostMapping("/news")
    public New createNews(@Valid @RequestBody New aNew) {
        return newDao.save(aNew);
    }

    @PutMapping("/news/{id}")
    public ResponseEntity<New> updateNews(@PathVariable(value = "id") int id,
                                          @Valid @RequestBody New newsDetails) throws ResourceNotFoundException {
        New aNew = newDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("News not found for this id :: " + id));

        aNew.setId(newsDetails.getId());
        aNew.setTitle(newsDetails.getTitle());
        aNew.setContent(newsDetails.getContent());
        aNew.setImage(newsDetails.getImage());
        aNew.setCreatedAt(newsDetails.getCreatedAt());
        aNew.setUpdatedAt(newsDetails.getUpdatedAt());
        final New updatedNews = newDao.save(aNew);
        return ResponseEntity.ok(updatedNews);
    }

    @DeleteMapping("/news/{id}")
    public Map<String, Boolean> deleteNews(@PathVariable(value = "id") int id)
            throws ResourceNotFoundException {
        New aNew = newDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("News not found for this id :: " + id));

        newDao.delete(aNew);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
