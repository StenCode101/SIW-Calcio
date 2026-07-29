package it.uniroma3.siw.siw_calcio.controller;

import it.uniroma3.siw.siw_calcio.model.Commento;
import it.uniroma3.siw.siw_calcio.model.Utente;
import it.uniroma3.siw.siw_calcio.model.Partita;
import it.uniroma3.siw.siw_calcio.service.CommentoService;
import it.uniroma3.siw.siw_calcio.service.UtenteService;
import it.uniroma3.siw.siw_calcio.service.PartitaService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PartitaController {

    private final PartitaService partitaService;
    private final CommentoService commentoService;
    private final UtenteService utenteService;

    PartitaController(PartitaService partitaService, CommentoService commentoService, UtenteService utenteService) {
        this.partitaService = partitaService;
        this.commentoService = commentoService;
        this.utenteService = utenteService;
    }

    @GetMapping("/partite")
    public String mostraPartite(Model model) {
        model.addAttribute("partite", partitaService.trovaTutte());
        return "partite";
    }

    @GetMapping("/partita/{id}")
    public String mostraPartita(@PathVariable("id") Long id, Model model) {
        Partita partita = partitaService.trovaPerId(id);
        List<Commento> commenti = commentoService.trovaPerPartita(partita);

        String usernameCorrente = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            usernameCorrente = ((UserDetails) principal).getUsername();
        }
        
        // CORRETTO: usiamo trovaPerUsername come definito nel tuo UtenteService
        Utente utenteCorrente = (usernameCorrente != null) ? utenteService.trovaPerUsername(usernameCorrente) : null; 

        model.addAttribute("partita", partita);
        model.addAttribute("commenti", commenti);
        model.addAttribute("nuovoCommento", new Commento());
        model.addAttribute("utenteCorrente", utenteCorrente);

        return "partita-dettagli";
    }

    // Gestione salvataggio o modifica di un commento
    @PostMapping("/partita/{id}/commenta")
    public String salvaCommento(@PathVariable("id") Long id, 
                                @RequestParam("testo") String testoCommento, 
                                @RequestParam(value = "commentoId", required = false) Long commentoId) {
        
        Partita partita = partitaService.trovaPerId(id);
        
        // Recuperiamo l'utente loggato
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        Utente autore = utenteService.trovaPerUsername(username); 

        if (commentoId != null) {
            // MODIFICA: peschiamo il commento dal database ed evitiamo problemi di merge
            Commento commentoEsistente = commentoService.trovaPerId(commentoId);
            if (commentoEsistente != null && commentoEsistente.getAutore().getId().equals(autore.getId())) {
                commentoEsistente.setTesto(testoCommento);
                commentoService.salva(commentoEsistente);
            }
        } else {
            // NUOVO COMMENTO: creiamo una nuova istanza pulita
            Commento nuovoCommento = new Commento();
            nuovoCommento.setTesto(testoCommento);
            nuovoCommento.setPartita(partita);
            nuovoCommento.setAutore(autore);
            commentoService.salva(nuovoCommento);
        }

        return "redirect:/partita/" + id;
    }
}