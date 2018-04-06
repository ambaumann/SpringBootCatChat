package com.example.catchat.controller;

import com.example.catchat.model.CatResource;
import com.example.catchat.repository.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/rest/cat")
public class CatController {
    @Autowired
    private CatRepository catRepository;

    @GetMapping
    public List<CatResource> listCats() {
        return catRepository.findAll();
    }

    @PutMapping
    public void addCat(@RequestBody CatResource cat) {
        catRepository.save(cat);
    }

    @DeleteMapping
    public void terminateCat(@RequestBody CatResource cat) {
        catRepository.delete(cat);
    }

    @PostMapping
    public void updateCat(@RequestBody CatResource cat) {
        catRepository.save(cat);
    }
}
