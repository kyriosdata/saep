/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import br.ufg.inf.es.saep.sandbox.dominio.excecoes.CampoExigidoNaoFornecido;

import java.util.Set;

/**
 * Um grupo permite reunir classes e
 * outros grupos, formando uma hierarquia.
 *
 * <p>Classes de relato podem estar classificados em
 * um grupo. Por exemplo, "aulas presenciais na graduação"
 * e "aulas do ensino a distância na graduação" são
 * classes do grupo "graduação".
 *
 * <p>Adicionalmente, grupos podem ser formados por
 * outros grupos e classes.
 */
public class Grupo extends Classe {

    /**
     * Reúne os componentes, outros grupos e classes
     * do presente grupo.
     */
    private Set<String> componentes;

    /**
     * Cria um grupo (reunião de componentes).
     *
     * @param codigo    O código do grupo.
     * @param nome      O nome do grupo.
     * @param descricao A descrição do grupo.
     * @param atributos O conjunto de atributos definido para o grupo.
     * @param membros     Conjunto de componentes contidos no grupo.
     * @throws CampoExigidoNaoFornecido Caso o
     *                                           conjunto de componentes seja
     *                                           {@code null} ou não
     *                                           contenha nenhum tipo.
     */
    public Grupo(final String codigo, final String nome,
                 final String descricao,
                 final Set<Atributo> atributos,
                 final Set<String> membros) {
        super(codigo, nome, descricao, atributos);

        if (membros == null || membros.isEmpty()) {
            throw new CampoExigidoNaoFornecido("componentes");
        }

        componentes = membros;
    }

    /**
     * Recupera o conjunto de componentes reunidos pelo grupo.
     *
     * @return Conjunto de componentes.
     */
    public final Set<String> getComponentes() {
        return componentes;
    }
}
