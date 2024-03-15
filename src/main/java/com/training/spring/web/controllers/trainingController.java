package com.training.spring.web.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class trainingController {

    @GetMapping("greeting/hello")
    public String greeting(){
        return "Hello world";
    }

}
