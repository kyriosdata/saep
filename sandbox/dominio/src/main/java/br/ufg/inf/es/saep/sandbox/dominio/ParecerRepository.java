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
     * Acrescenta o parecer ao repositório.
     *
     * @param parecer O parecer a ser persistido.
     *
     * @throws IdentificadorExistente Caso o
     *      identificador seja empregado por parecer
     *      existente (já persistido).
     */
    void persisteParecer(Parecer parecer);

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
     * Recupera o parecer pelo identificador.
     *
     * @param id O identificador do parecer.
     *
     * @return O parecer recuperado ou o valor {@code null},
     *      caso o identificador não defina um parecer.
     */
    Parecer parecerById(String id);

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
     * @param radoc O conjunto de relatos a ser persistido.
     *
     * @return O identificador único do RADOC.
     *
     * @throws IdentificadorExistente Caso o identificador
     *      do objeto a ser persistido seja empregado por
     *      RADOC existente.
     *

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
     *
     * @throws ExisteParecerReferenciandoRadoc Caso exista pelo
     *      menos um parecer que faz referência para o RADOC cuja
     *      remoção foi requisitada.
     */
    void removeRadoc(String identificador);

    /**
     * Recupera o RADOC identificado pelo argumento.
     *
     * @param identificador O identificador único do
     *                      RADOC.
     *
     * @return O {@code Radoc} correspondente ao
     *      identificador fornecido.
     */
    Radoc radocById(String identificador);
}
