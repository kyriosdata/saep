/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

/**
 * Um atributo define um nome e um tipo que
 * define o conjunto de valores que uma
 * "variável" com esse nome pode assumir.
 *
 * Um relato é composto por um conjunto de
 * valores, cada um deles para um atributo.
 */
public class Atributo {

    /**
     * O nome do atributo, por exemplo,
     * "cha" ou "nome".
     */
    private String nome;

    /**
     * A identificação do conjunto de valores
     * associado ao atributo. Por exemplo,
     * um atributo "inteiro" significa que
     * apenas valores inteiros podem ser
     * assumidos pelo atributo em questão.
     */
    private TipoPrimitivo tipo;

    /**
     * Informação adicional que detalha o uso
     * esperado do tipo (informação de ajuda).
     * Por exemplo, para o Atributo "nome", pode
     * ser algo como "nome completo do periódico,
     * por exemplo, International Journal of Health
     * Informatics.".
     */
    private String descricao;

    public Atributo(String nome, String descricao, TipoPrimitivo tipo) {
        this.nome = nome;
        this.descricao = descricao;
        this.tipo = tipo;
    }

    /**
     * Recupera o nome do atributo.
     *
     * @return O identificador único do atributo.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Recupera o tipo primitivo do atributo.
     *
     * @return O tipo do atributo.
     */
    public TipoPrimitivo getTipo() {
        return tipo;
    }
}
