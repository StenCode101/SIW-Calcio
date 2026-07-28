package it.uniroma3.siw.siw_calcio.controller;



import it.uniroma3.siw.siw_calcio.service.TorneoService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TorneoController {

    private final TorneoService torneoService;

    TorneoController(TorneoService torneoService) {
        this.torneoService = torneoService;
    }

    @GetMapping("/tornei")
    public String mostraTornei(Model model) {
        model.addAttribute("tornei", torneoService.trovaTutti());
        return "tornei";
    }
}