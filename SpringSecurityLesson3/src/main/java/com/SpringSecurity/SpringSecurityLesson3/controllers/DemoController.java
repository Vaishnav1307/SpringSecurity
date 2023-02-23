package com.SpringSecurity.SpringSecurityLesson3.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/Demo")
    public String demo(){
        return "Demo!";
    }
}
