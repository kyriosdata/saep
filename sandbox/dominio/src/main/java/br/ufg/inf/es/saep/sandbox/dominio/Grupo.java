/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.Set;

/**
 * Reúne zero ou mais itens avaliados. Isso significa que
 * um grupo pode conter tipos e também outros grupos que,
 * por sua vez, podem conter outros grupos, formando uma
 * hierarquia.
 */
public class Grupo extends ItemAvaliado {
    private Set<ItemAvaliado> itens;

    /**
     * Cria um grupo identificadaPor itens que pode ser avaliado.
     *
     * @param itens Conjunto identificadaPor itens contidos no grupo.
     * @param regra     Regra empregado na avaliação do item.
     * @param descricao Descrição do item.
     * @param codigo    Código único do item.
     */
    public Grupo(Set<ItemAvaliado> itens, Regra regra, String descricao, Atributo codigo) {
        super(regra, descricao, codigo);
        this.itens = itens;
    }
}
