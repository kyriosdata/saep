/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio.excecoes;

/**
 * Indica que o tipo de atributo é inválido.
 */
public class TipoDeAtributoInvalido extends RuntimeException {

    /**
     * Sinaliza que o tipo do atributo é inválido.
     *
     * @param mensagem Mensagem que detalha a exceção.
     */
    public TipoDeAtributoInvalido(final String mensagem) {
        super(mensagem);
    }
}
