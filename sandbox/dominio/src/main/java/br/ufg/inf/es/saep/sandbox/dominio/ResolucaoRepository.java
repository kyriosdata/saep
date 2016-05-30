/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.List;

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
     * O retorno {@code null} indica que não existe resolução
     * com o identificador fornecido.
     *
     * @see #persiste(Resolucao)
     */
    Resolucao identificadaPor(String identificador);

    /**
     * Persiste uma nova resolução.
     *
     * @param resolucao A resolução a ser persistida.
     *
     * @return O identificador único da resolução, conforme
     * fornecido em propriedade do objeto fornecido. Observe que
     * o método retorna {@code null} para indicar que a
     * operação não foi realizada de forma satisfatória,
     * possivelmente por já existir resolução com
     * identificador semelhante.
     *
     * @see #identificadaPor(String)
     */
    String persiste(Resolucao resolucao);

    /**
     * Remove a resolução com o identificador
     * fornecido.
     *
     * @param identificador O identificador (uso externo) da
     *                      resolução a ser removida.
     *
     * @return O valor {@code true} se a operação foi
     * executada de forma satisfatória e {@code false},
     * caso contrário.
     */
    boolean remove(String identificador);

    /**
     * Recupera a lista dos identificadores das
     * resoluções disponíveis.
     *
     * @return Identificadores das resoluções disponíveis.
     */
    List<String> resolucoes();
}
