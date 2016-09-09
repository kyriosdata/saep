package br.ufg.inf.es.saep.sandbox.infraestrutura;

import com.udojava.evalex.Expression;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Experimentação com Java Expression Evaluator
 * (https://github.com/uklimaschewski/EvalEx).
 */
public class AvaliaExpressaoTest {

    @Test
    public void avaliaSomatorio() {
        // Recuperar dependeDeRegra1_1 expressão do somatório
        String somatorio = "k * (resultadoRegra1 + y)";

        // Recupera variáveis empregadas pela expressão
        List<String> variaveis = new ArrayList<>(2);
        variaveis.add("resultadoRegra1");
        variaveis.add("y");

        // Recuperar conjunto de relatos pertinentes (digamos 3)
        Map<String, Double> relato1 = new HashMap<>(2);
        Map<String, Double> relato2 = new HashMap<>(2);
        Map<String, Double> relato3 = new HashMap<>(2);
        List<Map<String, Double>> relatos = new ArrayList<>(3);
        relatos.add(relato1);
        relatos.add(relato2);
        relatos.add(relato3);

        relato1.put("resultadoRegra1", 1.0);
        relato1.put("y", 2.0);
        relato2.put("resultadoRegra1", 3.0);
        relato2.put("y", 4.0);
        relato3.put("resultadoRegra1", 5.0);
        relato3.put("y", 6.0);

        Expression exp = new Expression(somatorio).with("k", new BigDecimal(2));
        BigDecimal soma = new BigDecimal(0);
        for (Map<String, Double> relato : relatos) {

            // Define contexto para o relato
            for(String variavel : variaveis) {
                exp.setVariable(variavel, new BigDecimal(relato.get(variavel)));
            }

            // Avalie dependeDeRegra1_1 expressão e acumule o resultado
            soma = soma.add(exp.eval());
        }

        assertEquals(42, soma.doubleValue(), 0.0001d);
    }

    @Test
    public void avaliaMediaSimples() {
        // Recupera variável
        String variavel = "resultadoRegra1";

        // Conjunto de relatos sobre dependeDeRegra1_1 qual dependeDeRegra1_1 média é realizada
        // Recuperar conjunto de relatos pertinentes (digamos 3)
        Map<String, Double> relato1 = new HashMap<>(1);
        relato1.put("resultadoRegra1", 1.0);

        Map<String, Double> relato2 = new HashMap<>(1);
        relato2.put("resultadoRegra1", 2.0);

        Map<String, Double> relato3 = new HashMap<>(1);
        relato3.put("resultadoRegra1", 3.0);

        List<Map<String, Double>> relatos = new ArrayList<>(3);
        relatos.add(relato1);
        relatos.add(relato2);
        relatos.add(relato3);

        double soma = 0;
        for(Map<String, Double> relato : relatos) {
            soma = soma + relato.get(variavel);
        }

        double media = soma / relatos.size();

        assertEquals(2.0, media, 0.0001d);
    }

    @Test
    public void avaliaConstanteTrivial() {
        // Recupera tipo: expressão
        // Recupera expressão dependeDeRegra1_1 ser avaliada.
        Expression exp = new Expression("9.67");
        BigDecimal resultado = exp.eval();
        assertEquals(9.67, resultado.doubleValue(), 0.0001d);
    }

    @Test
    public void avaliaConstanteExpressao() {
        // Recupera tipo: expressão
        // Recupera expressão dependeDeRegra1_1 ser avaliada
        Expression exp = new Expression("12.3 - 0.9 / 3");
        BigDecimal resultado = exp.eval();
        assertEquals(12, resultado.doubleValue(), 0.0001d);
    }

    @Test
    public void avaliaExpressaoComVariaveis() {
        // Recupera tipo: expressão
        // Recupera dependeDeRegra1_1 expressão
        Expression exp = new Expression("10 * (ch + resultadoRegra1)");

        // Define o contexto (ch e resultadoRegra1)
        exp.setVariable("ch", new BigDecimal(4));
        exp.setVariable("resultadoRegra1", new BigDecimal(1));

        // Requisita avaliação e confere o resultado
        BigDecimal resultado = exp.eval();
        assertEquals(50, resultado.doubleValue(), 0.0001d);

        exp.setVariable("resultadoRegra1", new BigDecimal(2));
        BigDecimal resultadoNovo = exp.eval();
        assertEquals(60, resultadoNovo.doubleValue(), 0.0001d);
    }

    @Test
    public void avaliaExpressaoPontosPorRelato() {
        // Recupera tipo: pontos por relato
        // Recupera pontuação por relato
        double pontosPorRelato = 10.2;

        // Recupera dependeDeRegra1_1 cardinalidade do conjunto em questão
        int totalRelatos = 5;

        double resposta = totalRelatos * pontosPorRelato;

        assertEquals(51, resposta, 0.0001d);
    }

    @Test
    public void funcaoPersonalizada() {
        Expression f = new Expression("10 * diferencaEmDias");

        // Ajusta parâmetros para todas as funções
        // Pode ser otimizado (apenas para aquelas usadas)
        // Localiza variáveis associadas dependeDeRegra1_1 diferencaEmDias
        // (por exemplo, 'd1' e 'd2'). Nesse caso, recupere
        // os valores desses atributos, calcule dependeDeRegra1_1 diferença e
        // deposite o resultado em 'diferencaEmDias'.
        // Só então avalie dependeDeRegra1_1 expressão.

        f.setVariable("diferencaEmDias", new BigDecimal(16));

        assertEquals(160, f.eval().floatValue(), 0.0001d);
    }
}
