/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio.excecoes;

/**
 * Indica que o tipo de regra é inválido.
 */
public class TipoDeRegraInvalido extends RuntimeException {

    /**
     * Sinaliza que tipo da regra em questão é
     * inválido.
     *
     * @param mensagem Detalhes da exceção.
     */
    public TipoDeRegraInvalido(final String mensagem) {
        super(mensagem);
    }
}
