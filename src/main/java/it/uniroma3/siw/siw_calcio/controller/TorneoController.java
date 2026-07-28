package it.uniroma3.siw.siw_calcio.controller;

import it.uniroma3.siw.siw_calcio.model.Torneo;
import it.uniroma3.siw.siw_calcio.model.Squadra;
import it.uniroma3.siw.siw_calcio.model.Partita;
import it.uniroma3.siw.siw_calcio.service.TorneoService;
import it.uniroma3.siw.siw_calcio.service.PartitaService;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class TorneoController {

    private final TorneoService torneoService;

    private final PartitaService partitaService;


    TorneoController(PartitaService partitaService, TorneoService torneoService) {
        this.partitaService = partitaService;
        this.torneoService = torneoService;
    }

    
    // Pagina con l'elenco di tutti i tornei
    @GetMapping("/tornei")
    public String mostraTornei(Model model) {
        model.addAttribute("tornei", torneoService.trovaTutti());
        return "tornei";
    }

    // Pagina con i dettagli di uno specifico torneo
    @GetMapping("/tornei/{id}")
    public String mostraDettagliTorneo(@PathVariable("id") Long id, Model model) {
        Torneo torneo = torneoService.trovaPerId(id);
        
        // 1. Troviamo tutte le partite di questo torneo
        List<Partita> partite = partitaService.trovaPerTorneo(torneo);
        
        // 2. Estraiamo le squadre partecipanti dalle partite
        // Usiamo un "Set" (che in Java è una lista che non ammette duplicati)
        Set<Squadra> squadrePartecipanti = new HashSet<>();
        for (Partita p : partite) {
            if (p.getSquadraCasa() != null) squadrePartecipanti.add(p.getSquadraCasa());
            if (p.getSquadraTrasferta() != null) squadrePartecipanti.add(p.getSquadraTrasferta());
        }

        // 3. Passiamo tutto alla pagina HTML
        model.addAttribute("torneo", torneo);
        model.addAttribute("partite", partite);
        model.addAttribute("squadre", squadrePartecipanti);
        
        return "torneo-dettagli";
    }
}