package br.ufg.inf.es.saep.sandbox.avaliador;

import br.ufg.inf.es.saep.sandbox.dominio.AvaliaRegraService;
import br.ufg.inf.es.saep.sandbox.dominio.Pontuacao;
import br.ufg.inf.es.saep.sandbox.dominio.Regra;
import br.ufg.inf.es.saep.sandbox.dominio.Relato;
import com.udojava.evalex.Expression;

import java.math.BigDecimal;
import java.util.List;

/**
 * Implementação do serviço de avaliação de regra usando
 * Java Expression Evaluator (https://github.com/uklimaschewski/EvalEx).
 */
public class Avaliador implements AvaliaRegraService {
    public Pontuacao avalia(List<Relato> relatos, Regra regra) {
        return null;
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
}
