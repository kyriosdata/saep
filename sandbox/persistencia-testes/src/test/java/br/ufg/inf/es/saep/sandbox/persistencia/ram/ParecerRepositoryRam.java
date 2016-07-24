package br.ufg.inf.es.saep.sandbox.persistencia.ram;

import br.ufg.inf.es.saep.sandbox.dominio.*;

import java.util.*;

/**
 * Implementação em RAM (apenas para ilustrar sugestão de teste).
 */
public class ParecerRepositoryRam implements ParecerRepository {

    private Map<String, Parecer> pareceres;
    private Map<String, Radoc> radocs;

    public ParecerRepositoryRam() {

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
