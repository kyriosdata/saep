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
    private Avaliavel original;
    private Avaliavel novo;
    private String justificativa;

    /**
     * Cria uma instância de nota.
     *
     * @param origem O avaliável de origem que é "alterado".
     *
     * @param destino O avaliável que é "substitui" a origem.
     *
     * @param justificativa A justificativa da "substituição".
     *
     * @throws CampoExigidoNaoFornecido Caso qualquer um dos argumentos
     *      seja {@code null}.
     *
     * @throws AvaliaveisIncompativeis Caso os avaliáveis fornecidos
     *      não sejam do mesmo tipo.
     */
    public Observacao(Avaliavel origem, Avaliavel destino, String justificativa) {
        if (origem == null) {
            throw new CampoExigidoNaoFornecido("origem");
        }

        if (destino == null) {
            throw new CampoExigidoNaoFornecido("destino");
        }

        if (justificativa == null) {
            throw new CampoExigidoNaoFornecido("justificativa");
        }

        if (!origem.getClass().equals(destino.getClass())) {
            throw new AvaliaveisIncompativeis("tipos distintos");
        }

        this.original = origem;
        this.novo = destino;
        this.justificativa = justificativa;
    }

    /**
     * Recupera o item original associado à nota.
     *
     * @return O item para o qual a nota existe.
     */
    public Avaliavel getItemOriginal() {
        return original;
    }

    /**
     * Recupera o item que "substitui" o item original
     * para efeito de avaliações.
     *
     * @return O item que "substitui" o item original.
     */
    public Avaliavel getItemNovo() {
        return novo;
    }

    /**
     * Recupera a justificativa para a nota.
     *
     * @return A justificativa da nota.
     */
    public String getJustificativa() {
        return justificativa;
    }
}
