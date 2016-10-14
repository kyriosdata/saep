/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio.excecoes;

/**
 * Indica tentativa de remover um RADOC para o qual há pelo
 * menos um Parecer que o referencia. Ou seja, a tentativa
 * deve falhar com essa exceção.
 */
public class HaParecerParaRadoc extends RuntimeException {

    /**
     * Sinaliza que existe parecer que faz referência a
     * RADOC.
     *
     * @param mensagem Detalhes da situação excepcional.
     */
    public HaParecerParaRadoc(final String mensagem) {
        super(mensagem);
    }
}
