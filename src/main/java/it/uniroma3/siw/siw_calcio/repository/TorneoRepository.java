package it.uniroma3.siw.siw_calcio.repository;

import it.uniroma3.siw.siw_calcio.model.Torneo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TorneoRepository extends JpaRepository<Torneo, Long> {
    
    // Trova tutti i tornei di uno specifico anno
    List<Torneo> findByAnno(Integer anno);
}