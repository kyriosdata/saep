package br.ufg.inf.es.saep.sandbox.persistencia;

import br.ufg.inf.es.saep.sandbox.dominio.*;

import java.util.*;

/**
 * Implementação em RAM (apenas para ilustrar sugestão de teste).
 */
public class ParecerRepositoryRam implements ParecerRepository {

    private Map<String, Parecer> pareceres;

    public ParecerRepositoryRam() {
        pareceres = new HashMap<>();
    }

    @Override
    public void adicionaNota(String s, Nota nota) {

    }

    @Override
    public void removeNota(String s, Avaliavel avaliavel) {

    }

    @Override
    public void persisteParecer(Parecer parecer) {
        pareceres.put(parecer.getId(), parecer);

    }

    @Override
    public void atualizaFundamentacao(String s, String s1) {

    }

    @Override
    public Parecer byId(String s) {
        return null;
    }

    @Override
    public void removeParecer(String s) {

    }

    @Override
    public Radoc radocById(String s) {
        return null;
    }

    @Override
    public String persisteRadoc(Radoc radoc) {
        return null;
    }

    @Override
    public void removeRadoc(String s) {

    }
}
