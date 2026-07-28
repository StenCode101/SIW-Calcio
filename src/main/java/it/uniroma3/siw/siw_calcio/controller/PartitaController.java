package it.uniroma3.siw.siw_calcio.controller;


import it.uniroma3.siw.siw_calcio.service.PartitaService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PartitaController {

    private final PartitaService partitaService;

    PartitaController(PartitaService partitaService) {
        this.partitaService = partitaService;
    }

    @GetMapping("/partite")
    public String mostraPartite(Model model) {
        model.addAttribute("partite", partitaService.trovaTutte());
        return "partite";
    }
}