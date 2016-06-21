/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.List;
import java.util.UUID;

/**
 * Resultado da avaliação de um processo de progressão,
 * promoção ou estágio probatório.
 *
 * <p>Um parecer envolve pelo menos um RADOC. Convém
 * ressaltar que no caso de estágio probatório, por
 * exemplo, vários RADOCs são empregados.
 *
 */
public class Parecer {

    /**
     * Identificador único do parecer.
     */
    private String guid;

    /**
     * Resolução com base na qual o parecer
     * é realizado.
     */
    private Resolucao resolucao;

    /**
     * Lista de relatórios com base nos quais
     * o parecer é realizado. Em muitos casos
     * um único relatório é utilizado.
     */
    private List<Radoc> radocs;

    /**
     * As pontuações obtidas pelo parecer.
     * Inclui aquelas pontuações que alteram
     * outras.
     */
    private Resultado resultado;
    private String descricao;
    private List<Alteracao> alteracoes;

    public Parecer() {
        this.guid = UUID.randomUUID().toString();
    }
}
