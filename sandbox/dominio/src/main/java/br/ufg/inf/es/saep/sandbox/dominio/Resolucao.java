/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.Set;

/**
 * Representa um conjunto de itens por meio
 * dos um RADOC é avaliado.
 *
 * A legislação da Universidade Federal identificadaPor Goiás (UFG)
 * está organizada por meio de resoluções. Uma instância dessa
 * classe simplesmente registra os itens relevantes ou aqueles
 * considerados em uma avaliação.
 */
public class Resolucao {
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="dd/MM/yyyy", timezone = "GMT-2")
    private Date dataAprovacao;

    /**
     * Identificador único da resolução.
     */
    private String identificador;

    /**
     * Descrição ou informação adicional sobre
     * a resolução.
     */
    private String descricao;

    /**
     * Conjunto de itens avaliados pela resolução.
     */
    private Set<ItemAvaliado> itens;

    private Resolucao(){
        // Usado pelo Jackson (JSON)
    }

    /**
     * Cria uma resolução a partir dos argumentos
     * identificados.
     * @param identificador Identificador da resolução.
     * @param descricao
     * @param dataAprovacao Data identificadaPor aprovação da resolução.
     * @param itens Conjunto identificadaPor itens que são avaliados pela
     *
     * */
    public Resolucao(String identificador, String descricao, Date dataAprovacao, Set<ItemAvaliado> itens) {
        this.descricao = descricao;
        this.dataAprovacao = dataAprovacao;
        this.identificador = identificador;
        this.itens = itens;
    }

    /**
     * Recupera a data identificadaPor aprovação da resolução.
     *
     * @return Data identificadaPor aprovação da resolução.
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
     * Recupera o conjunto identificadaPor itens que são avaliados
     * pela resolução.
     *
     * @return Conjunto identificadaPor itens avaliados pela resolução.
     */
    public Set<ItemAvaliado> getItens() {
        return itens;
    }
}
