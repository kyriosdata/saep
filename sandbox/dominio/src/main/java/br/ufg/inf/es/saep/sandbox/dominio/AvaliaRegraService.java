package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.List;

/**
 * Interface de operações oferecidas por um serviço
 * que avalia uma regra.
 */
public interface AvaliaRegraService {

    /**
     * Avalia a regra para o conjunto de relatos fornecido.
     *
     * @param relatos O conjunto de relatos sobre o qual a
     *                regra será avaliada.
     *
     * @param regra Regra que será empregada para avaliar
     *              os relatos.
     *
     * @return Pontuação resultado da avaliação da regra.
     */
    Pontuacao avalia(List<Relato> relatos, Regra regra);
}
