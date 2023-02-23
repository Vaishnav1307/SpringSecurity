package com.Security.SpringSecurityLesson2.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/Demo")
    public String hello(){
        return "HelloDemo!";
    }
}
