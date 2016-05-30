/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.List;

/**
 * Resultado da avaliação de um RADOC.
 *
 * Inclui o relatório sobre o qual o parecer é
 * emitido, juntamente com as eventuais alterações,
 * tanto dos relatos quanto de pontuações produzidas
 * pelo SAEP.
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
}
