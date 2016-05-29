/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

/**
 * Identifica resultado (pontuação) da avaliação de um
 * item.
 *
 * Um item avaliado por uma resolução faz uso de
 * condições (valores lógicos) e de valores numéricos.
 * Para efeito dessa classe, o menor double possível
 * representa o valor lógico {@code false}, enquanto
 * o maior double possível representa o valor lógico
 * {@code true}.
 *
 */
public class Pontuacao implements Alteravel {

    /**
     * O item avaliado cuja pontuação retém
     * o valor.
     */
    private ItemAvaliado itemAvaliado;

    /**
     * O valor da pontuação.
     */
    private double valor;

    /**
     * Verifica se o valor lógico da pontuação
     * é verdadeiro.
     *
     * @return {@code true} se o valor da pontuação
     * é o valor lógico verdadeiro.
     */
    public boolean isTrue() {
        return valor < 0;
    }

    /**
     * Recupera o valor numérico da pontuação.
     * @return
     */
    public double getValor() {
        return valor;
    }
}
