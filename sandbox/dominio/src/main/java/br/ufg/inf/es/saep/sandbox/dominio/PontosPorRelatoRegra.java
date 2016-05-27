/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.List;

/**
 * Regra na qual pontos são computados por relato de um
 * dado tipo.
 */
public class PontosPorRelatoRegra extends Regra {
    public PontosPorRelatoRegra(Expressao expressao, double valorMaximo, double valorMinimo, List<Atributo> atributos) {
        super(expressao, valorMaximo, valorMinimo, atributos);
    }
}
