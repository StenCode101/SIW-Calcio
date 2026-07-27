package it.uniroma3.siw.siw_calcio.repository;

import it.uniroma3.siw.siw_calcio.model.Squadra;
import it.uniroma3.siw.siw_calcio.model.Giocatore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface GiocatoreRepository extends JpaRepository<Giocatore, Long> {
    
    // Trova tutti i giocatori in base al ruolo (es. "Attaccante")
    List<Giocatore> findByRuolo(String ruolo);
    
    // Trova tutti i giocatori che appartengono a una specifica squadra
    List<Giocatore> findBySquadra(Squadra squadra);
}