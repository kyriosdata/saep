/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.Set;

/**
 * Uma instância dessa classe permite classificar um relato.
 *
 * <p>Uma Classe define os atributos
 * que caracterizam um relato.
 */
public class Classe extends Entidade {

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
     *      sobre o tipo.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Cria tipo definido pelo conjunto parecerById atributos.
     * @param id Código único que identifica o tipo.
     * @param nome O nome pelo qual o tipo é conhecido.
     * @param descricao Informação adicional sobre o tipo.
     * @param atributos Atributos que caracterizam o tipo.
     */
    public Classe(String id, String nome, String descricao, Set<Atributo> atributos) {
        super(id);

        if (nome == null || nome.isEmpty()) {
            throw new CampoExigidoNaoFornecido("id");
        }

        if (atributos == null || atributos.size() == 0) {
            throw new CampoExigidoNaoFornecido("atributos");
        }

        this.nome = nome;
        this.descricao = descricao;
        this.atributos = atributos;
    }

    /**
     * Recupera o conjunto de atributos da classe.
     *
     * @return O conjunto de atributos que define a classe.
     */
    public Set<Atributo> getAtributos() {
        return atributos;
    }
}
