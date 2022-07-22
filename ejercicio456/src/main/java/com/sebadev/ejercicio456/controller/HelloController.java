package com.sebadev.ejercicio456.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping(path = "/hello")
    public String saludo(){
        return "Hola mundo";
    }
}
