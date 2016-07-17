package br.ufg.inf.es.saep.sandbox.persistencia.gds;

import br.ufg.inf.es.saep.sandbox.dominio.Parecer;
import br.ufg.inf.es.saep.sandbox.dominio.ParecerRepository;
import br.ufg.inf.es.saep.sandbox.dominio.Radoc;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementação do repositório usando Google Datastore.
 */
public class ParecerRepositoryGds implements ParecerRepository {

    private Map<String, Parecer> pareceres;
    private Map<String, Radoc> radocs;

    public ParecerRepositoryGds() {

        pareceres = new HashMap<>();
        radocs = new HashMap<>();
    }

    @Override
    public void persisteParecer(Parecer parecer) {
        pareceres.put(parecer.getId(), parecer);
    }

    @Override
    public Parecer parecerById(String s) {
        return pareceres.get(s);
    }

    @Override
    public void removeParecer(String s) {
        pareceres.remove(s);
    }

    @Override
    public Radoc radocById(String s) {

        return radocs.get(s);
    }

    @Override
    public String persisteRadoc(Radoc radoc) {

        radocs.put(radoc.getId(), radoc);
        return radoc.getId();
    }

    @Override
    public void removeRadoc(String s) {
        radocs.remove(s);
    }
}
