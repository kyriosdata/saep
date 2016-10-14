/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio.regra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Ordena regras a serem avaliadas.
 *
 * <p>A ordenação obtida assegura que uma regra R só será
 * avaliada após a avaliação das regras que produzem
 * valores dos quais a regra R depende. Por exemplo, se uma
 * regra é definida pela expressão "2 * a", então deve
 * ser avaliada apenas após a avaliação da regra que
 * produz o resultado de "a".
 *
 * <p>A sequência retornada pelo método {@link #ordena(List)}
 * fornece uma ordem que pode ser empregada para executar
 * as regras.
 */
public final class OrdenacaoService {

    /**
     * Evita criação inútil de objeto.
     */
    private OrdenacaoService() {
        // Checkstyle
    }

    /**
     * Ordena topologicamente um conjunto de regras a
     * serem avaliadas.
     *
     * @param regras Regras a serem ordenadas.
     * @return Sequência de itens a serem executadas
     * nessa ordem.
     */
    public static List<Regra> ordena(final List<Regra> regras) {
        int size = regras.size();
        List<Regra> ordenados = new ArrayList<>(size);

        // Uma regra dá origem a um resultado identificado
        // pelo nome da variável da regra. Precisamos
        // identificar, no sentido inverso, a regra cuja
        // variável é identificada por um dado nome.
        Map<String, Regra> regraPorVariavel = new HashMap<>(size);

        for (Regra regra : regras) {
            regraPorVariavel.put(regra.getVariavel(), regra);
        }

        // Mantém aqueles já ordenados em estrutura que otmiza consulta
        Set<String> inseridos = new HashSet<>(size);

        // TODAS AS REGRAS SERÃO INSERIDAS
        for (Regra regra : regras) {
            insereRegrasAposComponentes(
                    regra, regraPorVariavel, ordenados, inseridos);
        }

        return ordenados;
    }

    /**
     * Insere a regra após os componentes da mesma.
     *
     * @param regra A regra a ser inserida.
     *
     * @param regraPorVariavel Dicionário que identifica
     *                         regra pela variável correspondente.
     * @param ordenadas Conjunto de regras ordenadas.
     *
     * @param inseridas Conjunto de regras já inseridas.
     */
    private static void insereRegrasAposComponentes(
            final Regra regra,
            final Map<String, Regra> regraPorVariavel,
            final List<Regra> ordenadas,
            final Set<String> inseridas) {

        // Regra já foi considerada.
        if (inseridas.contains(regra.getVariavel())) {
            return;
        }

        // Observe que antes de inserir o "item", os
        // itens dos quais esse depende são inseridos
        // primeiro. Ou seja, se "a depende de b", então
        // "a" será inserido após o "b" ser inserido.

        for (String variavel : regra.getDependeDe()) {

            // Se esse atributo já está ordenado,
            // não há o que fazer, vá para o próximo.
            if (inseridas.contains(variavel)) {
                continue;
            }

            // Uma variável não necessariamente identifica um "resultado"
            // de uma regra. O nome da variável pode ser o identificador
            // de um atributo de um relato. O teste abaixo considera
            // variáveis definidas por regras.
            if (regraPorVariavel.containsKey(variavel)) {
                Regra regraDaVariavel = regraPorVariavel.get(variavel);
                insereRegrasAposComponentes(regraDaVariavel,
                        regraPorVariavel,
                        ordenadas,
                        inseridas);
            }
        }

        // Insere regra após aquelas das quais depende
        ordenadas.add(regra);

        // Acrescenta variável àquelas já ordenadas
        inseridas.add(regra.getVariavel());
    }
}
