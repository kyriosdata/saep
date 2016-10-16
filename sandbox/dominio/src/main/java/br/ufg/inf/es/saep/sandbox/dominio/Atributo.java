/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import br.ufg.inf.es.saep.sandbox.dominio.excecoes.CampoExigidoNaoFornecido;
import br.ufg.inf.es.saep.sandbox.dominio.excecoes.TipoDeAtributoInvalido;

/**
 * Um atributo define um nome, uma descrição
 * e um tipo para um dado valor de um relato.
 *
 * <p>Um relato é composto por um conjunto de
 * valores, cada um deles para um atributo.
 *
 * @see Relato
 */
public class Atributo {

    /**
     * O atributo retém um valor lógico
     * ({@code true} ou {@code false}).
     */
    public static final int LOGICO = 0;

    /**
     * O atributo retém um valor do tipo
     * {@code float}.
     */
    public static final int REAL = 1;

    /**
     * O atributo retém uma sequência
     * de caracteres ({@link String}).
     */
    public static final int STRING = 2;

    /**
     * O atributo retém uma data no formato
     * dd/MM/aaaa.
     */
    public static final int DATA = 3;

    /**
     * O nome do atributo, por exemplo,
     * "cha" ou "nome". O nome de um atributo
     * deve ser único em vários contextos, por
     * exemplo, em um dado {@link Classe}, não
     * pode existir mais de um atributo com o
     * mesmo nome.
     *
     * <p>Trata-se de uma chave natural.
     */
    private String nome;

    /**
     * Informação adicional que detalha o uso
     * esperado do tipo (informação de ajuda).
     * Por exemplo, para o Atributo "nome", pode
     * ser algo como "nome completo do periódico,
     * por exemplo, International Journal of Health
     * Informatics.".
     *
     * <p>A descrição não é obrigatória.
     */
    private String descricao;

    /**
     * A identificação do conjunto de valores
     * associado ao atributo. Por exemplo,
     * um atributo "inteiro" significa que
     * apenas valores inteiros podem ser
     * assumidos pelo atributo em questão.
     */
    private int tipo;

    /**
     * Cria um atributo.
     *
     * @param nomeAtributo O nome do atributo.
     *
     * @param descricaoAtributo A descrição do atributo.
     *
     * @param tipoAtributo O tipo do atributo. O tipo define o domínio
     *             de valores que uma instância do atributo
     *             pode assumir.
     */
    public Atributo(final String nomeAtributo,
                    final String descricaoAtributo, final int tipoAtributo) {
        if (nomeAtributo == null || nomeAtributo.isEmpty()) {
            throw new CampoExigidoNaoFornecido("nome");
        }

        if (tipoAtributo < LOGICO || tipoAtributo > DATA) {
            throw new TipoDeAtributoInvalido("Valor de tipo inválido: "
                    + tipoAtributo);
        }

        nome = nomeAtributo;
        descricao = descricaoAtributo;
        tipo = tipoAtributo;
    }

    /**
     * Recupera o nome do atributo.
     *
     * @return O identificador único do atributo.
     */
    public final String getNome() {
        return nome;
    }

    /**
     * Recupera o tipo do atributo.
     *
     * @return O tipo do atributo, ou seja, o
     *      valor {@link #LOGICO}, {@link #REAL} ou
     *      {@link #STRING}.
     */
    public final int getTipo() {
        return tipo;
    }

    @Override
    public final boolean equals(final Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Atributo atributo = (Atributo) other;

        return nome.equals(atributo.nome);
    }

    @Override
    public final int hashCode() {
        return nome.hashCode();
    }

    /**
     * Recupera a descrição do atributo.
     *
     * @return Descrição do atributo.
     */
    public final String getDescricao() {
        return descricao;
    }
}
