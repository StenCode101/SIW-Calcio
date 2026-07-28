package it.uniroma3.siw.siw_calcio.service;



import it.uniroma3.siw.siw_calcio.model.Utente;
import it.uniroma3.siw.siw_calcio.repository.UtenteRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
@Service
public class UtenteService {

    private final UtenteRepository utenteRepository;

    // Iniettiamo il codificatore che abbiamo definito in SecurityConfig
    private final PasswordEncoder passwordEncoder;

    UtenteService(UtenteRepository utenteRepository, PasswordEncoder passwordEncoder) {
        this.utenteRepository = utenteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Utente salvaUtente(Utente utente) {
        // Prima di salvare, criptiamo la password digitata nel form
        String passwordCriptata = passwordEncoder.encode(utente.getPassword());
        utente.setPassword(passwordCriptata);
        
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