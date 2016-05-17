package br.ufg.inf.es.saep.sandbox.avaliador;

import com.udojava.evalex.Expression;

import java.math.BigDecimal;

public class IlustraUso {

    public static final String CONSTANTE_TRIVIAL = "9.67";
    public static final String CONSTANTE_EXPRESSAO = "1 + 1/3";
    public static final String CONSTANTE_LOGICA = "9.67 > 9.66";
    public static final String CONSTANTE_LOGICA_IGUALDADE = "124 == 124";

    public static final String VARIAVEL = "10 * ch";

    public static void main(String[] args) {
        Expression expression = null;
        BigDecimal result = null;

        result = AvaliaExpressao(CONSTANTE_TRIVIAL);
        System.out.println(result);

        result = AvaliaExpressao(CONSTANTE_EXPRESSAO);
        System.out.println(result);

        result = AvaliaExpressao(CONSTANTE_LOGICA);
        System.out.println(result);

        result = AvaliaExpressao(CONSTANTE_LOGICA_IGUALDADE);
        System.out.println(result);

        expression = new Expression(VARIAVEL);
        for (String variavel : expression.getDeclaredOperators()) {
            System.out.println(variavel);
        }
    }

    private static BigDecimal AvaliaExpressao(String expressao) {
        Expression exp = new Expression(expressao);
        return exp.eval();
    }
}
