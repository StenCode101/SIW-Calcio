package it.uniroma3.siw.siw_calcio.controller;


import it.uniroma3.siw.siw_calcio.model.Partita;
import it.uniroma3.siw.siw_calcio.service.PartitaService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/partite")
@CrossOrigin(origins = "*") // Permette a React (che di solito gira sulla porta 3000) di comunicare con Spring Boot (porta 8080)
public class PartitaRestController {

    private final PartitaService partitaService;

    PartitaRestController(PartitaService partitaService) {
        this.partitaService = partitaService;
    }

    @GetMapping
    public List<Map<String, Object>> getPartitePerCalendario() {
        List<Partita> partite = partitaService.trovaTutte(); // Assicurati di avere questo metodo nel Service!
        List<Map<String, Object>> partiteJson = new ArrayList<>();

        // Trasformiamo i dati in un formato semplice per evitare errori di "ricorsione infinita" con JSON e React
        for (Partita p : partite) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", p.getId());
            map.put("title", p.getSquadraCasa().getNome() + " vs " + p.getSquadraTrasferta().getNome());
            map.put("start", p.getDataEOra()); // React userà questa data
            map.put("end", p.getDataEOra());   // Mettiamo la stessa data per semplicità, o aggiungi 2 ore
            map.put("luogo", p.getLuogo());
            map.put("stato", p.getStato());
            map.put("risultato", p.getGoalsHome() + " - " + p.getGoalsAway());
            partiteJson.add(map);
        }

        return partiteJson;
    }
}