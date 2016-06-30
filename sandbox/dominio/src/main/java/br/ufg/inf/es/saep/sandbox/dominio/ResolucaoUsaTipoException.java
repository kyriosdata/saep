/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

/**
 * Indica a existência de referência para o objeto que se
 * deseja remover.
 */
public class ResolucaoUsaTipoException extends RuntimeException {

    public ResolucaoUsaTipoException(String mensagem) {
        super(mensagem);
    }
}
