/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import br.ufg.inf.es.saep.sandbox.dominio.excecoes.AvaliaveisIncompativeis;
import br.ufg.inf.es.saep.sandbox.dominio.excecoes.CampoExigidoNaoFornecido;

/**
 * Definição de um avaliável que deve ser utilizado
 * em detrimento de outro.
 *
 * <p>Uma observação permite "corrigir" eventual
 * equívoco presente em um avaliável sem alterá-lo.
 * Dessa forma, o avaliável original não é alterado,
 * mas associado àquele que o substitui, juntamente
 * com uma justificativa.
 *
 * @see Avaliavel
 *
 */
public class Observacao {

    /**
     * Avaliável de referência que será "substituído".
     * Se o valor é {@code null}, então significa que
     * a observação é uma inserção (acréscimo) de avaliável.
     */
    private Avaliavel original;

    /**
     * Avaliável que "substitui" o avaliável de
     * rereferência.
     */
    private Avaliavel novo;

    /**
     * Justificativa para a "alteração".
     */
    private String justificativa;

    /**
     * Cria uma instância de nota.
     *
     * @param origem O avaliável de origem que é "alterado".
     *
     * @param destino O avaliável que é "substitui" a origem.
     *
     * @param motivo A justificativa da "substituição".
     *
     * @throws CampoExigidoNaoFornecido Caso qualquer um dos argumentos
     *      seja {@code null}.
     *
     * @throws  AvaliaveisIncompativeis os avaliáveis fornecidos
     *      não sejam do mesmo tipo.
     */
    public Observacao(final Avaliavel origem,
                      final Avaliavel destino,
                      final String motivo) {

        if (motivo == null) {
            throw new CampoExigidoNaoFornecido("justificativa");
        }

        if (origem == null && destino == null) {
            throw new CampoExigidoNaoFornecido("origem ou destino");
        }

        original = origem;
        novo = destino;
        justificativa = motivo;

        // Se a observação inclui ou "ignora" um relato,
        // então não existe compatibilidade.

        boolean isAlteracao = origem != null && destino != null;

        if (isAlteracao && !origem.getClass().equals(destino.getClass())) {
            throw new AvaliaveisIncompativeis("tipos distintos");
        }
    }

    /**
     * Recupera o item original associado à nota.
     *
     * @return O item para o qual a nota existe.
     */
    public final Avaliavel getItemOriginal() {
        return original;
    }

    /**
     * Recupera o item que "substitui" o item original
     * para efeito de avaliações.
     *
     * @return O item que "substitui" o item original.
     */
    public final Avaliavel getItemNovo() {
        return novo;
    }

    /**
     * Recupera a justificativa para a nota.
     *
     * @return A justificativa da nota.
     */
    public final String getJustificativa() {
        return justificativa;
    }

    /**
     * Verifica se a observação representa o acréscimo,
     * inserção de um relato.
     *
     * @return {@code true} se a observação representa
     * um acréscimo e {@code false}, caso o relato represente
     * uma "substituição" de relato existente.
     */
    public final boolean isInsercao() {
        return original == null;
    }

    /**
     * Verifica se a observação "remove" um relato existente.
     *
     * @return {@code true} se a observação remove um relato.
     */
    public final boolean isRemocao() {
        return novo == null;
    }
}
