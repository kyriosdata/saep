/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

/**
 * Um atributo permite identificar cada um
 * dos possíveis valores que podem ser empregados
 * na descrição de um relato e/ou em uma expressão.
 *
 * Um atributo define um nome e um tipo que
 * define o conjunto de valores que uma
 * "variável" com esse nome pode assumir.
 *
 * Um relato é composto por um conjunto de
 * valores, cada um deles para um atributo.
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
     * O nome do atributo, por exemplo,
     * "cha" ou "nome". O nome de um atributo
     * deve ser único em vários contextos, por
     * exemplo, em um dado {@link Tipo}, não
     * pode existir mais de um atributo com o
     * mesmo nome.
     *
     * <p>Trata-se de uma chave natural.
     */
    private String nome;

    /**
     * A identificação do conjunto de valores
     * associado ao atributo. Por exemplo,
     * um atributo "inteiro" significa que
     * apenas valores inteiros podem ser
     * assumidos pelo atributo em questão.
     */
    private int tipo;

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

    public Atributo(String nome, String descricao, int tipo) {
        if (nome == null || nome.isEmpty()) {
            throw new CampoExigidoNaoFornecido("nome");
        }

        if (tipo < LOGICO || tipo > STRING) {
            throw new TipoDeAtributoInvalido("tipo");
        }

        this.nome = nome;
        this.descricao = descricao;
        this.tipo = tipo;
    }

    /**
     * Recupera o nome do atributo.
     *
     * @return O identificador único do atributo.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Recupera o tipo do atributo
     *
     * @return O tipo do atributo, ou seja, o
     * valor {@link #LOGICO}, {@link #REAL} ou
     * {@link #STRING}.
     */
    public int getTipo() {
        return tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Atributo atributo = (Atributo) o;

        return nome.equals(atributo.nome);
    }

    @Override
    public int hashCode() {
        return nome.hashCode();
    }

    /**
     * Recupera a descrição do atributo.
     *
     * @return Descrição do atributo.
     */
    public String getDescricao() {
        return descricao;
    }
}
