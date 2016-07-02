/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

/**
 * Indica que o tipo de atributo é inválido.
 */
public class TipoDeAtributoInvalido extends RuntimeException {

    public TipoDeAtributoInvalido(String mensagem) {
        super(mensagem);
    }
}
