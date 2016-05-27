/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.*;

/**
 * Fornece ORDENAÇÃO topológica a ser seguida
 * para execução das regras.
 *
 * A sequência retornada pelo método {@link #ordena(List)}
 * fornece uma ordem que pode ser empregada para executar
 * os itens avaliados na qual é assegurada que um item é
 * executado apenas se os itens dos quais dependem já
 * foram executados.
 *
 */
public class Ordenacao {

    /**
     * Ordena topologicamente os itens avaliados.
     *
     * @param itens Itens a serem ordenadas.
     *
     * @return Sequência de itens a serem executadas
     * nessa ordem.
     */
    public static List<ItemAvaliado> ordena(List<ItemAvaliado> itens) {
        int size = itens.size();
        List<ItemAvaliado> ordenados = new ArrayList<ItemAvaliado>(size);

        // Um item dá origem a um resultado identificado
        // pelo nome do atributo. Precisamos identificar,
        // no sentido inverso, o item cujo resultado é
        // identificado por um dado nome.
        Map<String, ItemAvaliado> itemPorNome = new HashMap<String, ItemAvaliado>(size);
        Set<String> inseridos = new HashSet<String>(size);

        for(ItemAvaliado item : itens) {
            itemPorNome.put(item.getAtributo().getNome(), item);
        }

        // TODAS OS ITENS SERÃO INSERIDOS
        for (ItemAvaliado item : itens) {
            insereItem(item, itemPorNome, ordenados, inseridos);
        }

        return ordenados;
    }

    private static void insereItem(ItemAvaliado item,
                            Map<String, ItemAvaliado> itemPorNome,
                            List<ItemAvaliado> ordenados,
                            Set<String> inseridos) {

        // Observe que antes de inserir o "item", os
        // itens dos quais esse depende são inseridos
        // primeiro. Ou seja, se "a depende de b", então
        // "a" será inserido após o "b" ser inserido.

        for(Atributo atributo : item.getRegra().getAtributos()) {
            String nome = atributo.getNome();

            // Se esse atributo já faz parte da sequência,
            // então já foi contemplado, passe para o próximo.
            if (inseridos.contains(nome)) {
                continue;
            }

            // Nome não necessariamente identifica um "resultado"
            // de um item. Nesse caso, é uma propriedade de um relato,
            // ou seja, valor fornecido (não obtido por execução de
            // regra).
            if (itemPorNome.containsKey(nome)) {
                insereItem(itemPorNome.get(nome), itemPorNome, ordenados, inseridos);
            }
        }

        // Insere item após componentes
        ordenados.add(item);

        // Acrescenta nome do atributo aos já inseridos
        inseridos.add(item.getAtributo().getNome());
    }
}
