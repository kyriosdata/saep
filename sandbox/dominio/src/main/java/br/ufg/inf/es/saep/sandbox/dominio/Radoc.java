package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.List;

/**
 * Relatório contendo relatos associados a um docente.
 */
public class Radoc {
    private List<Relato> relatos;

    /**
     * Cria um relatório de relatos.
     *
     * @param relatos Conjunto de relatos que fazem parte
     *                do relatório.
     */
    public Radoc(List<Relato> relatos) {
        if (relatos == null) {
            throw new IllegalArgumentException("relatos");
        }

        this.relatos = relatos;
    }
}
