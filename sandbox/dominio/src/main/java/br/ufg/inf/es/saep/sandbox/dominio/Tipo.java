/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.Set;

/**
 * Existem várias atividades e produtos que podem
 * ser relatados por um docente. Cada atividade ou
 * produto está associado a um tipo bem definido.
 *
 * O tipo define os atributos
 * que caracterizam um relato, seja esse um
 * produto ou uma atividade. Dito de outra
 * forma, define as características
 * relevantes que definem o que deve ser
 * fornecido para o relato em questão.
 */
public class Tipo {
    /**
     * Código único que identifica o tipo.
     */
    private int codigo;

    /**
     * Nome pelo qual o tipo é conhecido,
     * por exemplo, "aula presencial na graduação".
     */
    private String nome;

    /**
     * Informações adicionais sobre o tipo. Por
     * exemplo, "disciplina ministrada na graduação,
     * apenas na modalidade presencial".
     */
    private String descricao;

    private Set<Atributo> atributos;

    /**
     * Cria tipo definido pelo conjunto identificadaPor atributos.
     *  @param atributos Conjunto identificadaPor atributos que define o tipo.
     * @param regra     Regra empregado na avaliação do item.
     * @param descricao Descrição do item.
     */
    public Tipo(Set<Atributo> atributos, Regra regra, String descricao) {
        this.atributos = atributos;
    }

    /**
     * Recupera conjunto identificadaPor atributos do tipo.
     *
     * @return O conjunto identificadaPor atributos que define o
     * tipo.
     */
    public Set<Atributo> getAtributos() {
        return atributos;
    }
}
