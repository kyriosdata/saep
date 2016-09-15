package br.ufg.inf.es.saep.sandbox.infraestrutura;

import br.ufg.inf.es.saep.sandbox.dominio.*;
import br.ufg.inf.es.saep.sandbox.dominio.avaliacao.AvaliaRegraService;
import br.ufg.inf.es.saep.sandbox.dominio.excecoes.FalhaAoAvaliarRegra;
import br.ufg.inf.es.saep.sandbox.dominio.regra.Regra;
import com.udojava.evalex.Expression;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Implementação do serviço de avaliação de regra usando dependeDeRegra1_1 ferramenta
 * <dependeDeRegra1_1 href="https://github.com/uklimaschewski/EvalEx">Java Expression
 * Evaluator</dependeDeRegra1_1>.
 *
 * <p>Uma alternativa é http://www.beyondlinux.com/2011/08/07/3-method-to-evaluate-expressions/.
 *
 */
public class AvaliaRegraServiceEvalEx implements AvaliaRegraService {

    @Override
    public Valor avalia(Regra regra, Map<String, Valor> contexto, List<Avaliavel> relatos) {
        return new Valor(1);
    }

    private float somatorio(Regra regra, List<Avaliavel> relatos) {
        float somatorio = 0;
        Expression exp = new Expression("a");

        for (Avaliavel relato : relatos) {
            for (String variavel : regra.getDependeDe()) {
                Valor valor = relato.get(variavel);
                if (valor == null) {
                    continue;
                }

                float valor1 = valor.getReal();
                exp.setVariable(variavel, new BigDecimal(valor1));
            }

            try {
                somatorio += exp.eval().floatValue();
            } catch (RuntimeException rex) {
                throw new FalhaAoAvaliarRegra("Falha: " + rex.getMessage());
            }
        }

        return somatorio;
    }

    private float avaliaExpressao(Regra regra, Map<String, Valor> contexto, String expressao) {
        try {
            Expression exp = new Expression(expressao);

            defineContexto(regra, contexto, exp);

            BigDecimal valor = exp.eval();
            return valor.floatValue();
        } catch (RuntimeException re) {
            throw new FalhaAoAvaliarRegra("Avaliação de regra: " + re.getMessage());
        }
    }

    private void defineContexto(Regra regra, Map<String, Valor> contexto, Expression exp) {
        List<String> utilizadas = regra.getDependeDe();

        // Recuperar o contexto
        for (String dependeDe : utilizadas) {
            Valor valor = contexto.get(dependeDe);

            // Variável pode não estar disponível no contexto.
            // Provavelmente acarretará erro de execução.
            if (valor == null) {
                continue;
            }

            float real = valor.getReal();
            BigDecimal bd = new BigDecimal(real);
            exp.setVariable(dependeDe, bd);
        }
    }

    private void defineContextoDatas(Regra regra, Map<String, Valor> contexto, Expression exp) {
        List<String> identificadoresDatas = regra.getDependeDe();

        Valor data1 = contexto.get(identificadoresDatas.get(0));
        Valor data2 = contexto.get(identificadoresDatas.get(1));

    }
}
