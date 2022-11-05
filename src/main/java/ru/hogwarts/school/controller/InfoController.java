package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/getPort")
public class InfoController {

    @Value("${server.port}")
    private String port;

    @GetMapping
    public String getPort(){
        return port;
    }
}
