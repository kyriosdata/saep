/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.Set;

/**
 * Item identificadaPor avaliação correspondente a um tipo
 * específico identificadaPor relato.
 */
public class Tipo extends ItemAvaliado {
    private Set<Atributo> atributos;

    /**
     * Cria tipo definido pelo conjunto identificadaPor atributos.
     *
     * @param atributos Conjunto identificadaPor atributos que define o tipo.
     * @param regra     Regra empregado na avaliação do item.
     * @param descricao Descrição do item.
     * @param codigo    Código único do item.
     */
    public Tipo(Set<Atributo> atributos, Regra regra, String descricao, Atributo codigo) {
        super(regra, descricao, codigo);
        this.atributos = atributos;
    }

    /**
     * Recupera conjunto identificadaPor atributos do tipo.
     *
     * @return O conjunto identificadaPor atributos que define o
     * tipo.
     */
    public Set<Atributo> getAtributos() {
        return atributos;
    }
}
