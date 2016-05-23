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
     * Cria um grupo de itens que pode ser avaliado.
     *
     * @param itens Conjunto de itens contidos no grupo.
     * @param regra     Regra empregado na avaliação do item.
     * @param descricao Descrição do item.
     * @param codigo    Código único do item.
     */
    public Grupo(Set<ItemAvaliado> itens, Regra regra, String descricao, String codigo) {
        super(regra, descricao, codigo);
        this.itens = itens;
    }
}
