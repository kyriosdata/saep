/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

/**
 * Indica tentativa de remover um RADOC para o qual há pelo
 * menos um Parecer que o referencia. Ou seja, a tentativa
 * deve falhar com essa exceção.
 */
public class ExisteParecerReferenciandoRadoc extends RuntimeException {

    public ExisteParecerReferenciandoRadoc(String mensagem) {
        super(mensagem);
    }
}
