package it.uniroma3.siw.siw_calcio.service;

import it.uniroma3.siw.siw_calcio.model.Giocatore;
import it.uniroma3.siw.siw_calcio.model.Squadra;
import it.uniroma3.siw.siw_calcio.repository.GiocatoreRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GiocatoreService {

    private final GiocatoreRepository giocatoreRepository;

    GiocatoreService(GiocatoreRepository giocatoreRepository) {
        this.giocatoreRepository = giocatoreRepository;
    }

    @Transactional
    public Giocatore salvaGiocatore(Giocatore giocatore) {
        return giocatoreRepository.save(giocatore);
    }

    public List<Giocatore> trovaTutti() {
        return giocatoreRepository.findAll();
    }

    public Giocatore trovaPerId(Long id) {
        return giocatoreRepository.findById(id).orElse(null);
    }

    public List<Giocatore> trovaPerSquadra(Squadra squadra) {
        return giocatoreRepository.findBySquadra(squadra);
    }

    @Transactional
    public void eliminaGiocatore(Long id) {
        giocatoreRepository.deleteById(id);
    }
}