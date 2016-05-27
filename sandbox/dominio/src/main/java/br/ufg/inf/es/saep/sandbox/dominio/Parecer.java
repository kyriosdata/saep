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
    private Resolucao resolucao;
    private Radoc radoc;
    private Resultado resultado;
    private String descricao;
    private List<Alteracao> alteracoes;
}
