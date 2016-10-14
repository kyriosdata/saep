/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio.excecoes;

import br.ufg.inf.es.saep.sandbox.dominio.Observacao;

/**
 * Indica que os avaliáveis estabelecidos em uma
 * {@link Observacao} são de tipos distintos e,
 * portanto, incompatíveis.
 */
public class AvaliaveisIncompativeis extends RuntimeException {

    /**
     * Cria instância de exceção gerada quando se tenta
     * criar {@link Observacao} com avaliáveis de tipos
     * distintos.
     *
     * @param campo Informação sobre a tentativa de criar
     *              {@link Observacao} com avaliáveis de tipos
     *              distintos.
     */
    public AvaliaveisIncompativeis(String campo) {
        super(campo);
    }
}
