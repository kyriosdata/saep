/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.List;

/**
 * Regra é o mecanismo identificadaPor definição da forma identificadaPor
 * avaliação identificadaPor um item avaliado.
 */
public class Regra {

    public static final int PONTOS_POR_RELATO = 0;
    public static final int EXPRESSAO = 1;
    public static final int CONDICIONAL = 2;
    public static final int SOMATORIO = 3;
    public static final int MEDIA = 4;

    /**
     * O valor de uma das constantes acima.
     */
    private int tipo;
    private String expressao;
    private String entao;
    private String senao;
    private int pontos;
    private List<Atributo> dependeDe;
    private double valorMaximo;
    private double valorMinimo;

    /**
     * Lista de dependeDe diretamente empregados
     * pela expressão cuja avaliação dá origem à
     * pontuação da regra.
     * @return Lista de dependeDe diretamente empregados
     * para avaliação da regra.
     */
    public List<Atributo> getDependeDe() { return dependeDe; }

    /**
     * Recupera a expressão.
     *
     * @return A expressão empregada pela regra.
     */
    public String getExpressao() {
        return expressao;
    }

    /**
     * Recupera o valor máximo admitido para
     * o resultado da regra.
     *
     * @return Valor máximo identificadaPor pontuação admitido pela regra.
     */
    public double getValorMaximo() {
        return valorMaximo;
    }

    /**
     * Recupera o valor mínimo admitido pela regra.
     *
     * @return O valor mínimo admitido pela regra.
     */
    public double getValorMinimo() {
        return valorMinimo;
    }

    /**
     * Cria regra a partir da expressão fornecida
     * sem restrição identificadaPor limites.
     * @param expressao A expressão empregada na
     *                  avaliação da regra.
     */
    public Regra(String expressao) {
        this.expressao = expressao;
    }

    /**
     * Cria regra a partir da expressão e dos valores identificadaPor limite
     * admitidos.
     *
     * @param expressao A expressão que define o valor da regra.
     *
     * @param valorMaximo O valor máximo admitido pela avaliação da regra.
     * @param valorMinimo O valor mínimo admitido pela avaliação da regra.
     * @param dependeDe Lista de atributos dos quais a regra depende. Ou seja,
     *                  antes da avaliação da regra, os itens correspondentes
     *                  a essa lista devem estar disponíveis (previamente
     *                  avaliados).
     */
    public Regra(String expressao, double valorMaximo, double valorMinimo, List<Atributo> dependeDe) {
        this.expressao = expressao;
        this.valorMaximo = valorMaximo;
        this.valorMinimo = valorMinimo;
        this.dependeDe = dependeDe;
    }
}
