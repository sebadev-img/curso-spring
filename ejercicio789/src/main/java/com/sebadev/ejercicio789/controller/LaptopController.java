package com.sebadev.ejercicio789.controller;

import com.sebadev.ejercicio789.model.Laptop;
import com.sebadev.ejercicio789.repository.LaptopRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {

    private final LaptopRepository repository;

    public LaptopController(LaptopRepository repository){
        this.repository = repository;
    }
    @GetMapping(path = "api/laptops")
    public List<Laptop> getLaptops(){
        return repository.findAll();
    }
    @GetMapping(path = "api/laptops/{id}")
    public ResponseEntity<Laptop> getLaptop(@PathVariable Long id){
        Optional<Laptop> laptop = repository.findById(id);
        if(laptop.isPresent()){
            return ResponseEntity.ok(laptop.get());
        }
        return ResponseEntity.notFound().build();

    }
    @PostMapping(path = ("api/laptops"))
    public ResponseEntity<Laptop> createLaptop(@RequestBody Laptop laptop){
        if(laptop.getId() != null){
            return ResponseEntity.badRequest().build();
        }
        Laptop result = repository.save(laptop);
        return ResponseEntity.ok(result);
    }
    @PutMapping(path = "api/laptops")
    public ResponseEntity<Laptop> updateLaptop(@RequestBody Laptop laptop){
        if(laptop.getId() == null){
            return ResponseEntity.badRequest().build();
        }
        if(!repository.existsById(laptop.getId())){
            return ResponseEntity.notFound().build();
        }
        Laptop result = repository.save(laptop);
        return ResponseEntity.ok(result);
    }
    @DeleteMapping(path = "/api/laptops/{id}")
    public ResponseEntity<Laptop> deleteLaptop(@PathVariable Long id){
        if(!repository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping(path = "api/laptops")
    public ResponseEntity<Laptop> deleteAll(){
        repository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
