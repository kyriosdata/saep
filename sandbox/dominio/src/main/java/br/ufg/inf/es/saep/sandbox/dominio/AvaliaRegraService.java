package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.List;

/**
 * Created by fabio_000 on 27/05/2016.
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
