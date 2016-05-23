package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.List;

/**
 * Interface a ser implementada por qualquer serviço
 * que ofereça o cálculo de avaliação de regras.
 */
public interface PontuacaoService {

    /**
     * Computa resultado da avaliação de relatos.
     *
     * @param relatos Conjunto de relatos sobre os quais
     *                o cômputo será feito.
     *
     * @return Resultado produzido pela avaliação de relatos.
     */
    Resultado calcula(List<Relato> relatos);
}
