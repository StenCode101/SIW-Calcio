package it.uniroma3.siw.siw_calcio.service;


import it.uniroma3.siw.siw_calcio.model.Arbitro;
import it.uniroma3.siw.siw_calcio.repository.ArbitroRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArbitroService {

    private final ArbitroRepository arbitroRepository;

    ArbitroService(ArbitroRepository arbitroRepository) {
        this.arbitroRepository = arbitroRepository;
    }

    @Transactional
    public Arbitro salvaArbitro(Arbitro arbitro) {
        return arbitroRepository.save(arbitro);
    }

    public List<Arbitro> trovaTutti() {
        return arbitroRepository.findAll();
    }

    public Arbitro trovaPerId(Long id) {
        return arbitroRepository.findById(id).orElse(null);
    }

    @Transactional
    public void eliminaArbitro(Long id) {
        arbitroRepository.deleteById(id);
    }
}