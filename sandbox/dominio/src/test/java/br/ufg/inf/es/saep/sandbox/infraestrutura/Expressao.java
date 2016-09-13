/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.infraestrutura;

/**
 * Identifica uma expressão que produz um
 * valor {@code double} quando avaliada.
 */
public interface Expressao {

    /**
     * Obtém o valor produzido pela execução
     * da expressão.
     *
     * @return Valor {@code double} obtido da
     * avaliação da expressão.
     */
    double valor();
}
