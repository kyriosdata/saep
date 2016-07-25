/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

/**
 * Indica que os itens avaliáveis em questão são de
 * tipos distintos e, portanto, não podem fazer parte
 * de uma nota.
 */
public class AvaliaveisDeTiposDistintos extends RuntimeException {

    /**
     * Cria instância de exceção gerada quando se tenta
     * criar {@link Nota} com avaliáveis de tipos
     * distintos.
     *
     * @param campo Informação sobre a tentativa de criar
     *              {@link Nota} com avaliáveis de tipos
     *              distintos.
     */
    public AvaliaveisDeTiposDistintos(String campo) {
        super(campo);
    }
}
