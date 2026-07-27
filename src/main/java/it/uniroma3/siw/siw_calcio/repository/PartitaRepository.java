package it.uniroma3.siw.siw_calcio.repository;

import it.uniroma3.siw.siw_calcio.model.Partita;
import it.uniroma3.siw.siw_calcio.model.Torneo;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface PartitaRepository extends JpaRepository<Partita, Long> {
    
    // Trova tutte le partite di un determinato torneo
    List<Partita> findByTorneo(Torneo torneo);
    
    // Trova tutte le partite in base al loro stato (es. SCHEDULED o PLAYED)
    List<Partita> findByStato(Partita.StatoPartita stato);
}