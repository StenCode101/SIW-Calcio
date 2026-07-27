package it.uniroma3.siw.siw_calcio.service;



import it.uniroma3.siw.siw_calcio.model.Squadra;
import it.uniroma3.siw.siw_calcio.repository.SquadraRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SquadraService {

    private final SquadraRepository squadraRepository;

    SquadraService(SquadraRepository squadraRepository) {
        this.squadraRepository = squadraRepository;
    }

    @Transactional
    public Squadra salvaSquadra(Squadra squadra) {
        return squadraRepository.save(squadra);
    }

    public List<Squadra> trovaTutte() {
        return squadraRepository.findAll();
    }

    public Squadra trovaPerId(Long id) {
        return squadraRepository.findById(id).orElse(null);
    }

    @Transactional
    public void eliminaSquadra(Long id) {
        squadraRepository.deleteById(id);
    }
}