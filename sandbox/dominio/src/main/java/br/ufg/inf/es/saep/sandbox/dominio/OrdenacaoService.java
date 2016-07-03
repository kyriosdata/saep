/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.*;

/**
 * Fornece ordenação das regras a serem avaliadas.
 * A ordenação assegura que uma regra só será
 * avaliada após a avaliação das regras que produzem
 * valores dos quais ela depende. Por exemplo, se uma
 * regra é definida pela expressão "2 * a", então deve
 * ser avaliada apenas após a avaliação da regra que
 * produz o resultado de "a".
 *
 * <p>A sequência retornada pelo método {@link #ordena(List)}
 * fornece uma ordem que pode ser empregada para executar
 * as regras.
 *
 */
public class OrdenacaoService {

    /**
     * Ordena topologicamente um conjunto de regras a
     * serem avaliadas.
     *
     * @param regras Regras a serem ordenadas.
     *
     * @return Sequência de itens a serem executadas
     * nessa ordem.
     */
    public static List<Regra> ordena(List<Regra> regras) {
        int size = regras.size();
        List<Regra> ordenados = new ArrayList<>(size);

        // Uma regra dá origem a um resultado identificado
        // pelo nome da variável da regra. Precisamos
        // identificar, no sentido inverso, a regra cuja
        // variável é identificada por um dado nome.
        Map<String, Regra> regraPorVariavel = new HashMap<>(size);

        for(Regra regra : regras) {
            regraPorVariavel.put(regra.getVariavel(), regra);
        }

        // Mantém aqueles já ordenados em estrutura que otmiza consulta
        Set<String> inseridos = new HashSet<>(size);

        // TODAS AS REGRAS SERÃO INSERIDAS
        for (Regra regra : regras) {
            insereRegra(regra, regraPorVariavel, ordenados, inseridos);
        }

        return ordenados;
    }

    private static void insereRegra(Regra regra,
                                    Map<String, Regra> regraPorVariavel,
                                    List<Regra> ordenadas,
                                    Set<String> inseridas) {

        // Observe que antes de inserir o "item", os
        // itens dos quais esse depende são inseridos
        // primeiro. Ou seja, se "a depende de b", então
        // "a" será inserido após o "b" ser inserido.

        for(String variavel : regra.getDependeDe()) {

            // Se esse atributo já está ordenado,
            // não há o que fazer, vá para o próximo.
            if (inseridas.contains(variavel)) {
                continue;
            }

            // Uma variável não necessariamente identifica um "resultado"
            // de uma regra. O nome da variável pode ser o identificador
            // de um atributo de um relato.
            if (regraPorVariavel.containsKey(variavel)) {
                Regra regraDaVariavel = regraPorVariavel.get(variavel);
                insereRegra(regraDaVariavel, regraPorVariavel, ordenadas, inseridas);
            }
        }

        // Insere regra após aquelas das quais depende
        ordenadas.add(regra);

        // Acrescenta variável àquelas já ordenadas
        inseridas.add(regra.getVariavel());
    }
}
