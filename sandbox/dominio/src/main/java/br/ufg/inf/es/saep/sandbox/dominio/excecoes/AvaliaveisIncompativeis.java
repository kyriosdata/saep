/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio.excecoes;

/**
 * Indica que os avaliáveis estabelecidos em uma
 * {@link br.ufg.inf.es.saep.sandbox.dominio.Observacao}
 * são de tipos distintos e,
 * portanto, incompatíveis.
 */
public class AvaliaveisIncompativeis extends RuntimeException {

    /**
     * Cria instância de exceção gerada quando se tenta
     * criar {@link br.ufg.inf.es.saep.sandbox.dominio.Observacao}
     * com avaliáveis de tipos
     * distintos.
     *
     * @param campo Informação sobre a tentativa de criar
     *              {@link br.ufg.inf.es.saep.sandbox.dominio.Observacao}
     *              com avaliáveis de tipos
     *              distintos.
     */
    public AvaliaveisIncompativeis(String campo) {
        super(campo);
    }
}
