/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Serviço de acesso às informações de configuração das regras
 * do SAEP.
 */
public class Regras {

    private List<Integer> tipos = new ArrayList<Integer>();
    private List<Float> pontos = new ArrayList<Float>();
    private List<String> sentencas = new ArrayList<String>();
    private List<String> variaveis = new ArrayList<String>();

    /**
     * Define lista de variáveis que devem ser definidas para se avaliar
     * uma dada variável.
     */
    private Map<Integer, List<String>> dependencias = new HashMap<Integer, List<String>>();

    public Regras() {
        // CODIGO: 0
        tipos.add(0); // pontos por relato
        pontos.add(1.1f);
        sentencas.add("");
        variaveis.add("x");
        dependencias.put(0, new ArrayList<String>(0));

        // CODIGO: 1
        tipos.add(1); // pontos por relato
        pontos.add(0f);
        sentencas.add("8.97");
        variaveis.add("y");
        dependencias.put(1, new ArrayList<String>(0));

        // CODIGO: 2
        tipos.add(1); // pontos por relato
        pontos.add(0f);
        sentencas.add("x + y");
        variaveis.add("z");
        ArrayList<String> utilizadas = new ArrayList<String>(0);
        utilizadas.add("x");
        utilizadas.add("y");
        dependencias.put(2, utilizadas);
    }

    /**
     * Recupera o tipo da sentença.
     *
     * @param codigo O código da sentença.
     *
     * @return O inteiro que identifica o tipo
     * da sentença cujo código é fornecido.
     */
    public int getTipo(int codigo) {
        return tipos.get(codigo);
    }

    /**
     * Recupera a quantidade de pontos atribuída
     * para cada relato.
     *
     * @param codigo O código único da sentença que
     *               avalia o tipo de relato em
     *               questão.
     *
     * @return Total de pontos atribuído para cada
     * relato avaliada pela sentença cujo código é
     * fornecido.
     */
    public float getPontosPorRelato(int codigo) {
        return pontos.get(codigo);
    }

    /**
     * Recupera a sentença correspondente ao código.
     *
     * @param codigo O código da sentença.
     *
     * @return A sentença ou expressão associada ao código.
     */
    public String getSentenca(int codigo) {
        return sentencas.get(codigo);
    }

    /**
     * Recupera o nome da variável associado ao resultado
     * da avaliação da sentença cujo código é fornecido.
     *
     * @param codigo O código único da sentença.
     *
     * @return O nome da variável.
     */
    public String getVariavel(int codigo) {
        return variaveis.get(codigo);
    }

    public List<String> getDependencias(int codigo) {
        return dependencias.get(codigo);
    }
}
