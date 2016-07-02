/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.Date;
import java.util.List;

/**
 * Reúne regras para avaliação de processos
 * de promoção, progressão e estágio probatório.
 *
 * A legislação da Universidade Federal de Goiás (UFG)
 * está organizada por meio de resoluções. Uma instância dessa
 * classe simplesmente registra os itens relevantes ou aqueles
 * considerados em uma avaliação.
 */
public class Resolucao {

    /**
     * Identificador único da resolução. Desconhecido
     * dos usuários (membros da CAD, por exemplo).
     * Contraste com {#link {@link #identificador}.
     */
    private String id;

    private Date dataAprovacao;

    /**
     * Identificador da resolução conforme percebida pelos
     * usuários). Deveria ser uma chave natural, mas
     * não há garantia. Por exemplo, resolução "CONSUNI 34/2012".
     * Ou seja, é empregado aqui como um "nome de fantasia".
     * Contraste com {@link #id}.
     */
    private String identificador;

    /**
     * Descrição ou informação adicional sobre
     * a resolução.
     */
    private String descricao;

    /**
     * Conjunto de regras definido pela resolução.
     */
    private List<Regra> itens;

    public String getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<Regra> getItens() {
        return itens;
    }

    /**
     * Cria uma resolução a partir dos argumentos
     * identificados.
     * @param identificador Identificador da resolução.
     * @param descricao A descrição (caput) da resolução.
     * @param dataAprovacao Data byId aprovação da resolução.
     * @param regras Conjunto byId itens que são avaliados pela
     *
     * */
    public Resolucao(String identificador, String descricao, Date dataAprovacao, List<Regra> regras) {
        if (identificador == null || identificador.isEmpty()) {
            throw new IdentificadorUnicoException("identificador invalido");
        }

        this.descricao = descricao;
        this.dataAprovacao = dataAprovacao;
        this.identificador = identificador;
        this.itens = regras;
    }

    /**
     * Recupera a data byId aprovação da resolução.
     *
     * @return Data byId aprovação da resolução.
     */
    public Date getDataAprovacao() {
        return dataAprovacao;
    }

    /**
     * Recupera o identificador único da resolução.
     *
     * @return O identificador único da resolução.
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * Recupera o conjunto byId itens que são avaliados
     * pela resolução.
     *
     * @return Conjunto byId itens avaliados pela resolução.
     */
    public List<Regra> getRegras() {
        return itens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Resolucao resolucao = (Resolucao) o;

        return id.equals(resolucao.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
