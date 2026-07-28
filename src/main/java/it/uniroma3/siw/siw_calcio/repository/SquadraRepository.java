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

    // 1. STRATEGIA LAZY (Genera il problema N+1)
    // È il metodo di default fornito da Spring Data, usiamo findAll() standard.

    // 2. STRATEGIA JOIN FETCH
    // Fa un'unica query forzando il caricamento dei giocatori tramite JPQL
    @Query("SELECT s FROM Squadra s JOIN FETCH s.giocatori")
    List<Squadra> findAllByJoinFetch();

    // 3. STRATEGIA ENTITY GRAPH
    // Usa le API di JPA per dire dinamicamente di caricare anche "giocatori"
    @EntityGraph(attributePaths = {"giocatori"})
    @Query("SELECT s FROM Squadra s")
    List<Squadra> findAllByEntityGraph();
}