/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

/**
 * Mantém valor (pontuação) associado a uma
 * variável.
 *
 * <p>Regras estabelecem a forma como valores
 * são obtidos, enquanto as pontuações retém
 * tais valores.
 *
 */
public class Pontuacao implements Avaliavel {

    /**
     * O nome do identificador da pontuação.
     *
     * <p>Trata-se de chave natural.
     */
    private String atributo;

    /**
     * O valor da pontuação.
     */
    private Valor valor;

    /**
     * Cria uma pontuação.
     *
     * @param nome O nome da pontuação.
     *
     * @param valor O valor da pontuação.
     */
    public Pontuacao(String nome, Valor valor) {

        if (nome == null || nome.isEmpty()) {
            throw new CampoExigidoNaoFornecido("nome");
        }

        if (valor == null) {
            throw new CampoExigidoNaoFornecido("valor");
        }

        this.atributo = nome;
        this.valor = valor;
    }

    /**
     * Recupera o valor da pontuação se o identificador fornecido
     * coincide com aquele da pontuação.
     *
     * @param atributo O identificador único do atributo.
     *
     * @return O valor da pontuação ou {@code null} caso o
     * identificador fornecido seja diferente daquele da pontuação.
     *
     */
    public Valor get(String atributo) {

        if (this.atributo.equals(atributo)) {
            return valor;
        }

        return null;
    }

    /**
     * Recupera o identificador da variável (atributo)
     * da pontuação.
     *
     * @return O identificador da pontuação.
     */
    public String getAtributo() {
        return atributo;
    }

    /**
     * Recupera o valor da pontuação.
     *
     * @return O valor da pontuação.
     */
    public Valor getValor() {
        return valor;
    }
}
