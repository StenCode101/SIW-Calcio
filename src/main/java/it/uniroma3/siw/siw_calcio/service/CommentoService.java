package it.uniroma3.siw.siw_calcio.service;

import it.uniroma3.siw.siw_calcio.model.Commento;
import it.uniroma3.siw.siw_calcio.model.Partita;
import it.uniroma3.siw.siw_calcio.repository.CommentoRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommentoService {

    private final CommentoRepository commentoRepository;

    CommentoService(CommentoRepository commentoRepository) {
        this.commentoRepository = commentoRepository;
    }

    public List<Commento> trovaPerPartita(Partita partita) {
        return commentoRepository.findByPartita(partita);
    }

    public Commento trovaPerId(Long id) {
        return commentoRepository.findById(id).orElse(null);
    }

    public void salva(Commento commento) {
        commentoRepository.save(commento);
    }
}