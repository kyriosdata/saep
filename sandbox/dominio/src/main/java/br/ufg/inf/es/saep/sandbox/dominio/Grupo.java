/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.Set;

/**
 * Um grupo permite reunir tipos e grupos, formando
 * uma hierarquia.
 *
 * <p>Tipos de relato podem estar classificados em
 * um grupo. Por exemplo, "aulas presenciais na graduação"
 * e "aulas do ensino a distância na graduação" são
 * tipos do grupo "graduação".
 *
 * <p>Adicionalmente, grupos podem ser formados por
 * outros grupos e tipos.
 */
public class Grupo extends Tipo {

    private Set<String> tipos;

    /**
     * Cria um grupo (reunião de tipos).
     *
     * @param codigo    O código do grupo.
     * @param nome      O nome do grupo.
     * @param descricao A descrição do grupo.
     * @param atributos O conjunto de atributos definido para o grupo.
     * @param tipos     Conjunto de tipos contidos no grupo.
     * @throws CampoExigidoNaoFornecido Caso o
     *                                           conjunto de tipos seja
     *                                           {@code null} ou não
     *                                           contenha nenhum tipo.
     */
    public Grupo(String codigo, String nome,
                 String descricao,
                 Set<Atributo> atributos,
                 Set<String> tipos) {
        super(codigo, nome, descricao, atributos);

        if (tipos == null || tipos.isEmpty()) {
            throw new CampoExigidoNaoFornecido("tipos");
        }

        this.tipos = tipos;
    }

    /**
     * Recupera o conjunto de tipos reunidos pelo grupo.
     *
     * @return Conjunto de tipos.
     */
    public Set<String> getTipos() {
        return tipos;
    }
}
