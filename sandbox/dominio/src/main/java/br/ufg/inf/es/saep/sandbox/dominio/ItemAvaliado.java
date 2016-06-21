/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

/**
 * Representa um item avaliado por uma resolução.
 * Ou seja, qualquer elemento que direta ou indiretamente
 * produz um valor considerado por uma
 * resolução.
 *
 * <p>Uma resolução é definida por meio do conjunto de
 * itens avaliados por ela.
 *
 */
public class ItemAvaliado {

    /**
     * Regra cuja execução produz o valor
     * ou pontuação para o item avaliado.
     */
    private Regra regra;

    /**
     * Descrição do item avaliado.
     */
    private String descricao;

    /**
     * Retém o tipo avaliado, se for o caso.
     * Convém ressaltar que nem toda avaliação é
     * sobre um conjunto de relatos de determinado
     * tipo. Quando não for, o valor dessa propriedade
     * é irrelevante.
     */
    private String tipo;

    /**
     * Nome da variável que guardará
     * o resultado da avaliação do item.
     */
    private String variavel;

    /**
     * Recupera a regra identificadaPor avaliação do item.
     *
     * @return Regra que avalia o item.
     */
    public Regra getRegra() {
        return regra;
    }

    /**
     * Recupera a descrição do item.
     *
     * @return Sequência que descreve o item.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Recupera o identificador (nome) da variável que deverá
     * guardar o variavel da avaliação do item.
     *
     * @return Código único do item.
     */
    public String getVariavel() {
        return variavel;
    }

    public String getTipo() {
        return tipo;
    }

    /**
     * Cria um item que pode ser avaliado.
     *
     * @param regra Regra empregado na avaliação do item.
     * @param descricao Descrição do item.
     * @param variavel Código único do variavel (nome).

     */
    public ItemAvaliado(Regra regra, String descricao, String variavel) {
        this.regra = regra;
        this.descricao = descricao;
        this.variavel = variavel;
    }
}
