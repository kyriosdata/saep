/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

/**
 * Serviços oferecidos para abstração da implementação da
 * persistência de resoluções.
 *
 */
public interface ResolucaoRepository {

    /**
     * Recupera a instância de {@code Resolucao} correspondente
     * ao identificador.
     *
     * @param identificador O identificador único da resolução a
     *                      ser recuperada.
     *
     * @return {@code Resolucao} identificada por {@code identificador}.
     */
    Resolucao identificadaPor(String identificador);
}
