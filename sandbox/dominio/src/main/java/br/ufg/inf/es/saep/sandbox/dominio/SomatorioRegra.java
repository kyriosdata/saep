/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.List;

/**
 * Regra que representa um somatório cuja função é
 * definida pela expressão da regra.
 */
public class SomatorioRegra extends Regra {
    public SomatorioRegra(Expressao expressao, double valorMaximo, double valorMinimo, List<Atributo> atributos) {
        super(expressao, valorMaximo, valorMinimo, atributos);
    }
}
