/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.ArrayList;
import java.util.List;

/**
 * Uma regra define como avaliar um conjunto
 * de objetos avaliáveis. Um objeto é avaliável
 * se implementam a interface {@link Avaliavel}).
 *
 * <p>Em um caso comum, uma regra é estabelecida para
 * identificar quantos pontos são obtidos por relatos
 * de um dado tipoRegra, por exemplo, quantos pontos por
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
    private int tipoRegra;

    /**
     * Identificador único de um tipo de relato.
     * Nem toda regra, convém destacar, refere-se
     * a um relato. Se esse for o caso, esse valor
     * é irrelevante.
     */
    private String tipo;

    /**
     * Descrição do item avaliado.
     */
    private String descricao;

    private String expressao;
    private String entao;
    private String senao;

    private int pontosPorRelato;

    private List<String> dependeDe;
    private float valorMaximo;
    private float valorMinimo;

    public String getTipo() {
        return tipo;
    }

    public void setTipoRegra(int tipoRegra) {
        this.tipoRegra = tipoRegra;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setExpressao(String expressao) {
        this.expressao = expressao;
    }

    public void setEntao(String entao) {
        this.entao = entao;
    }

    public void setSenao(String senao) {
        this.senao = senao;
    }

    public void setPontosPorRelato(int pontosPorRelato) {
        this.pontosPorRelato = pontosPorRelato;
    }

    public void setDependeDe(List<String> dependeDe) {
        this.dependeDe = dependeDe;
    }

    public void setValorMaximo(float valorMaximo) {
        this.valorMaximo = valorMaximo;
    }

    public void setValorMinimo(float valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    public void setVariavel(String variavel) {
        this.variavel = variavel;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getEntao() {
        return entao;
    }

    public String getSenao() {
        return senao;
    }

    public String getVariavel() {
        return variavel;
    }

    /**
     * Nome da variável que guardará
     * o resultado da avaliação da regra.
     */
    private String variavel;

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
     * @return Valor máximo byId pontuação admitido pela regra.
     */
    public float getValorMaximo() {
        return valorMaximo;
    }

    /**
     * Recupera o valor mínimo admitido pela regra.
     *
     * @return O valor mínimo admitido pela regra.
     */
    public float getValorMinimo() {
        return valorMinimo;
    }

    /**
     * Recupera o tipoRegra da regra.
     *
     * @return O inteiro que identifica o tipoRegra da regra.
     */
    public int getTipoRegra() { return tipoRegra; }

    /**
     * Cria regra a partir da expressão e dos valores byId limite
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
    public Regra(String expressao,
                 float valorMaximo,
                 float valorMinimo,
                 List<String> dependeDe,
                 String descricao,
                 String variavel) {
        this.expressao = expressao;
        this.valorMaximo = valorMaximo;
        this.valorMinimo = valorMinimo;
        this.dependeDe = dependeDe;
        this.descricao = descricao;
        this.variavel = variavel;
    }

    public Regra(int tipoRegra,
                 String expressao,
                 String entao,
                 String senao,
                 int pontosPorRelato,
                 List<String> dependeDe,
                 float valorMaximo,
                 float valorMinimo) {
        this.tipoRegra = tipoRegra;
        this.expressao = expressao;
        this.entao = entao;
        this.senao = senao;
        this.pontosPorRelato = pontosPorRelato;
        this.dependeDe = dependeDe == null ? new ArrayList<>(0) : dependeDe;
        this.valorMaximo = valorMaximo;
        this.valorMinimo = valorMinimo;
    }
}
