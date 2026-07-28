package it.uniroma3.siw.siw_calcio.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin") // Aggiunge /admin in automatico a tutte le rotte di questo controller
public class AdminController {

    @GetMapping("/dashboard")
    public String mostraDashboardAdmin() {
        return "admin/dashboard";
    }
}