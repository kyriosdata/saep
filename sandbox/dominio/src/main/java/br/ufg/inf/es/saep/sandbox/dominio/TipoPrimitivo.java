/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

/**
 * Definição dos conjuntos de valores que podem
 * ser assumidos por um determinado atributo.
 */
public enum TipoPrimitivo {

    /**
     * Domínio binário: true ou false.
     */
    LOGICO,

    /**
     * Subconjunto dos números reais,
     * por exemplo, 0.12, 345.
     */
    REAL,

    /**
     * Sequência de caracteres, por exemplo,
     * "Construção de Software".
     */
    STRING
}
