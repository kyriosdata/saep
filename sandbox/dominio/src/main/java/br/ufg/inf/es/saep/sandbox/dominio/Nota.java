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
 * substituído por outro. Ou seja, o valor fornecido
 * por meio de uma Nota é considerado,
 * em vez do "original". Uma nota permite claramente
 * identificar essas "alterações".
 *
 */
public class Nota {
    private Avaliavel original;
    private Avaliavel novo;
    private String justificativa;

    public Nota(Avaliavel origem, Avaliavel destino, String justificativa) {
        this.original = origem;
        this.novo = origem;
        this.justificativa = justificativa;
    }
}
