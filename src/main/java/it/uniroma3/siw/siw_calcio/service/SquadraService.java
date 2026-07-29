package it.uniroma3.siw.siw_calcio.service;



import it.uniroma3.siw.siw_calcio.model.Squadra;
import it.uniroma3.siw.siw_calcio.repository.SquadraRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import java.util.List;

@Service
public class SquadraService {

    private final SquadraRepository squadraRepository;

    SquadraService(SquadraRepository squadraRepository) {
        this.squadraRepository = squadraRepository;
    }

    @Transactional
    public Squadra salvaSquadra(Squadra squadra) {
        return squadraRepository.save(squadra);
    }

    public List<Squadra> trovaTutte() {
        return squadraRepository.findAll();
    }

    public Squadra trovaPerId(Long id) {
        return squadraRepository.findById(id).orElse(null);
    }

    @Transactional
    public void eliminaSquadra(Long id) {
        squadraRepository.deleteById(id);
    }


    //TEST PRESTAZIONALI
    @Transactional(readOnly = true)
    public void testLazy() {
        StopWatch sw = new StopWatch();
        sw.start("LAZY");
        List<Squadra> squadre = squadraRepository.findAll();
        for (Squadra s : squadre) {
            if (s.getGiocatori() != null) {
                s.getGiocatori().size(); // Forza la query per ogni squadra (Problema N+1)
            }
        }
        sw.stop();
        System.out.println(sw.prettyPrint());
    }

    // Nota: Per testare EAGER seriamente, dovresti temporaneamente cambiare 
    // il caricamento in @OneToMany(fetch = FetchType.EAGER) nella classe Squadra
    @Transactional(readOnly = true)
    public void testEager() {
        StopWatch sw = new StopWatch();
        sw.start("EAGER");
        List<Squadra> squadre = squadraRepository.findAll();
        for (Squadra s : squadre) {
            if (s.getGiocatori() != null) {
                s.getGiocatori().size();
            }
        }
        sw.stop();
        System.out.println(sw.prettyPrint());
    }

    @Transactional(readOnly = true)
    public void testJoinFetch() {
        StopWatch sw = new StopWatch();
        sw.start("JOIN FETCH");
        List<Squadra> squadre = squadraRepository.findAllWithGiocatori(); // 1 sola query ottimizzata!
        for (Squadra s : squadre) {
            if (s.getGiocatori() != null) {
                s.getGiocatori().size();
            }
        }
        sw.stop();
        System.out.println(sw.prettyPrint());
    }

    @Transactional(readOnly = true)
    public void testEntityGraph() {
        StopWatch sw = new StopWatch();
        sw.start("ENTITY GRAPH");
        List<Squadra> squadre = squadraRepository.findAllEntityGraph(); // 1 sola query ottimizzata!
        for (Squadra s : squadre) {
            if (s.getGiocatori() != null) {
                s.getGiocatori().size();
            }
        }
        sw.stop();
        System.out.println(sw.prettyPrint());
    }
}