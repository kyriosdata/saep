package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.Set;

/**
 * Item de avaliação correspondente a um tipo
 * específico de relato.
 */
public class Tipo extends ItemAvaliado {
    private Set<Atributo> atributos;

    /**
     * Cria tipo definido pelo conjunto de atributos.
     *
     * @param atributos Conjunto de atributos que define o tipo.
     * @param regra     Regra empregado na avaliação do item.
     * @param descricao Descrição do item.
     * @param codigo    Código único do item.
     */
    public Tipo(Set<Atributo> atributos, Regra regra, String descricao, String codigo) {
        super(regra, descricao, codigo);
        this.atributos = atributos;
    }

    /**
     * Recupera conjunto de atributos do tipo.
     *
     * @return O conjunto de atributos que define o
     * tipo.
     */
    public Set<Atributo> getAtributos() {
        return atributos;
    }
}
