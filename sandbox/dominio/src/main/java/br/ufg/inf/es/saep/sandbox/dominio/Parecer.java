/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import br.ufg.inf.es.saep.sandbox.dominio.excecoes.CampoExigidoNaoFornecido;

import java.util.List;
import java.util.UUID;

/**
 * Resultado da avaliação de um relatório
 * ({@link Relatorio}) para uma dada configuração
 * ({@link br.ufg.inf.es.saep.sandbox.dominio.regra.Configuracao}).
 *
 * @see br.ufg.inf.es.saep.sandbox.dominio.regra.Configuracao
 * @see Relatorio
 */
public class Parecer extends Entidade {

    /**
     * Identificação da configuração empregada
     * na produção do parecer.
     */
    private String configuracao;

    /**
     * Lista de relatórios com base nos quais
     * o parecer é realizado. Em muitos casos
     * um único relatório é utilizado.
     */
    private List<String> relatorios;

    /**
     * As pontuações obtidas pelo parecer.
     * Inclui aquelas pontuações que alteram
     * outras.
     */
    private List<Pontuacao> pontuacoes;

    /**
     * Texto associado ao parecer.
     */
    private String fundamentacao;

    /**
     * Conjunto de alterações realizadas tanto
     * na entrada (relatos) quanto em pontuações
     * fornecidas automaticamente pelo SAEP.
     *
     * <p>Observe que os valores definidos pelas
     * alterações possuem prioridade sobre os
     * valores "originais".
     */
    private List<Observacao> observacoes;

    /**
     * Cria instância de parecer.
     *
     * <p>Construtor relevante para construção de parecer
     * a partir de dados em meio persistente.
     *
     * @param id O identificador único do parecer.
     *
     * @param resolucaoId O identificador da resolução na qual
     *                    o parecer se baseia.
     *
     * @param radocsIds A lista de identificadores de RADOCs
     *                  empregados pelo parecer.
     *
     * @param pontuacoes O conjunto de todas as pontuações obtidas
     *                   pelo parecer.
     *
     * @param fundamentacao Texto que fundamenta o parecer.
     *
     * @param observacoes Notas que alteram valores de pontuações e/ou
     *              relatos dos RADOCs empregados.
     */
    public Parecer(String id,
                   String resolucaoId,
                   List<String> radocsIds,
                   List<Pontuacao> pontuacoes,
                   String fundamentacao,
                   List<Observacao> observacoes) {

        super(id);

        if (resolucaoId == null || resolucaoId.isEmpty()) {
            throw new CampoExigidoNaoFornecido("resolucaoId");
        }

        if (radocsIds == null || radocsIds.size() == 0) {
            throw new CampoExigidoNaoFornecido("radocsIds");
        }

        if (pontuacoes == null || pontuacoes.size() == 0) {
            throw new CampoExigidoNaoFornecido("radocsIds");
        }

        this.configuracao = resolucaoId;
        this.relatorios = radocsIds;
        this.pontuacoes = pontuacoes;
        this.fundamentacao = fundamentacao;
        this.observacoes = observacoes;
    }

    /**
     * Cria instância de parecer.
     *
     * <p>Construtor de conveniência para o cenário
     * em que um novo parecer é criado e o identificador
     * correspondente é gerado.
     *
     * @param resolucaoId O identificador da resolução na qual
     *                    o parecer se baseia.
     *
     * @param radocsIds A lista de identificadores de RADOCs
     *                  empregados pelo parecer.
     *
     * @param pontuacoes O conjunto de todas as pontuações obtidas
     *                   pelo parecer.
     *
     * @param fundamentacao Texto que fundamenta o parecer.
     *
     * @param observacoes Notas que alteram valores de pontuações e/ou
     *              relatos dos RADOCs empregados.
     */
    public Parecer(String resolucaoId,
                   List<String> radocsIds,
                   List<Pontuacao> pontuacoes,
                   String fundamentacao,
                   List<Observacao> observacoes) {
        this(UUID.randomUUID().toString(),
                resolucaoId,
                radocsIds,
                pontuacoes,
                fundamentacao,
                observacoes);
    }

    /**
     * Recupera o identificador da resolução utilizada pelo parecer.
     *
     * @return O identificador da resolução utilizada pelo parecer.
     */
    public String getResolucaoId() {
        return configuracao;
    }

    /**
     * Recupera os identificadores dos RADOCs sobre os quais o
     * parecer é elaborado.
     *
     * @return Identificadores de RADOCs.
     */
    public List<String> getRadocsIds() {
        return relatorios;
    }

    /**
     * Recupera os resultados, ou pontuações obtidas na
     * elaboração do parecer.
     *
     * @return Pontuações obtidas pelo parecer.
     */
    public List<Pontuacao> getPontuacoes() {
        return pontuacoes;
    }

    /**
     * Recupera o texto que fundamenta o parecer.
     *
     * @return Texto de fundamentação do parecer.
     */
    public String getFundamentacao() {
        return fundamentacao;
    }

    /**
     * Recupera as observacoes eventualmente registradas pelo parecer.
     *
     * @return Notas utilizadas pelo parecer.
     */
    public List<Observacao> getObservacoes() {
        return observacoes;
    }
}
