package it.uniroma3.siw.siw_calcio.config;

import it.uniroma3.siw.siw_calcio.service.SquadraService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PerformanceRunner implements CommandLineRunner {

    private final SquadraService squadraService;

    PerformanceRunner(SquadraService squadraService) {
        this.squadraService = squadraService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=========================================");
        System.out.println("INIZIO TEST PRESTAZIONALI SQUADRA-GIOCATORI");
        System.out.println("=========================================");

        squadraService.testLazy();
        
        // scommenta questa riga dopo aver impostato FetchType.EAGER nella classe Squadra
        // squadraService.testEager(); 
        
        squadraService.testJoinFetch();
        squadraService.testEntityGraph();

        System.out.println("=========================================");
        System.out.println("FINE TEST PRESTAZIONALI");
        System.out.println("=========================================");
    }
}