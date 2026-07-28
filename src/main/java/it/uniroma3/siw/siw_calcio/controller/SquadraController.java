package it.uniroma3.siw.siw_calcio.controller;

import it.uniroma3.siw.siw_calcio.model.Squadra;
import it.uniroma3.siw.siw_calcio.service.GiocatoreService;
import it.uniroma3.siw.siw_calcio.service.SquadraService;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SquadraController {

    private final SquadraService squadraService;

    private final GiocatoreService giocatoreService;

    SquadraController(SquadraService squadraService, GiocatoreService giocatoreService) {
        this.squadraService = squadraService;
        this.giocatoreService = giocatoreService;
    }

    @GetMapping("/squadre")
    public String mostraSquadre(Model model) {
        model.addAttribute("squadre", squadraService.trovaTutte());
        return "squadre";
    }

    // NUOVA ROTTA: Mostra i giocatori di una specifica squadra
    @GetMapping("/squadre/{id}/giocatori")
    public String mostraGiocatoriSquadra(@PathVariable("id") Long id, Model model) {
        Squadra squadra = squadraService.trovaPerId(id);
        
        // Passiamo sia la squadra (per mostrare il nome nel titolo) sia la lista dei giocatori
        model.addAttribute("squadra", squadra);
        model.addAttribute("giocatori", giocatoreService.trovaPerSquadra(squadra));
        
        return "squadra-giocatori";
    }
}