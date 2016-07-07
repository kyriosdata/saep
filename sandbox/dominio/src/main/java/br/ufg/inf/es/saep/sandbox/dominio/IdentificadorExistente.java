/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

/**
 * Indica que o identificador já foi empregado por outro
 * objeto, ou seja, situação não esperada.
 */
public class IdentificadorExistente extends RuntimeException {

    /**
     * Indica o nome do identificador cuja tentativa de
     * reutilização gera a exceção.
     *
     * @param campo Nome do campo cujo valor não está
     *              disponível ou é incorreto.
     */
    public IdentificadorExistente(String campo) {
        super(campo);
    }
}
