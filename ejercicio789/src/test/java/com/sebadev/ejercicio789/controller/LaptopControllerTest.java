package com.sebadev.ejercicio789.controller;

import com.sebadev.ejercicio789.model.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class LaptopControllerTest {

    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;



    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:8080");
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @Test
    void getLaptops() {
        ResponseEntity<Laptop[]> response = testRestTemplate.getForEntity("/api/laptops",Laptop[].class);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(200,response.getStatusCodeValue());
        List<Laptop> laptops = Arrays.asList(response.getBody());
        System.out.println(laptops.size());

    }

    @Test
    void getLaptop() {
        ResponseEntity<Laptop> response = testRestTemplate.getForEntity("/api/laptops/1",Laptop.class);
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    void createLaptop() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                    "modelo":"modelo test"
                }
                """;
        HttpEntity<String> request = new HttpEntity<>(json,headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops",HttpMethod.POST,request,Laptop.class);
        Laptop result = response.getBody();
        assertEquals(1L,result.getId());
        assertEquals("modelo test",result.getModelo());
    }
}