package br.ufg.inf.es.saep.sandbox.avaliador;

import com.udojava.evalex.Expression;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

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

        // Deve ser conhecido que "ch" é empregada pela expressão.
        expression = new Expression(VARIAVEL).with("ch", new BigDecimal(3.4));
        System.out.println(expression.eval());

        expression = new Expression("n * m");
        System.out.println(somatorio(1, 10, expression));
    }

    private static BigDecimal somatorio(int limiteInferior, int limiteSuperior, Expression expr) {
        BigDecimal acumulador = new BigDecimal(0);
        for (int i = limiteInferior; i <= limiteSuperior; i++) {

            // DEFINE VALORES DE TODOS OS TERMOS DA EXPRESSÃO
            // PARA O ÍNDICE i
            expr.setVariable("i", new BigDecimal(i));
            expr.setVariable("n", new BigDecimal(1));
            expr.setVariable("m", new BigDecimal(1));

            BigDecimal exprResult = expr.eval();
            acumulador = acumulador.add(exprResult);
        }

        return acumulador;
    }

    private static void ExibirTokens(Expression expression) {
        Iterator<String> tokens = expression.getExpressionTokenizer();
        while (tokens.hasNext()) {
            System.out.print(tokens.next() + ",");
        }
    }

    private static BigDecimal AvaliaExpressao(String expressao) {
        Expression exp = new Expression(expressao);
        return exp.eval();
    }
}
