package it.uniroma3.siw.siw_calcio.repository;


import it.uniroma3.siw.siw_calcio.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UtenteRepository extends JpaRepository<Utente, Long> {
    
    // Metodo fondamentale per Spring Security: serve a cercare l'utente nel DB al momento del login
    Optional<Utente> findByUsername(String username);
    
    // Trova tutti gli utenti in base al ruolo (es. per avere la lista degli admin)
    List<Utente> findByRuolo(String ruolo);
}