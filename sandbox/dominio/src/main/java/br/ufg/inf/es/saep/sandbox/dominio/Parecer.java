/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.List;
import java.util.UUID;

/**
 * Resultado da avaliação de um processo de progressão,
 * promoção ou estágio probatório. Um parecer, portanto,
 * registra o resultado de uma avaliação.
 *
 * <p>Um parecer envolve pelo menos um RADOC avaliado com
 * base em uma resolução. No caso de estágio probatório, por
 * exemplo, vários RADOCs são empregados.
 *
 * <p>Um parecer é obtido da aplicação de regras aos relatos,
 * o que produz pontuações, que também podem ser empregadas
 * por regras. A definição de pontuações é automática e baseada
 * nas regras. A CAD, contudo, no processo de produção de
 * um parecer, pode "substituir" o valor de uma pontuação por
 * outro, assim como também pode interferir no resultado
 * obtido por uma pontuação ao alterar o valor de um
 * relato.
 *
 * @see Relatorio
 */
public class Parecer extends Entidade {

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
     * <p>Observe que os valores definidos pelas
     * alterações possuem prioridade sobre os
     * valores "originais".
     */
    private List<Nota> notas;

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
     * @param notas Notas que alteram valores de pontuações e/ou
     *              relatos dos RADOCs empregados.
     */
    public Parecer(String id,
                   String resolucaoId,
                   List<String> radocsIds,
                   List<Pontuacao> pontuacoes,
                   String fundamentacao,
                   List<Nota> notas) {

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

        this.resolucao = resolucaoId;
        this.radocs = radocsIds;
        this.pontuacoes = pontuacoes;
        this.fundamentacao = fundamentacao;
        this.notas = notas;
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
     * @param notas Notas que alteram valores de pontuações e/ou
     *              relatos dos RADOCs empregados.
     */
    public Parecer(String resolucaoId,
                   List<String> radocsIds,
                   List<Pontuacao> pontuacoes,
                   String fundamentacao,
                   List<Nota> notas) {
        this(UUID.randomUUID().toString(),
                resolucaoId,
                radocsIds,
                pontuacoes,
                fundamentacao,
                notas);
    }

    /**
     * Recupera o identificador da resolução utilizada pelo parecer.
     *
     * @return O identificador da resolução utilizada pelo parecer.
     */
    public String getResolucaoId() {
        return resolucao;
    }

    /**
     * Recupera os identificadores dos RADOCs sobre os quais o
     * parecer é elaborado.
     *
     * @return Identificadores de RADOCs.
     */
    public List<String> getRadocsIds() {
        return radocs;
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
     * Recupera as notas eventualmente registradas pelo parecer.
     *
     * @return Notas utilizadas pelo parecer.
     */
    public List<Nota> getNotas() {
        return notas;
    }
}
