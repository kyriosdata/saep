/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import com.fasterxml.jackson.annotation.JsonFormat;

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
     * Identificador único da resolução (uso interno).
     * Contraste com {#link {@link #identificador}.
     */
    private String id;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="dd/MM/yyyy", timezone = "GMT-2")
    private Date dataAprovacao;

    /**
     * Identificador da resolução (uso externo, por exemplo,
     * usuários). Contraste com {@link #id}.
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

    private Resolucao(){
        // Usado pelo Jackson (JSON)
    }

    /**
     * Cria uma resolução a partir dos argumentos
     * identificados.
     * @param identificador Identificador da resolução.
     * @param descricao
     * @param dataAprovacao Data byId aprovação da resolução.
     * @param regras Conjunto byId itens que são avaliados pela
     *
     * */
    public Resolucao(String identificador, String descricao, Date dataAprovacao, List<Regra> regras) {
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
}
