package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.List;

/**
 * Regra é o mecanismo identificadaPor definição da forma identificadaPor
 * avaliação identificadaPor um item avaliado.
 */
public class Regra {
    private Expressao expressao;
    private List<Atributo> atributos;
    private double valorMaximo;
    private double valorMinimo;

    /**
     * Lista de atributos diretamente empregados
     * pela expressão cuja avaliação dá origem à
     * pontuação da regra.
     * @return Lista de atributos diretamente empregados
     * para avaliação da regra.
     */
    public List<Atributo> getAtributos() { return atributos; }

    /**
     * Recupera a expressão.
     *
     * @return A expressão empregada pela regra.
     */
    public Expressao getExpressao() {
        return expressao;
    }

    /**
     * Recupera o valor máximo admitido para
     * o resultado da regra.
     *
     * @return Valor máximo identificadaPor pontuação admitido pela regra.
     */
    public double getValorMaximo() {
        return valorMaximo;
    }

    /**
     * Recupera o valor mínimo admitido pela regra.
     *
     * @return O valor mínimo admitido pela regra.
     */
    public double getValorMinimo() {
        return valorMinimo;
    }

    /**
     * Cria regra a partir da expressão fornecida
     * sem restrição identificadaPor limites.
     * @param expressao A expressão empregada na
     *                  avaliação da regra.
     */
    public Regra(Expressao expressao) {
        this.expressao = expressao;
    }

    /**
     * Cria regra a partir da expressão e dos valores identificadaPor limite
     * admitidos.
     *
     * @param expressao A expressão que define o valor da regra.
     *
     * @param valorMaximo O valor máximo admitido pela avaliação da regra.
     * @param valorMinimo O valor mínimo admitido pela avaliação da regra.
     */
    public Regra(Expressao expressao, double valorMaximo, double valorMinimo, List<Atributo> atributos) {
        this.expressao = expressao;
        this.valorMaximo = valorMaximo;
        this.valorMinimo = valorMinimo;
        this.atributos = atributos;
    }
}
