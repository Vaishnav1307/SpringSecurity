package com.SpringSecurity.SpringSecurityLesson7.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/demo")
//    @PreAuthorize("hasAuthority('read')")
    @PreAuthorize("hasAnyAuthority('read','write')")
    public String demo() {
        return "demo";
    }


    @GetMapping("/demo1/{chotya}")
    @PreAuthorize("#something == authentication.name")
    public String demo1(@PathVariable("chotya") String something) {
        return "demo1";
    }


}
