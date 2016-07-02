/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

/**
 * Oferece noção de coleções de pareceres em memória.
 *
 * <p>Um parecer é o resultado produzido pela avaliação
 * de um conjunto de relatos (RADOC) conforme uma dada
 * resolução. O parecer pode ser produzido pela Comissão
 * de Avaliação Docente (CAD) ou automaticamente pelo
 * SAEP.
 *
 * @see Parecer
 * @see Radoc
 */
public interface ParecerRepository {

    /**
     * Adiciona nota ao parecer.
     *
     * @throws IdentificadorDesconhecido Caso o identificador
     * fornecido não identifique um parecer existente.
     *
     * @param parecer O identificador único do parecer.
     *
     * @param nota A alteração a ser acrescentada ao
     * pareder.
     */
    void adicionaNota(String parecer, Nota nota);

    /**
     * Remove a nota cujo item Avaliavel original é
     * fornedido.
     *
     * @param original Instância de {@link Avaliavel} que participa
     *                 da {@link Nota} a ser removida como origem.
     *
     */
    void removeNota(Avaliavel original);

    /**
     * Acrescenta o parecer ao repositório.
     *
     * @throws IdentificadorDesconhecido Caso um
     * identificador único não seja fornecido ou já exista entidade
     * persistida com esse identificador.
     *
     * @param parecer O parecer a ser persistido.
     *
     */
    void persisteParecer(Parecer parecer);

    /**
     * Altera a fundamentação do parecer.
     *
     * <p>Fundamentação é o texto propriamente dito do
     * parecer. Não confunda com as alterações de
     * valores (dados de relatos ou de pontuações).
     *
     * @throws IdentificadorDesconhecido Caso o identificador
     * fornecido não identifique um parecer.
     *
     * @param parecer O identificador único do parecer.
     * @param fundamentacao Novo texto da fundamentação do parecer.
     */
    void atualizaFundamentacao(String parecer, String fundamentacao);

    /**
     * Recupera o parecer pelo identificador.
     *
     * @param id O identificador do parecer.
     *
     * @return O parecer recuperado ou o valor {@code null},
     * caso o identificador não defina um parecer.
     */
    Parecer byId(String id);

    /**
     * Remove o parecer.
     *
     * @param id O identificador único do parecer.
     */
    void removeParecer(String id);

    /**
     * Recupera o RADOC identificado pelo argumento.
     *
     * @param identificador O identificador único do
     *                      RADOC.
     *
     * @return O {@code Radoc} correspondente ao
     * identificador fornecido.
     */
    Radoc radocById(String identificador);

    /**
     * Conjunto de relatos de atividades e produtos
     * associados a um docente.
     *
     * <p>Um conjunto de relatos é extraído de fonte
     * externa de informação. Uma cópia é mantida pelo
     * SAEP para consistência de pareceres efetuados ao
     * longo do tempo. Convém ressaltar que informações
     * desses relatórios podem ser alteradas continuamente.
     *
     * @throws IdentificadorDesconhecido Caso o identificador
     * único do objeto a se persistido não esteja definido ou
     * já exista entidade com esse identificador.
     *
     * @param radoc O conjunto de relatos a ser persistido.
     *
     * @return O identificador único do RADOC ou o valor
     * {@code null}, caso a persistência não seja realizada
     * de forma satisfatória, por exemplo, o identificador
     * já existe.
     */
    String persisteRadoc(Radoc radoc);

    /**
     * Remove o RADOC.
     *
     * <p>Após essa operação o RADOC correspondente não
     * estará disponível para consulta.
     *
     * <p>Não é permitida a remoção de um RADOC para o qual
     * há pelo menos um parecer referenciando-o.
     *
     * @param identificador O identificador do RADOC.
     */
    void removeRadoc(String identificador);
}
