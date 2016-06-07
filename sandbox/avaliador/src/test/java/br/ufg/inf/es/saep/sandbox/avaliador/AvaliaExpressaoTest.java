package br.ufg.inf.es.saep.sandbox.avaliador;

import br.ufg.inf.es.saep.sandbox.dominio.Resolucao;
import com.udojava.evalex.Expression;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Experimentação com Java Expression Evaluator
 * (https://github.com/uklimaschewski/EvalEx).
 */
public class AvaliaExpressaoTest {

    @Test
    public void avaliaConstanteTrivial() {
        Expression exp = new Expression("9.67");
        BigDecimal resultado = exp.eval();
        assertEquals(9.67, resultado.doubleValue(), 0.0001d);
    }

    @Test
    public void avaliaConstanteExpressao() {
        Expression exp = new Expression("12.3 - 0.9 / 3");
        BigDecimal resultado = exp.eval();
        assertEquals(12, resultado.doubleValue(), 0.0001d);
    }

    @Test
    public void avaliaExpressaoComVariavel() {
        Expression exp = new Expression("10 * ch");
        exp.setVariable("ch", new BigDecimal(5));
        BigDecimal resultado = exp.eval();
        assertEquals(50, resultado.doubleValue(), 0.0001d);
    }
}
