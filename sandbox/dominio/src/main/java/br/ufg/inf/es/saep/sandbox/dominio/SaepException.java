/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

/**
 * Exceção gerada pelo SAEP para indicar situação excepcional.
 */
public class SaepException extends RuntimeException {

    public SaepException(String mensagem) {
        super(mensagem);
    }
}
