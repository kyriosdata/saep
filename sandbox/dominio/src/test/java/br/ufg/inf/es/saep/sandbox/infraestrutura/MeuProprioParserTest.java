package br.ufg.inf.es.saep.sandbox.infraestrutura;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class MeuProprioParserTest {

    private static Map<String, Double> variaveis = new HashMap<>();

    @BeforeClass
    public static void setUp() {
        variaveis.put("x", 5d);
        variaveis.put("ch", 5d);
        variaveis.put("t", 2d);
        variaveis.put("numeroMeses", 3d);
    }

    @Test
    public void expressoesSemVariaveis() {
        Map<String,Double> vars = new HashMap<>();

        // Nenhuma variável acrescentada
        AvaliadorExpressao exp;

        exp = new AvaliadorExpressao("2");
        assertEquals(0, exp.getVariaveis().size());

        exp = new AvaliadorExpressao("2/(3-4)");
        assertEquals(0, exp.getVariaveis().size());

        exp = new AvaliadorExpressao("2 / ( 3 - 4 ) ");
        assertEquals(0, exp.getVariaveis().size());

        exp = new AvaliadorExpressao("2 + (3+4)");
        assertEquals(0, exp.getVariaveis().size());

        exp = new AvaliadorExpressao("2-(3-4)");
        assertEquals(0, exp.getVariaveis().size());

        exp = new AvaliadorExpressao("(2-3)-4");
        assertEquals(0, exp.getVariaveis().size());

        exp = new AvaliadorExpressao("(1/2)/(3 * (4*5))");
        assertEquals(0, exp.getVariaveis().size());
    }

    @Test
    public void expressaoComUmaVariavel() {
        AvaliadorExpressao expr = new AvaliadorExpressao("(1-y)-2");
        assertEquals(1, expr.getVariaveis().size());
    }

    @Test
    public void algumasOperacoes() {
        AvaliadorExpressao expr = new AvaliadorExpressao("");
        assertEquals(0, expr.valor(variaveis), 0.001d);

        expr = new AvaliadorExpressao("(x)");
        assertEquals(5, expr.valor(variaveis), 0.001d);

        expr = new AvaliadorExpressao("24.8 / 8");
        assertEquals(3.1, expr.valor(variaveis), 0.001d);

        expr = new AvaliadorExpressao("3.3-0.3");
        assertEquals(3.0, expr.valor(variaveis), 0.001d);

        expr = new AvaliadorExpressao("x+(ch-x)");
        assertEquals(5.0, expr.valor(variaveis), 0.001d);

        expr = new AvaliadorExpressao("(2+1) - 3");
        assertEquals(0, expr.valor(variaveis), 0.001d);

        expr = new AvaliadorExpressao("2 + 1");
        assertEquals(3.0, expr.valor(variaveis), 0.001d);

        expr = new AvaliadorExpressao("t * x");
        assertEquals(10, expr.valor(variaveis), 0.001d);

        expr = new AvaliadorExpressao("3 / numeroMeses");
        assertEquals(1, expr.valor(variaveis), 0.001d);

        expr = new AvaliadorExpressao("((100 / 2) / 10) / 5");
        assertEquals(1, expr.valor(variaveis), 0.001d);

        expr = new AvaliadorExpressao("((80 + 1) / 9) / (3 * (2 / 2))");
        assertEquals(3, expr.valor(variaveis), 0.001d);
    }

    @Test
    public void outrosExemplos() {
        AvaliadorExpressao expr = new AvaliadorExpressao("10 * ch / 32");
        variaveis.put("ch", new Double(256));
        assertEquals(80, expr.valor(variaveis), 0.0001d);

        variaveis.put("ch", new Double(320));
        assertEquals(100, expr.valor(variaveis), 0.0001d);
    }
}
