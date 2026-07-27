package it.uniroma3.siw.siw_calcio.service;


import it.uniroma3.siw.siw_calcio.model.Partita;
import it.uniroma3.siw.siw_calcio.model.Torneo;
import it.uniroma3.siw.siw_calcio.repository.PartitaRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PartitaService {

    private final PartitaRepository partitaRepository;

    PartitaService(PartitaRepository partitaRepository) {
        this.partitaRepository = partitaRepository;
    }

    @Transactional
    public Partita salvaPartita(Partita partita) {
        return partitaRepository.save(partita);
    }

    public List<Partita> trovaTutte() {
        return partitaRepository.findAll();
    }

    public Partita trovaPerId(Long id) {
        return partitaRepository.findById(id).orElse(null);
    }

    public List<Partita> trovaPerTorneo(Torneo torneo) {
        return partitaRepository.findByTorneo(torneo);
    }

    @Transactional
    public void eliminaPartita(Long id) {
        partitaRepository.deleteById(id);
    }
}