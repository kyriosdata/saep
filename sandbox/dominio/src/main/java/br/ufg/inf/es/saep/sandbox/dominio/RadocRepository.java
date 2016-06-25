/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

/**
 * Abstração dos serviços de persistência de RADOC.
 *
 * <p>Um RADOC é uma coleção de relatos. Os serviços
 * não incluem a edição de um RADOC, processo realizado
 * ao longo de um ano letivo, onde as informações são
 * fornecidas a vários sistemas distintos.
 *
 * <p>O registro de um RADOC pelo SAEP é para
 * assegurar que mudanças externas nas informações
 * que compõem um RADOC não tornem inconsistentes
 * os pareceres correspondentes.
 *
 * @see Radoc
 */
public interface RadocRepository {

    /**
     * Recupera o RADOC identificado pelo argumento.
     *
     * @param identificador O identificador único do
     *                      RADOC.
     *
     * @return O {@code Radoc} correspondente ao
     * identificador fornecido.
     */
    Radoc byId(String identificador);

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
     * @return O identificador único do RADOC ou o valor
     * {@code null}, caso a persistência não seja realizada
     * de forma satisfatória, por exemplo, o identificador
     * já existe.
     */
    String persistir(Radoc radoc);

    /**
     * Remove o RADOC.
     *
     * <p>Após essa operação o RADOC correspondente não
     * estará disponível para consulta.
     *
     * @param identificador O identificador do RADOC.
     */
    void remove(String identificador);
}
