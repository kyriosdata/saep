/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.List;

/**
 * Regra que representa a média de uma dada
 * variável em um conjunto de valores.
 */
public class MediaRegra extends Regra {
    public MediaRegra(Expressao expressao, double valorMaximo, double valorMinimo, List<Atributo> atributos) {
        super(expressao, valorMaximo, valorMinimo, atributos);
    }
}
