package it.uniroma3.siw.siw_calcio.service;



import it.uniroma3.siw.siw_calcio.model.Torneo;
import it.uniroma3.siw.siw_calcio.repository.TorneoRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TorneoService {

    private final TorneoRepository torneoRepository;

    TorneoService(TorneoRepository torneoRepository) {
        this.torneoRepository = torneoRepository;
    }

    @Transactional
    public Torneo salvaTorneo(Torneo torneo) {
        return torneoRepository.save(torneo);
    }

    public List<Torneo> trovaTutti() {
        return torneoRepository.findAll();
    }

    public Torneo trovaPerId(Long id) {
        return torneoRepository.findById(id).orElse(null);
    }

    @Transactional
    public void eliminaTorneo(Long id) {
        torneoRepository.deleteById(id);
    }
}