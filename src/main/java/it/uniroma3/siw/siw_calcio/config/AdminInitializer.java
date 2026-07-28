package it.uniroma3.siw.siw_calcio.config;


import it.uniroma3.siw.siw_calcio.model.Utente;
import it.uniroma3.siw.siw_calcio.service.UtenteService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// @Component dice a Spring di caricare questa classe all'avvio
@Component
public class AdminInitializer implements CommandLineRunner {

    private final UtenteService utenteService;

    AdminInitializer(UtenteService utenteService) {
        this.utenteService = utenteService;
    }

    // Il metodo run viene eseguito in automatico appena l'applicazione è pronta
    @Override
    public void run(String... args) throws Exception {
        
        // Controlliamo se l'utente "admin" esiste già nel database
        if (utenteService.trovaPerUsername("admin") == null) {
            
            // Se non esiste, lo creiamo
            Utente admin = new Utente();
            admin.setUsername("admin");
            admin.setPassword("admin123"); // Scegli qui la password che preferisci!
            admin.setRuolo("ADMIN");
            
            // Usiamo salvaUtente, che grazie al Service cripterà la password prima di salvarla!
            utenteService.salvaUtente(admin);
            
            System.out.println("=========================================");
            System.out.println(" ACCOUNT ADMIN CREATO CON SUCCESSO!      ");
            System.out.println(" Username: admin                         ");
            System.out.println(" Password: admin123                      ");
            System.out.println("=========================================");
        } else {
            System.out.println("Account admin già presente nel database.");
        }
    }
}