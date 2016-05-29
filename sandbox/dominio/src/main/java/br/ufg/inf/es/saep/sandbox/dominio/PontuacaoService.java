/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.List;

/**
 * Interface a ser implementada por serviço
 * que ofereça o cálculo (avaliação) de relatos.
 *
 * A avaliação é realizada conforme a "configuração"
 * (conjunto de itens avaliados) empregada.
 */
public interface PontuacaoService {

    /**
     * Computa resultado da avaliação dos relatos.
     *
     * @param relatos Conjunto de relatos sobre os quais
     *                o cômputo será feito.
     *
     * @return Resultado produzido pela avaliação identificadaPor relatos.
     */
    Resultado calcula(List<Relato> relatos);
}
