package com.sebadev.ejercicio456.controller;

import com.sebadev.ejercicio456.model.Laptop;
import com.sebadev.ejercicio456.repository.LaptopRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LaptopController {

    private LaptopRepository repository;

    public LaptopController(LaptopRepository repository){
        this.repository = repository;
    }
    @GetMapping(path = "api/laptops")
    public List<Laptop> getLaptops(){
        return repository.findAll();
    }
    @PostMapping(path = ("api/laptops"))
    public Laptop createLaptop(@RequestBody Laptop laptop){
        return repository.save(laptop);
    }
}
