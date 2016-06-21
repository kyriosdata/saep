/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

/**
 * Abstração dos serviços de persistência de pareceres.
 *
 */
public interface ParecerRepository {

    /**
     * Adiciona a alteração ao parecer.
     *
     * @param parecer O identificador único do parecer.
     *
     * @param alteracao A alteração a ser acrescentada ao
     * pareder.
     */
    void adicionaAlteracao(String parecer, Alteracao alteracao);

    /**
     * Acrescenta o parecer ao repositório.
     *
     * @param parecer O parecer a ser persistido.
     */
    void persisteParecer(Parecer parecer);

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
    void remove(String id);
}
