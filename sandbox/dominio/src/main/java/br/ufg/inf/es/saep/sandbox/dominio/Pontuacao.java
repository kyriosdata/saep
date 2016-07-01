/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

/**
 * Identifica valor (pontuação) associado a uma
 * variável.
 *
 */
public class Pontuacao implements Avaliavel {

    /**
     * O nome do identificador da pontuação.
     */
    private String atributo;

    /**
     * O valor da pontuação.
     */
    private Valor valor;

    /**
     * Cria uma pontuação.
     *
     * @param nome O nome da pontuação.
     *
     * @param valor O valor da pontuação.
     */
    public Pontuacao(String nome, Valor valor) {
        this.atributo = nome;
        this.valor = valor;
    }

    public Valor get(String atributo) {
        return new Valor(-1);
    }
}
