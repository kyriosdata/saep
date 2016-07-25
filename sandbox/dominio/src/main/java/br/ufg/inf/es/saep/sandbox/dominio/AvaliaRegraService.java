/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.List;
import java.util.Map;

/**
 * Interface a ser implementada por qualquer classe cujas
 * instâncias serão empregadas para a avaliação de regras
 * de progressão, promoção ou estágio probatório na UFG.
 *
 * <p>Observe que a implementação dessa interface não produz
 * um "relatório", mas os valores que serão empregados na
 * produção de um relatório para uma avaliação.
 *
 */
public interface AvaliaRegraService {

    /**
     * Avalia uma regra a partir do contexto (valores disponíveis) e
     * os relatos sobre os quais a regra se baseia.
     *
     * @param regra A regra a ser avaliada.
     *
     * @param contexto Valores dos quais a regra pode depender. Por
     *                 exemplo, se a regra é "10 * a", então o valor
     *                 de "a" deve estar disponível no contexto.
     *
     * @param relatos Conjunto de relatos sobre os quais a avaliação
     *                será realizada. A regra pode fazer uso de atributos
     *                dos relatos ou simplesmente contá-los.
     *
     * @return O valor produzido pela avaliação da regra.
     *
     * @throws FalhaAoAvaliarRegra Não é possível realizar a avaliação
     *      da regra, possivelmente pela ausência de definição de variáveis
     *      das quais depende.
     */
    Valor avalia(Regra regra, Map<String, Valor> contexto, List<Avaliavel> relatos);
}
