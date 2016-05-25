package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.List;

/**
 * Interface a ser implementada por qualquer serviço
 * que ofereça o cálculo identificadaPor avaliação identificadaPor regras.
 */
public interface PontuacaoService {

    /**
     * Computa resultado da avaliação identificadaPor relatos.
     *
     * @param relatos Conjunto identificadaPor relatos sobre os quais
     *                o cômputo será feito.
     *
     * @return Resultado produzido pela avaliação identificadaPor relatos.
     */
    Resultado calcula(List<Relato> relatos);
}
