package it.uniroma3.siw.siw_calcio.repository;

import it.uniroma3.siw.siw_calcio.model.Squadra;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SquadraRepository extends JpaRepository<Squadra, Long> {
    
    // Trova una squadra tramite il suo nome esatto
    Squadra findByNome(String nome);
    
    // Trova tutte le squadre di una determinata città
    List<Squadra> findByCitta(String citta);

   // Metodo base per recuperare tutte le squadre (usato da Lazy e Eager)
    List<Squadra> findAll();

    // 1. Soluzione con JOIN FETCH
    @Query("SELECT s FROM Squadra s LEFT JOIN FETCH s.giocatori")
    List<Squadra> findAllWithGiocatori();

    // 2. Soluzione con ENTITY GRAPH
    @EntityGraph(attributePaths = {"giocatori"})
    @Query("SELECT s FROM Squadra s")
    List<Squadra> findAllEntityGraph();
}