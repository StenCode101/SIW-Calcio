package it.uniroma3.siw.siw_calcio.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Quando un utente visita la radice del sito "/"
    @GetMapping("/")
    public String mostraIndex() {
        // Restituisce il file "index.html" (Thymeleaf aggiunge in automatico .html)
        return "index";
    }
}