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
 *
 * Por exemplo, segunda resolução 32/2013,
 * CONSUNI, Anexo II, dentre as atividades de ensino,
 * na graduação, temos dois tipos, citados abaixo:
 * <ul>
 *     <li>Aulas presenciais na graduação</li>
 *     <li>Aulas do ensino a distância na graduação</li>
 * </ul>
 *
 * Esses dois tipos são ilustrados acima pelos nomes
 * correspondentes. Os códigos poderiam ser "APG" e
 * "AEADG", respectivamente.
 */
public class Tipo {

    /**
     * Código único que identifica o tipo,
     * por exemplo, "EG" para ensino de
     * graduação. O código é, geralmente,
     * "curto".
     */
    private String codigo;

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

    /**
     * Conjunto de atributos que caracteriza um
     * relato do tipo.
     */
    private Set<Atributo> atributos;

    /**
     * Recupera o código único do tipo.
     *
     * @return Identificador único do tipo.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Recupera o nome pelo qual o tipo é conhecido.
     *
     * @return O nome do tipo.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Recupera a descrição do tipo.
     *
     * @return Descrição ou informação adicional
     * sobre o tipo.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Cria tipo definido pelo conjunto byId atributos.
     * @param nome O nome pelo qual o tipo é conhecido.
     * @param codigo Código único que identifica o tipo.
     * @param descricao Informação adicional sobre o tipo.
     * @param atributos Atributos que caracterizam o tipo.

     */
    public Tipo(String nome, String codigo, String descricao, Set<Atributo> atributos) {
        this.nome = nome;
        this.codigo = codigo;
        this.descricao = descricao;
        this.atributos = atributos;
    }

    /**
     * Recupera conjunto byId atributos do tipo.
     *
     * @return O conjunto byId atributos que define o
     * tipo.
     */
    public Set<Atributo> getAtributos() {
        return atributos;
    }
}
