package com.madsoftware.proyecto_final.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @GetMapping("/error")
    public String handleError() {
        // Puedes personalizar esto según tus necesidades
        return "error";
    }

    public String getErrorPath() {
        return "/error";
    }
}