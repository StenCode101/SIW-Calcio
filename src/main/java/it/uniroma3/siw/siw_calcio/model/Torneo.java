package it.uniroma3.siw.siw_calcio.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Torneo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Integer anno;
    
    @Column(columnDefinition = "TEXT")
    private String descrizione;

    // Relazioni
    @ManyToMany(mappedBy = "tornei")
    private List<Squadra> squadrePartecipanti;

    @OneToMany(mappedBy = "torneo")
    private List<Partita> partite;

    // Costruttori, Getters e Setters
    public Torneo() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Integer getAnno() { return anno; }
    public void setAnno(Integer anno) { this.anno = anno; }
    public String getDescrizione() { return descrizione; }
    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }
    public List<Squadra> getSquadrePartecipanti() { return squadrePartecipanti; }
    public void setSquadrePartecipanti(List<Squadra> squadrePartecipanti) { this.squadrePartecipanti = squadrePartecipanti; }
    public List<Partita> getPartite() { return partite; }
    public void setPartite(List<Partita> partite) { this.partite = partite; }
}