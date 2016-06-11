/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

/**
 * Representa item avaliado por uma resolução.
 * Qualquer elemento que direta ou indiretamente
 * produz um valor considerado por uma
 * resolução é representado por uma instância
 * dessa classe.
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
     * Atributo por meio do qual o item
     * avaliado pode ter o seu valor
     * recuperado. Observe que não inclui
     * o valor propriamente dito, mas o
     * identificador do resultado.
     */
    private Atributo resultado;

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
     * Recupera o resultado único associado ao item.
     *
     * @return Código único do item.
     */
    public Atributo getResultado() {
        return resultado;
    }

    /**
     * Cria um item que pode ser avaliado.
     *
     * @param regra Regra empregado na avaliação do item.
     * @param descricao Descrição do item.
     * @param resultado Código único do resultado (nome).

     */
    public ItemAvaliado(Regra regra, String descricao, Atributo resultado) {
        this.regra = regra;
        this.descricao = descricao;
        this.resultado = resultado;
    }
}
