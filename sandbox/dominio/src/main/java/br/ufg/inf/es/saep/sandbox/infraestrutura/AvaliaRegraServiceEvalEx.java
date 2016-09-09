package br.ufg.inf.es.saep.sandbox.infraestrutura;

import br.ufg.inf.es.saep.sandbox.dominio.*;
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
        switch (regra.getTipo()) {
            case Regra.PONTOS:
                float pontosPorRelato = regra.getPontosPorItem();
                float total = pontosPorRelato * relatos.size();
                total = ajustaLimites(regra, total);

                return new Valor(total);

            case Regra.EXPRESSAO:
                float valor = avaliaExpressao(regra, contexto, regra.getExpressao());
                valor = ajustaLimites(regra, valor);

                return new Valor(valor);

            case Regra.CONDICIONAL:
                float condicao = avaliaExpressao(regra, contexto, regra.getExpressao());
                float entaoOuSenao;
                if (condicao != 0f) {
                    entaoOuSenao = avaliaExpressao(regra, contexto, regra.getEntao());
                } else {
                    entaoOuSenao = avaliaExpressao(regra, contexto, regra.getSenao());
                }

                entaoOuSenao = ajustaLimites(regra, entaoOuSenao);

                return new Valor(entaoOuSenao);

            case Regra.SOMATORIO:
                float somatorio = somatorio(regra, relatos);

                somatorio = ajustaLimites(regra, somatorio);

                return new Valor(somatorio);

            case Regra.MEDIA:
                float parcial = somatorio(regra, relatos);
                parcial /= relatos.size();

                parcial = ajustaLimites(regra, parcial);

                return new Valor(parcial);

            case Regra.DATAS_COMPARACAO:

            case Regra.DATAS_DIFERENCA:

            default:
                throw new TipoDeRegraInvalido("avalia");
        }
    }

    private float somatorio(Regra regra, List<Avaliavel> relatos) {
        float somatorio = 0;
        Expression exp = new Expression(regra.getExpressao());

        for (Avaliavel relato : relatos) {
            for (String variavel : regra.getDependeDe()) {
                Valor valor = relato.get(variavel);
                if (valor == null) {
                    continue;
                }

                float valor1 = valor.getFloat();
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

    private float ajustaLimites(Regra regra, float valor) {
        if (valor < regra.getValorMinimo()) {
            return regra.getValorMinimo();
        }

        if (valor > regra.getValorMaximo()) {
            return regra.getValorMaximo();
        }

        return valor;
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

            float real = valor.getFloat();
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
