/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio.excecoes;

/**
 * Indica situação excepcional gerada durante a
 * avaliação de uma regra.
 */
public class FalhaAoAvaliarRegra extends RuntimeException {

    public FalhaAoAvaliarRegra(String mensagem) {
        super(mensagem);
    }
}
