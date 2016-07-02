/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

/**
 * Serviço oferecido por objeto que pode
 * ser avaliado por uma regra.
 *
 * <p>Um objeto que permite recuperar valores
 * de seus atributos por meio de um nome
 * (identificador), é um objeto avaliável.
 */
public interface Avaliavel {

    /**
     * Recupera o valor do atributo para o
     * objeto em questão.
     *
     * @param atributo O identificador único do atributo.
     *
     * @return O valor do atributo.
     */
    Valor get(String atributo);
}
