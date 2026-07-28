package it.uniroma3.siw.siw_calcio.security;

import it.uniroma3.siw.siw_calcio.model.Utente;
import it.uniroma3.siw.siw_calcio.repository.UtenteRepository;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UtenteRepository utenteRepository;

    UserDetailsServiceImpl(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Cerchiamo l'utente nel database tramite la repository
        Utente utente = utenteRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato con username: " + username));

        // Trasformiamo il nostro "Utente" in uno "User" di Spring Security
        return User.builder()
                .username(utente.getUsername())
                .password(utente.getPassword()) // La password qui sarà già criptata nel DB
                .authorities(utente.getRuolo()) // Assegniamo il ruolo (es. "USER" o "ADMIN")
                .build();
    }
}