package it.uniroma3.siw.siw_calcio.controller;



import it.uniroma3.siw.siw_calcio.model.Utente;
import it.uniroma3.siw.siw_calcio.service.UtenteService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UtenteController {

    private final UtenteService utenteService;

    UtenteController(UtenteService utenteService) {
        this.utenteService = utenteService;
    }

    // --- LOGIN ---
    @GetMapping("/login")
    public String mostraLogin() {
        return "login";
    }

    // --- REGISTRAZIONE ---
    
    // 1. Mostra il form vuoto
    @GetMapping("/register")
    public String mostraRegistrazione(Model model) {
        // Passiamo un oggetto "Utente" vuoto alla pagina HTML per raccogliere i dati
        model.addAttribute("utente", new Utente());
        return "register";
    }

    // 2. Riceve i dati dal form e li salva
    @PostMapping("/register")
    public String registraUtente(@ModelAttribute("utente") Utente utente) {
        // Impostiamo il ruolo di default per i nuovi registrati
        utente.setRuolo("USER");
        
        // Salviamo l'utente nel database tramite il service
        utenteService.salvaUtente(utente);
        
        // Dopo essersi registrato con successo, lo rimandiamo alla pagina di login
        return "redirect:/login";
    }
}