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
 * <p>O resultado é uma coleção de "pontuações", valores
 * associados a sequências de caracteres, onde cada uma
 * delas identifica um resultado relevante a ser
 * considerado em uma avaliação.
 * <p>
 * Observe que a implementação dessa interface não produz um
 * "relatório", mas os valores que serão empregados na
 * produção de um relatório para uma avaliação.
 *
 */
public interface AvaliaRegraService {
    Valor avaliaRegra(Regra regra, Map<String, Valor> contexto, List<Avaliavel> relatos);
}
