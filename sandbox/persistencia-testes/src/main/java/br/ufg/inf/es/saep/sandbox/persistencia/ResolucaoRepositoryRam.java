package br.ufg.inf.es.saep.sandbox.persistencia;

import br.ufg.inf.es.saep.sandbox.dominio.Resolucao;
import br.ufg.inf.es.saep.sandbox.dominio.ResolucaoRepository;
import br.ufg.inf.es.saep.sandbox.dominio.Tipo;

import java.util.*;

/**
 * Implementação em RAM (apenas para ilustrar testes).
 */
public class ResolucaoRepositoryRam implements ResolucaoRepository {

    private Map<String, Resolucao> resolucoes;
    private Map<String, Tipo> tipos;

    public ResolucaoRepositoryRam() {
        resolucoes = new HashMap<>();
        tipos = new HashMap<>();
    }

    @Override
    public Resolucao byId(String s) {
        return resolucoes.get(s);
    }

    @Override
    public String persiste(Resolucao resolucao) {
        String id = resolucao.getId();
        resolucoes.put(id, resolucao);

        return id;
    }

    @Override
    public boolean remove(String s) {
        Resolucao resolucao = resolucoes.remove(s);
        return resolucao != null;
    }

    @Override
    public List<String> resolucoes() {
        ArrayList<String> resultado = new ArrayList<>();
        resultado.addAll(resolucoes.keySet());
        return resultado;
    }

    @Override
    public void persisteTipo(Tipo tipo) {
        tipos.put(tipo.getId(), tipo);
    }

    @Override
    public void removeTipo(String s) {
        tipos.remove(s);
    }

    @Override
    public Tipo tipoPeloCodigo(String s) {
        Iterator<Tipo> valores = tipos.values().iterator();
        while (valores.hasNext()) {
            Tipo tipo = valores.next();
            if (tipo.getId().equals(s)) {
                return tipo;
            }
        }

        return null;
    }

    @Override
    public List<Tipo> tiposPeloNome(String s) {
        List<Tipo> semelhantes = new ArrayList<>();
        Iterator<Tipo> valores = tipos.values().iterator();
        while (valores.hasNext()) {
            Tipo tipo = valores.next();
            if (tipo.getNome().contains(s)) {
                semelhantes.add(tipo);
            }
        }

        return semelhantes;
    }
}
