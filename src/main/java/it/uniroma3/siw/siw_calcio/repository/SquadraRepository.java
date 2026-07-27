package it.uniroma3.siw.siw_calcio.repository;

import it.uniroma3.siw.siw_calcio.model.Squadra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SquadraRepository extends JpaRepository<Squadra, Long> {
    
    // Trova una squadra tramite il suo nome esatto
    Squadra findByNome(String nome);
    
    // Trova tutte le squadre di una determinata città
    List<Squadra> findByCitta(String citta);
}