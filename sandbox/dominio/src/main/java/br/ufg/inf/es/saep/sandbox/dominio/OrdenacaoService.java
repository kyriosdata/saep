/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.*;

/**
 * Fornece ordenação dos itens avaliados, ordenação
 * topológica, que assegura que uma regra só será
 * avaliada se os valores dos quais depende já estão
 * disponíveis.
 *
 * A sequência retornada pelo método {@link #ordena(List)}
 * fornece uma ordem que pode ser empregada para executar
 * os itens avaliados na qual é assegurada que um item é
 * executado apenas se os itens dos quais dependem já
 * foram executados.
 *
 */
public class OrdenacaoService {

    /**
     * Ordena topologicamente um conjunto de itens a serem
     * ordenados.
     *
     * @param itens Itens a serem ordenadas.
     *
     * @return Sequência de itens a serem executadas
     * nessa ordem.
     */
    public static List<Regra> ordena(List<Regra> itens) {
        int size = itens.size();
        List<Regra> ordenados = new ArrayList<Regra>(size);

        // Um item dá origem a um resultado identificado
        // pelo nome do atributo. Precisamos identificar,
        // no sentido inverso, o item cujo resultado é
        // identificado por um dado nome.
        Map<String, Regra> itemPorNome = new HashMap<String, Regra>(size);
        Set<String> inseridos = new HashSet<String>(size);

        for(Regra item : itens) {
            itemPorNome.put(item.getVariavel(), item);
        }

        // TODAS OS ITENS SERÃO INSERIDOS
        for (Regra item : itens) {
            insereItem(item, itemPorNome, ordenados, inseridos);
        }

        return ordenados;
    }

    private static void insereItem(Regra item,
                            Map<String, Regra> itemPorNome,
                            List<Regra> ordenados,
                            Set<String> inseridos) {

        // Observe que antes de inserir o "item", os
        // itens dos quais esse depende são inseridos
        // primeiro. Ou seja, se "a depende de b", então
        // "a" será inserido após o "b" ser inserido.

        for(String atributo : item.getDependeDe()) {

            // Se esse atributo já faz parte da sequência,
            // então já foi contemplado, passe para o próximo.
            if (inseridos.contains(atributo)) {
                continue;
            }

            // Nome não necessariamente identifica um "resultado"
            // de um item. Nesse caso, é uma propriedade de um relato,
            // ou seja, valor fornecido (não obtido por execução de
            // regra).
            if (itemPorNome.containsKey(atributo)) {
                insereItem(itemPorNome.get(atributo), itemPorNome, ordenados, inseridos);
            }
        }

        // Insere item após componentes
        ordenados.add(item);

        // Acrescenta nome do atributo aos já inseridos
        inseridos.add(item.getVariavel());
    }
}
