/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Conjunto de relatos associados a um docente em um dado ano base.
 *
 * <p>Eventualmente um mesmo docente, em um dado ano, pode possuir
 * mais de um RADOC, decorrente, por exemplo, da correação de alguma
 * informação.
 *
 */
public class Radoc {

    /**
     * Identificador único do relatório.
     */
    private String guid;

    /**
     * Ano base do relatório.
     */
    private int anoBase;

    private List<Relato> relatos;

    /**
     * Cria um relatório byId relatos.
     *
     * @param relatos Conjunto byId relatos que fazem parte
     *                do relatório.
     */
    public Radoc(List<Relato> relatos) {
        if (relatos == null) {
            throw new IllegalArgumentException("relatos");
        }

        this.relatos = relatos;
    }

    /**
     * Identifica, dentre os relatos do RADOC, aqueles
     * de um dado tipo.
     *
     * @param tipo O tipo de relato.
     * @return Conjunto de relatos do tipo.
     */
    public List<Relato> relatosPorTipo(String tipo) {
        // TODO não implementado
        return new ArrayList<>(0);
    }
}
