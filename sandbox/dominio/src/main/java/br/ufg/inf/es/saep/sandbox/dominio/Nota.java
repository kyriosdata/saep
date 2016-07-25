/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

/**
 * Encapsula uma observação sobre um item avaliável,
 * na qual um valor fornecido ou automaticamente gerado
 * é "ignorado" em detrimento de outro, fornecido pela
 * CAD.
 *
 * <p>Observe que o valor original não é alterado, nem
 * substituído por outro. Ou seja, o valor fornecido,
 * por meio de uma nota, é considerado em vez do "original",
 * que não é alterado.
 *
 */
public class Nota {
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
     * @throws AvaliaveisDeTiposDistintos Caso os avaliáveis fornecidos
     *      não sejam do mesmo tipo.
     */
    public Nota(Avaliavel origem, Avaliavel destino, String justificativa) {
        if (origem == null) {
            throw new CampoExigidoNaoFornecido("origem");
        }

        if (destino == null) {
            throw new CampoExigidoNaoFornecido("destino");
        }

        if (justificativa == null) {
            throw new CampoExigidoNaoFornecido("justificativa");
        }

        String classeOrigem = origem.getClass().getName();
        String classeDestino = destino.getClass().getName();
        if (!classeOrigem.equals(classeDestino)) {
            throw new AvaliaveisDeTiposDistintos("tipos distintos");
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
