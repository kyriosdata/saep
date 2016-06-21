/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Uma regra estabelece um valor para um conjunto
 * de objetos avaliáveis (que implementam a interface
 * {@link Avaliavel}).
 *
 * <p>Em um caso comum, uma regra é estabelecida para
 * identificar quantos pontos são obtidos por relatos
 * de um dado tipo, por exemplo, quantos pontos por
 * livro publicado com corpo editorial.
 *
 * <p>Uma regra pode ser empregada para obter a média
 * de pontos obtidos com o ensino em determinado período.
 * Nesse caso, não se trata de uma simples contagem ou
 * consulta a propriedades de relatos. A regra em questão
 * é aplicada sobre um conjunto de entrada no qual cada
 * elemento possui um atributo devidamente identificado,
 * sobre o qual a média será calculada.
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

    private int pontosPorRelato;

    private List<String> dependeDe;
    private double valorMaximo;
    private double valorMinimo;

    public float getPontosPorRelato() {
        return pontosPorRelato;
    }

    /**
     * Lista de dependeDe diretamente empregados
     * pela expressão cuja avaliação dá origem à
     * pontuação da regra.
     * @return Lista de dependeDe diretamente empregados
     * para avaliação da regra.
     */
    public List<String> getDependeDe() { return dependeDe; }

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
     * Recupera o tipo da regra.
     *
     * @return O inteiro que identifica o tipo da regra.
     */
    public int getTipo() { return tipo; }

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
    public Regra(String expressao, double valorMaximo, double valorMinimo, List<String> dependeDe) {
        this.expressao = expressao;
        this.valorMaximo = valorMaximo;
        this.valorMinimo = valorMinimo;
        this.dependeDe = dependeDe;
    }
}
