/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import br.ufg.inf.es.saep.sandbox.dominio.excecoes.CampoExigidoNaoFornecido;

/**
 * Mantém valor (pontos) associado a uma
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
     * @param pontos O valor da pontuação.
     */
    public Pontuacao(final String nome, final Valor pontos) {

        if (nome == null || nome.isEmpty()) {
            throw new CampoExigidoNaoFornecido("nome");
        }

        if (pontos == null) {
            throw new CampoExigidoNaoFornecido("valor");
        }

        this.atributo = nome;
        this.valor = pontos;
    }

    /**
     * Recupera o valor da pontuação se o identificador fornecido
     * coincide com aquele da pontuação.
     *
     * @param nome O identificador único do atributo.
     *
     * @return O valor da pontuação ou {@code null} caso o
     *      identificador fornecido seja diferente daquele
     *      da pontuação.
     */
    public final Valor get(final String nome) {

        if (atributo.equals(nome)) {
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
    public final String getAtributo() {
        return atributo;
    }

    /**
     * Recupera o valor da pontuação.
     *
     * @return O valor da pontuação.
     */
    public final Valor getValor() {
        return valor;
    }

    /**
     * Classe de uma pontuação.
     *
     * @return O identificador da classe de toda pontuação.
     */
    public final String getClasse() {
        return "pontuacao";
    }
}
