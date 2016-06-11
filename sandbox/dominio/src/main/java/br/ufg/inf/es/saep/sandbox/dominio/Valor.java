package br.ufg.inf.es.saep.sandbox.dominio;

/**
 * Encapsula um valor para um dos tipos
 * de atributo.
 *
 * O código que cria uma instância dessa classe
 * é responsável por chamar o método get
 * correspondente.
 */
public class Valor {

    /**
     * O nome e o tipo do valor.
     */
    private Atributo atributo;

    /**
     * Contêiner para o valor numérico
     * da instância.
     */
    private double real;

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
    public Valor(Atributo atributo, String valor) {
        this.atributo = atributo;
        this.string = valor;
    }

    /**
     * Cria uma instância cujo valor é
     * um número real.
     *
     * @param valor Número real correspondente
     *              ao valor.
     */
    public Valor(Atributo atributo, double valor) {
        this.atributo = atributo;
        real = valor;
    }

    /**
     * Cria uma instância cujo valor é
     * lógico.
     *
     * @param valor Valor lógico a ser
     *              retido pela instância.
     */
    public Valor(Atributo atributo, boolean valor) {
        this.atributo = atributo;
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
    public boolean getLogico() {
        return logico;
    }

    /**
     * Recupera o valor real (numérico)
     * armazenado na instância.
     *
     * @return O valor numérico correspondente
     * à instância.
     */
    public double getReal() {
        return real;
    }

    /**
     * Recupera o atributo do valor.
     *
     * @return O atributo que define o nome
     * e o tipo do valor.
     */
    public Atributo getAtributo() {
        return atributo;
    }
}
