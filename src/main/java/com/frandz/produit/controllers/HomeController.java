package com.frandz.produit.controllers;

import com.frandz.produit.authController.AuthenticationResponse;
import com.frandz.produit.exception.ExpiredTokenException;
import com.frandz.produit.exception.InvalidTokenException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/")
@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class HomeController {
    @GetMapping("/home")
    public String home() {
        return "hello word";
    }
}
