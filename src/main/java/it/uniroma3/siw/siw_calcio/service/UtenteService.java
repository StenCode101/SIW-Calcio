package it.uniroma3.siw.siw_calcio.service;



import it.uniroma3.siw.siw_calcio.model.Utente;
import it.uniroma3.siw.siw_calcio.repository.UtenteRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UtenteService {

    private final UtenteRepository utenteRepository;

    UtenteService(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    @Transactional
    public Utente salvaUtente(Utente utente) {
        // NOTA: In futuro, qui aggiungeremo la logica per codificare (hash) 
        // la password prima di salvarla nel database con Spring Security!
        return utenteRepository.save(utente);
    }

    public List<Utente> trovaTutti() {
        return utenteRepository.findAll();
    }

    public Utente trovaPerId(Long id) {
        return utenteRepository.findById(id).orElse(null);
    }

    public Utente trovaPerUsername(String username) {
        return utenteRepository.findByUsername(username).orElse(null);
    }

    @Transactional
    public void eliminaUtente(Long id) {
        utenteRepository.deleteById(id);
    }
}