/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

/**
 * Indica que o tipo de regra é inválido.
 */
public class TipoDeRegraInvalido extends RuntimeException {

    public TipoDeRegraInvalido(String mensagem) {
        super(mensagem);
    }
}
