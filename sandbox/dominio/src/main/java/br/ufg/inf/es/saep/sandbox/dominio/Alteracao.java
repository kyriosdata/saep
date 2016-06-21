/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

/**
 * Encapsula uma alteração em um relato ou
 * em pontuação produzida pelo SAEP.
 *
 * O elemento original não é alterado, mas uma
 * cópia do mesmo.
 */
public class Alteracao {
    private Avaliavel original;
    private Avaliavel novo;
    private String justificativa;

    public Alteracao(Avaliavel origem, Avaliavel destino, String justificativa) {
        this.original = origem;
        this.novo = origem;
        this.justificativa = justificativa;
    }
}
