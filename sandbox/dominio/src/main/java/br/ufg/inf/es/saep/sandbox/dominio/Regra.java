package br.ufg.inf.es.saep.sandbox.dominio;

/**
 * Regra é o mecanismo de definição da forma de
 * avaliação de um item avaliado.
 */
public class Regra {
    private Expressao expressao;
    private double valorMaximo;
    private double valorMinimo;

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
     * @return Valor máximo de pontuação admitido pela regra.
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
     * sem restrição de limites.
     * @param expressao A expressão empregada na
     *                  avaliação da regra.
     */
    public Regra(Expressao expressao) {
        this.expressao = expressao;
    }

    /**
     * Cria regra a partir da expressão e dos valores de limite
     * admitidos.
     *
     * @param expressao A expressão que define o valor da regra.
     *
     * @param valorMaximo O valor máximo admitido pela avaliação da regra.
     * @param valorMinimo O valor mínimo admitido pela avaliação da regra.
     */
    public Regra(Expressao expressao, double valorMaximo, double valorMinimo) {
        this.expressao = expressao;
        this.valorMaximo = valorMaximo;
        this.valorMinimo = valorMinimo;
    }
}
