package it.uniroma3.siw.siw_calcio.controller;

import it.uniroma3.siw.siw_calcio.service.SquadraService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SquadraController {

    private final SquadraService squadraService;

    SquadraController(SquadraService squadraService) {
        this.squadraService = squadraService;
    }

    @GetMapping("/squadre")
    public String mostraSquadre(Model model) {
        model.addAttribute("squadre", squadraService.trovaTutte());
        return "squadre";
    }
}