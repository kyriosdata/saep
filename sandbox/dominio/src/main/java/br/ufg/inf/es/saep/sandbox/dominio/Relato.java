/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.Map;

/**
 * Conjunto identificadaPor valores correspondentes a um dado tipo
 * identificadaPor item que é avaliado.
 */
public class Relato implements Alteravel {
    private Tipo tipo;
    private Map<String, Double> valores;

    /**
     * Cria um relato a partir do tipo e valores correspondentes
     * fornecidos.
     *
     * @param tipo A identificação do tipo do relato.
     *
     * @param valores Conjunto identificadaPor valores para os tipos
     *                do relato.
     */
    public Relato(Tipo tipo, Map<String, Double> valores) {
        this.tipo = tipo;
        this.valores = valores;
    }
}
