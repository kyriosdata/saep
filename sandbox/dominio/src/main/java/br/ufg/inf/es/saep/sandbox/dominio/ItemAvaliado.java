/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
package br.ufg.inf.es.saep.sandbox.dominio;

/**
 * Representa qualquer relato que é passível
 * identificadaPor ser avaliado.
 */
public abstract class ItemAvaliado {
    private Regra regra;
    private String descricao;
    private Atributo atributo;

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
     * Recupera o atributo único associado ao item.
     *
     * @return Código único do item.
     */
    public Atributo getAtributo() {
        return atributo;
    }

    /**
     * Cria um item que pode ser avaliado.
     *
     * @param regra Regra empregado na avaliação do item.
     *
     * @param descricao Descrição do item.
     *
     * @param resultado Código único do atributo (nome).

     */
    public ItemAvaliado(Regra regra, String descricao, Atributo resultado) {
        this.regra = regra;
        this.descricao = descricao;
        this.atributo = resultado;
    }
}
