package it.uniroma3.siw.siw_calcio.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Squadra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Integer annoDiFondazione;
    private String citta;

    // Relazioni
    @OneToMany(mappedBy = "squadra", cascade = CascadeType.ALL)
    private List<Giocatore> giocatori;

    // Una squadra partecipa a uno o più tornei (Relazione Molti-a-Molti)
    @ManyToMany
    @JoinTable(
        name = "squadra_torneo",
        joinColumns = @JoinColumn(name = "squadra_id"),
        inverseJoinColumns = @JoinColumn(name = "torneo_id")
    )
    private List<Torneo> tornei;

    // Costruttori, Getters e Setters
    public Squadra() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Integer getAnnoDiFondazione() { return annoDiFondazione; }
    public void setAnnoDiFondazione(Integer annoDiFondazione) { this.annoDiFondazione = annoDiFondazione; }
    public String getCitta() { return citta; }
    public void setCitta(String citta) { this.citta = citta; }
    public List<Giocatore> getGiocatori() { return giocatori; }
    public void setGiocatori(List<Giocatore> giocatori) { this.giocatori = giocatori; }
    public List<Torneo> getTornei() { return tornei; }
    public void setTornei(List<Torneo> tornei) { this.tornei = tornei; }
}