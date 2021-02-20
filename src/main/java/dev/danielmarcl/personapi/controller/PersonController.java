package dev.danielmarcl.personapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
    @GetMapping("api/v1/person")
    public static String getPerson() {
        return "Person Controller :: OK";
    }
}
