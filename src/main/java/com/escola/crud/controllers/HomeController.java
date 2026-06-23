package com.escola.crud.controllers;

import com.escola.crud.dtos.UsuarioDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String healthCheck() {
        return "OK";
    }

}
