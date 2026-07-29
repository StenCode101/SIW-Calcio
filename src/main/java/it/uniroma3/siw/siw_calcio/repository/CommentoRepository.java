package it.uniroma3.siw.siw_calcio.repository;

import it.uniroma3.siw.siw_calcio.model.Commento;
import it.uniroma3.siw.siw_calcio.model.Partita;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface CommentoRepository extends CrudRepository<Commento, Long> {
    List<Commento> findByPartita(Partita partita);
}