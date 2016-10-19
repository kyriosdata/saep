/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import br.ufg.inf.es.saep.sandbox.dominio.excecoes.CampoExigidoNaoFornecido;

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
     * O valor desse campo é uma chave natural.
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
    public final String getNome() {
        return nome;
    }

    /**
     * Recupera a descrição do tipo.
     *
     * @return Descrição ou informação adicional
     *      sobre o tipo.
     */
    public final String getDescricao() {
        return descricao;
    }

    /**
     * Cria uma classe.
     * @param id Código único que identifica a classe.
     * @param nomeClasse O nome pelo qual a classe é conhecida.
     * @param descricaoClasse Informação adicional sobre a classe.
     * @param atributosClasse Atributos que caracterizam a classe.
     */
    public Classe(final String id, final String nomeClasse,
                  final String descricaoClasse,
                  final Set<Atributo> atributosClasse) {
        super(id);

        if (nomeClasse == null || nomeClasse.isEmpty()) {
            throw new CampoExigidoNaoFornecido("id");
        }

        if (atributosClasse == null || atributosClasse.isEmpty()) {
            throw new CampoExigidoNaoFornecido("atributos");
        }

        this.nome = nomeClasse;
        this.descricao = descricaoClasse;
        this.atributos = atributosClasse;
    }

    /**
     * Recupera o conjunto de atributos da classe.
     *
     * @return O conjunto de atributos que define a classe.
     */
    public final Set<Atributo> getAtributos() {
        return atributos;
    }
}
