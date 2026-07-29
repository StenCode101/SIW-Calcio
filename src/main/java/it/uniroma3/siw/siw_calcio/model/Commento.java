package it.uniroma3.siw.siw_calcio.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "commenti")
public class Commento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String testo;

    private LocalDateTime dataOra;

    @ManyToOne
    private Partita partita;

    @ManyToOne
    private Utente autore; 

    @PrePersist
    public void prePersist() {
        this.dataOra = LocalDateTime.now();
    }

    // Costruttori, Getters e Setters
    public Commento() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTesto() { return testo; }
    public void setTesto(String testo) { this.testo = testo; }

    public LocalDateTime getDataOra() { return dataOra; }
    public void setDataOra(LocalDateTime dataOra) { this.dataOra = dataOra; }

    public Partita getPartita() { return partita; }
    public void setPartita(Partita partita) { this.partita = partita; }

    public Utente getAutore() { return autore; }
    public void setAutore(Utente autore) { this.autore = autore; }
}