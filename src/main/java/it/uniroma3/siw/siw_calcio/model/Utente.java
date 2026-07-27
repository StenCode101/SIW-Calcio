package it.uniroma3.siw.siw_calcio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "utenti") // "user" in PostgreSQL è spesso una parola riservata, quindi è meglio chiamare la tabella "utenti"
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String ruolo; // Es. "ADMIN", "USER"

    // Costruttori, Getters e Setters
    public Utente() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRuolo() { return ruolo; }
    public void setRuolo(String ruolo) { this.ruolo = ruolo; }
}