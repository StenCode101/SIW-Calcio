package it.uniroma3.siw.siw_calcio.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Partita {

    public enum StatoPartita {
        SCHEDULED, PLAYED, CANCELLED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataEOra;
    private String luogo;
    
    // Possiamo usare "Integer" in modo che possa essere null se la partita non è ancora giocata
    private Integer goalsHome;
    private Integer goalsAway;

    @Enumerated(EnumType.STRING)
    private StatoPartita stato;

    // Relazioni
    @ManyToOne
    @JoinColumn(name = "torneo_id")
    private Torneo torneo;

    @ManyToOne
    @JoinColumn(name = "squadra_casa_id")
    private Squadra squadraCasa;

    @ManyToOne
    @JoinColumn(name = "squadra_trasferta_id")
    private Squadra squadraTrasferta;

    @ManyToOne
    @JoinColumn(name = "arbitro_id")
    private Arbitro arbitro;

    // Costruttori, Getters e Setters
    public Partita() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getDataEOra() { return dataEOra; }
    public void setDataEOra(LocalDateTime dataEOra) { this.dataEOra = dataEOra; }
    public String getLuogo() { return luogo; }
    public void setLuogo(String luogo) { this.luogo = luogo; }
    public Integer getGoalsHome() { return goalsHome; }
    public void setGoalsHome(Integer goalsHome) { this.goalsHome = goalsHome; }
    public Integer getGoalsAway() { return goalsAway; }
    public void setGoalsAway(Integer goalsAway) { this.goalsAway = goalsAway; }
    public StatoPartita getStato() { return stato; }
    public void setStato(StatoPartita stato) { this.stato = stato; }
    public Torneo getTorneo() { return torneo; }
    public void setTorneo(Torneo torneo) { this.torneo = torneo; }
    public Squadra getSquadraCasa() { return squadraCasa; }
    public void setSquadraCasa(Squadra squadraCasa) { this.squadraCasa = squadraCasa; }
    public Squadra getSquadraTrasferta() { return squadraTrasferta; }
    public void setSquadraTrasferta(Squadra squadraTrasferta) { this.squadraTrasferta = squadraTrasferta; }
    public Arbitro getArbitro() { return arbitro; }
    public void setArbitro(Arbitro arbitro) { this.arbitro = arbitro; }
}