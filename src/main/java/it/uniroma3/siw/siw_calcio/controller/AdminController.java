package it.uniroma3.siw.siw_calcio.controller;

import org.springframework.stereotype.Controller;

import it.uniroma3.siw.siw_calcio.model.*;
import it.uniroma3.siw.siw_calcio.service.*;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final TorneoService torneoService;
    private final SquadraService squadraService;
    private final GiocatoreService giocatoreService;
    private final PartitaService partitaService;

    AdminController(TorneoService torneoService, SquadraService squadraService, GiocatoreService giocatoreService, PartitaService partitaService) {
        this.torneoService = torneoService;
        this.squadraService = squadraService;
        this.giocatoreService = giocatoreService;
        this.partitaService = partitaService;
    }

    @GetMapping("/dashboard")
    public String mostraDashboardAdmin(Model model) {
        model.addAttribute("tornei", torneoService.trovaTutti());
        model.addAttribute("squadre", squadraService.trovaTutte());
        model.addAttribute("giocatori", giocatoreService.trovaTutti());
        model.addAttribute("partite", partitaService.trovaTutte());
        return "admin/dashboard";
    }

    // --- TORNEO ---
    @GetMapping("/torneo/nuovo")
    public String nuovoTorneo(Model model) {
        model.addAttribute("torneo", new Torneo());
        return "admin/torneo-form";
    }

    @PostMapping("/torneo/salva")
    public String salvaTorneo(@ModelAttribute("torneo") Torneo torneo) {
        torneoService.salvaTorneo(torneo);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/torneo/modifica/{id}")
    public String modificaTorneo(@PathVariable("id") Long id, Model model) {
        model.addAttribute("torneo", torneoService.trovaPerId(id));
        return "admin/torneo-form";
    }

    // --- SQUADRA ---
    @GetMapping("/squadra/nuova")
    public String nuovaSquadra(Model model) {
        model.addAttribute("squadra", new Squadra());
        return "admin/squadra-form";
    }

    @PostMapping("/squadra/salva")
    public String salvaSquadra(@ModelAttribute("squadra") Squadra squadra) {
        squadraService.salvaSquadra(squadra);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/squadra/modifica/{id}")
    public String modificaSquadra(@PathVariable("id") Long id, Model model) {
        model.addAttribute("squadra", squadraService.trovaPerId(id));
        return "admin/squadra-form";
    }

    @GetMapping("/squadra/elimina/{id}")
    public String eliminaSquadra(@PathVariable("id") Long id) {
        squadraService.eliminaSquadra(id);
        return "redirect:/admin/dashboard";
    }

    // --- GIOCATORE ---
    @GetMapping("/giocatore/nuovo")
    public String nuovoGiocatore(Model model) {
        model.addAttribute("giocatore", new Giocatore());
        model.addAttribute("squadre", squadraService.trovaTutte());
        return "admin/giocatore-form";
    }

    @PostMapping("/giocatore/salva")
    public String salvaGiocatore(@ModelAttribute("giocatore") Giocatore giocatore) {
        giocatoreService.salvaGiocatore(giocatore);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/giocatore/modifica/{id}")
    public String modificaGiocatore(@PathVariable("id") Long id, Model model) {
        model.addAttribute("giocatore", giocatoreService.trovaPerId(id));
        model.addAttribute("squadre", squadraService.trovaTutte());
        return "admin/giocatore-form";
    }

    // --- PARTITA ---
    @GetMapping("/partita/nuova")
    public String nuovaPartita(Model model) {
        model.addAttribute("partita", new Partita());
        model.addAttribute("tornei", torneoService.trovaTutti());
        model.addAttribute("squadre", squadraService.trovaTutte());
        return "admin/partita-form";
    }

    @PostMapping("/partita/salva")
    public String salvaPartita(@ModelAttribute("partita") Partita partita) {
        partitaService.salvaPartita(partita);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/partita/modifica/{id}")
    public String modificaPartita(@PathVariable("id") Long id, Model model) {
        model.addAttribute("partita", partitaService.trovaPerId(id));
        model.addAttribute("tornei", torneoService.trovaTutti());
        model.addAttribute("squadre", squadraService.trovaTutte());
        return "admin/partita-form";
    }

    @GetMapping("/partita/elimina/{id}")
    public String eliminaPartita(@PathVariable("id") Long id) {
        partitaService.eliminaPartita(id);
        return "redirect:/admin/dashboard";
    }
}