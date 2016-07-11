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
     * Adiciona nota ao parecer. Caso a nota a ser acrescentada
     * se refira a um item {@link Avaliavel} para o qual já
     * exista uma nota, então a corrente substitui a anterior.
     *
     * @throws IdentificadorDesconhecido Caso o identificador
     * fornecido não identifique um parecer existente.
     *
     * @param id O identificador único do parecer.
     *
     * @param nota A alteração a ser acrescentada ao
     * pareder.
     */
    void adicionaNota(String id, Nota nota);

    /**
     * Remove a nota cujo item {@link Avaliavel} original é
     * fornedido.
     *
     * @param id O identificador único do parecer.
     * @param original Instância de {@link Avaliavel} que participa
     *                 da {@link Nota} a ser removida como origem.
     *
     */
    void removeNota(String id, Avaliavel original);

    /**
     * Acrescenta o parecer ao repositório.
     *
     * @throws IdentificadorExistente Caso o
     * identificador seja empregado por parecer
     * existente (já persistido).
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
     * <p>Após a chamada a esse método, o parecer alterado
     * pode ser recuperado pelo método {@link #byId(String)}.
     * Observe que uma instância disponível antes dessa chamada
     * torna-se "inválida".
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
     * <p>Se o identificador fornecido é inválido
     * ou não correspondente a um parecer existente,
     * nenhuma situação excepcional é gerada.
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
     * @throws IdentificadorExistente Caso o identificador
     * do objeto a ser persistido seja empregado por
     * RADOC existente.
     *
     * @param radoc O conjunto de relatos a ser persistido.
     *
     * @return O identificador único do RADOC.
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
     * @throws ExisteParecerReferenciandoRadoc Caso exista pelo
     * menos um parecer que faz referência para o RADOC cuja
     * remoção foi requisitada.
     *
     * @param identificador O identificador do RADOC.
     */
    void removeRadoc(String identificador);
}
