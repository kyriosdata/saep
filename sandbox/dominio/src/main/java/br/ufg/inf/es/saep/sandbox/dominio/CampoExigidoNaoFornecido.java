/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

/**
 * Indica situação excepcional na qual um campo
 * deveria receber ou possuir um valor, embora esse não
 * esteja disponível.
 */
public class CampoExigidoNaoFornecido extends RuntimeException {

    /**
     * Indica que um valor é esperado, mas
     * não está disponível.
     *
     * @param campo Nome do campo cujo valor não está
     *              disponível ou é incorreto.
     */
    public CampoExigidoNaoFornecido(String campo) {
        super(campo);
    }
}
