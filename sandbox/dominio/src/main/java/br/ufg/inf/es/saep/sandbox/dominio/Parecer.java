/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.List;
import java.util.UUID;

/**
 * Resultado da avaliação de um processo de progressão,
 * promoção ou estágio probatório.
 *
 * <p>Um parecer envolve pelo menos um RADOC. Convém
 * ressaltar que no caso de estágio probatório, por
 * exemplo, vários RADOCs são empregados.
 *
 * @see Radoc
 */
public class Parecer {

    /**
     * Identificador único do parecer.
     */
    private String id;

    /**
     * Resolução com base na qual o parecer
     * é realizado.
     */
    private String resolucao;

    /**
     * Lista de relatórios com base nos quais
     * o parecer é realizado. Em muitos casos
     * um único relatório é utilizado.
     */
    private List<String> radocs;

    /**
     * As pontuações obtidas pelo parecer.
     * Inclui aquelas pontuações que alteram
     * outras.
     */
    private List<Pontuacao> pontuacoes;

    /**
     * O texto do parecer propriamente dito, ou
     * fundamentação.
     */
    private String fundamentacao;

    /**
     * Conjunto de alterações realizadas tanto
     * na entrada (relatos) quanto em pontuações
     * fornecidas automaticamente pelo SAEP.
     *
     * Observe que os valores definidos pelas
     * alterações possuem prioridade sobre os
     * valores "originais".
     */
    private List<Nota> notas;

    public Parecer() {
        this.id = UUID.randomUUID().toString();
    }

    public Parecer(String id,
                   String resolucaoId,
                   List<String> radocsIds,
                   List<Pontuacao> pontuacoes,
                   String fundamentacao,
                   List<Nota> notas) {
        this.id = id;
        this.resolucao = resolucaoId;
        this.radocs = radocsIds;
        this.pontuacoes = pontuacoes;
        this.fundamentacao = fundamentacao;
        this.notas = notas;
    }

    public String getId() {
        return id;
    }

    public String getResolucao() {
        return resolucao;
    }

    public List<String> getRadocs() {
        return radocs;
    }

    public List<Pontuacao> getPontuacoes() {
        return pontuacoes;
    }

    public String getFundamentacao() {
        return fundamentacao;
    }

    public List<Nota> getNotas() {
        return notas;
    }
}
