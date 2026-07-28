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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class TorneoController {

    private final TorneoService torneoService;
    private final PartitaService partitaService;

    TorneoController(TorneoService torneoService, PartitaService partitaService) {
        this.torneoService = torneoService;
        this.partitaService = partitaService;
    }

    // Pagina con l'elenco di tutti i tornei
    @GetMapping("/tornei")
    public String mostraTornei(Model model) {
        model.addAttribute("tornei", torneoService.trovaTutti());
        return "tornei";
    }

    // Pagina con i dettagli di uno specifico torneo e relativa classifica ordinata
    @GetMapping("/tornei/{id}")
    public String mostraDettagliTorneo(@PathVariable("id") Long id, Model model) {
        Torneo torneo = torneoService.trovaPerId(id);
        
        List<Partita> partite = partitaService.trovaPerTorneo(torneo);
        
        Set<Squadra> squadrePartecipanti = new HashSet<>();
        Map<Squadra, Integer> golPerSquadra = new HashMap<>();

        for (Partita p : partite) {
            Squadra casa = p.getSquadraCasa();
            Squadra trasferta = p.getSquadraTrasferta();

            if (casa != null) squadrePartecipanti.add(casa);
            if (trasferta != null) squadrePartecipanti.add(trasferta);

            if ("PLAYED".equals(p.getStato())) {
                int golCasa = (p.getGoalsHome() != null) ? p.getGoalsHome() : 0;
                int golTrasferta = (p.getGoalsAway() != null) ? p.getGoalsAway() : 0;

                if (casa != null) {
                    golPerSquadra.put(casa, golPerSquadra.getOrDefault(casa, 0) + golCasa);
                }
                if (trasferta != null) {
                    golPerSquadra.put(trasferta, golPerSquadra.getOrDefault(trasferta, 0) + golTrasferta);
                }
            }
        }

        for (Squadra s : squadrePartecipanti) {
            golPerSquadra.putIfAbsent(s, 0);
        }

        // Ordiniamo la mappa in base ai gol (decrescente)
        List<Map.Entry<Squadra, Integer>> classificaEntry = new ArrayList<>(golPerSquadra.entrySet());
        classificaEntry.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        // Estraiamo solo le squadre ordinate, eliminando il numero dei gol
        List<Squadra> classificaSquadre = new ArrayList<>();
        for (Map.Entry<Squadra, Integer> entry : classificaEntry) {
            classificaSquadre.add(entry.getKey());
        }

        model.addAttribute("torneo", torneo);
        model.addAttribute("partite", partite);
        model.addAttribute("squadre", squadrePartecipanti);
        model.addAttribute("classifica", classificaSquadre); // Passiamo solo la lista di squadre ordinate
        
        return "torneo-dettagli";
    }
}