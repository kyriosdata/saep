/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

/**
 * Encapsula um valor, que pode ser uma sequência
 * de caracteres, lógico ou um {@code float}.
 *
 * Um relato é descrito por uma coleção de
 * valores, por exemplo, um relato correspondente
 * a um "livro" pode ter atributos como "titulo"
 * e "numeroPaginas", dentre outros. Um valor
 * correspondente para "titulo" de um dado relato
 * pode ser "Amar e ser livre", enquanto o valor
 * para "numeroPaginas" pode ser 209, por exemplo.
 *
 * Uma instância dessa classe é empregada para
 * reter qualquer um desses valores. A recuperação
 * do valor depende do uso do método <b>get</b>
 * correspondente ao tipo. Cabe a quem envia uma
 * mensagem para uma instância de valor fazer
 * uso do método correto.
 */
public class Valor {

    /**
     * Contêiner para o valor numérico
     * da instância.
     */
    private float real;

    /**
     * Contêiner para o valor lógico
     * da instância.
     */
    private boolean logico;

    /**
     * Contêiner para a sequência de caracteres
     * mantida pela instância.
     */
    private String string;

    /**
     * Cria uma instância cujo valor é
     * uma sequência de caracteres.
     *
     * @param valor Sequência de caracteres
     *              do valor.
     */
    public Valor(String valor) {
        this.string = valor;
    }

    /**
     * Cria uma instância cujo valor é
     * um número real.
     *
     * @param valor Número real correspondente
     *              ao valor.
     */
    public Valor(float valor) {
        real = valor;
    }

    /**
     * Cria uma instância cujo valor é
     * lógico.
     *
     * @param valor Valor lógico a ser
     *              retido pela instância.
     */
    public Valor(boolean valor) {
        logico = valor;
    }

    /**
     * Recupera a sequência de caracteres
     * do valor.
     *
     * @return Sequência de caracteres da
     * instância.
     */
    public String getString() {
        return string;
    }

    /**
     * Recupera o valor lógico correspondente
     * à instância.
     *
     * @return O valor {@code true} ou
     * {@code false} correspondente à
     * instância.
     */
    public boolean getBoolean() {
        return logico;
    }

    /**
     * Recupera o valor real (numérico)
     * armazenado na instância.
     *
     * @return O valor numérico correspondente
     * à instância.
     */
    public float getFloat() {
        return real;
    }
}
