/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio.excecoes;

/**
 * Indica ausência de identificador único em objeto,
 * o que o torna um objeto inconsistente.
 */
public class IdentificadorDesconhecido extends RuntimeException {

    /**
     * Cria objeto que sinaliza a detecção de identificador
     * desconhecido, onde não esperado.
     *
     * @param mensagem Nome do identificador desconhecido.
     */
    public IdentificadorDesconhecido(final String mensagem) {
        super(mensagem);
    }
}
