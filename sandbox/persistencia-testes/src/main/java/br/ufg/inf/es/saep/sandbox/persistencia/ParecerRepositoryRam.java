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
    public void adicionaNota(String id, Nota nota) {
        Parecer toUpdate = parecerById(id);

        Parecer novo = new Parecer(id,
                toUpdate.getResolucaoId(),
                toUpdate.getRadocsIds(),
                toUpdate.getPontuacoes(),
                toUpdate.getFundamentacao(),
                toUpdate.getNotas());
        pareceres.remove(id);
        pareceres.put(id, novo);
    }

    @Override
    public void removeNota(String s, Avaliavel avaliavel) {

    }

    @Override
    public void persisteParecer(Parecer parecer) {
        pareceres.put(parecer.getId(), parecer);

    }

    @Override
    public void atualizaFundamentacao(String id, String fundamentacao) {
        Parecer toUpdate = parecerById(id);
        Parecer novo = new Parecer(id,
                toUpdate.getResolucaoId(),
                toUpdate.getRadocsIds(),
                toUpdate.getPontuacoes(),
                fundamentacao,
                toUpdate.getNotas());
        pareceres.remove(id);
        pareceres.put(id, novo);
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
