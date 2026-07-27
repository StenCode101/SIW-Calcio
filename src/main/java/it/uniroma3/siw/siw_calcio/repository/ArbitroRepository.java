package it.uniroma3.siw.siw_calcio.repository;


import it.uniroma3.siw.siw_calcio.model.Arbitro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ArbitroRepository extends JpaRepository<Arbitro, Long> {
    
    // Essendo il codice arbitrale unico, usiamo Optional per gestirne l'eventuale assenza
    Optional<Arbitro> findByCodiceArbitrale(String codiceArbitrale);
}