/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

/**
 * Abstração dos serviços de persistência de RADOC.
 *
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
    Radoc identificadoPor(String identificador);

    /**
     * Conjunto de relatos que formam um RADOC, no
     * formato JSON.
     *
     * <p>Um conjunto de relatos é extraído de fonte
     * externa de informação. Uma cópia é mantida pelo
     * SAEP para consistência de pareceres efetuados ao
     * longo do tempo. Convém ressaltar que informações
     * desses relatórios podem ser alteradas continuamente.
     *
     * @param relatos
     * @return
     */
    String persistir(String relatos);

    /**
     * Remove o RADOC.
     *
     * @param identificador O identificador do RADOC.
     */
    void remove(String identificador);
}
