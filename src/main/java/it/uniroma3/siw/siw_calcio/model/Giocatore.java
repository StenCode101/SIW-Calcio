package it.uniroma3.siw.siw_calcio.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Giocatore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cognome;
    private LocalDate dataDiNascita;
    private String ruolo;
    private Double altezza;

    // Relazioni
    @ManyToOne
    @JoinColumn(name = "squadra_id")
    private Squadra squadra;

    // Costruttori, Getters e Setters
    public Giocatore() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }
    public LocalDate getDataDiNascita() { return dataDiNascita; }
    public void setDataDiNascita(LocalDate dataDiNascita) { this.dataDiNascita = dataDiNascita; }
    public String getRuolo() { return ruolo; }
    public void setRuolo(String ruolo) { this.ruolo = ruolo; }
    public Double getAltezza() { return altezza; }
    public void setAltezza(Double altezza) { this.altezza = altezza; }
    public Squadra getSquadra() { return squadra; }
    public void setSquadra(Squadra squadra) { this.squadra = squadra; }
}