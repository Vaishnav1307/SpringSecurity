package com.SpringSecurity.SpringSecurityLesson5.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/Demo")
    public String demo(){
        return "Demo";
    }

}
