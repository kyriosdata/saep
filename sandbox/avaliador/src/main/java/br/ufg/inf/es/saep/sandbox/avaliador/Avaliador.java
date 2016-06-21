package br.ufg.inf.es.saep.sandbox.avaliador;

import br.ufg.inf.es.saep.sandbox.dominio.*;
import com.udojava.evalex.Expression;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementação do serviço de avaliação de regra usando
 * Java Expression Evaluator (https://github.com/uklimaschewski/EvalEx).
 */
public class Avaliador implements AvaliaRegraService {

    /**
     * Depósito de valores produzidos pela avaliação de expressões.
     * A chave de acesso é o identificador do atributo (nome)
     * associado à expressão avaliada.
     */
    private Map<String, Valor> contexto = new HashMap<>();

    /**
     * Serviço de acesso às informações de configuração do SAEP.
     * Possivelmente deverá ser "injetado".
     */
    private Regras regras = new Regras();

    public Avaliador(Regras regras) {
        this.regras = regras;
    }

    @Override
    public Valor avaliaRegra(Regra regra, Map<String, Valor> contexto, List<Relato> relatos) {
        switch (regra.getTipo()) {
            case Regras.PONTOS_POR_RELATO:
                float pontosPorRelato = regra.getPontosPorRelato();
                float total = pontosPorRelato * relatos.size();
                total = ajustaLimites(regra, total);

                return new Valor(total);

            case Regras.EXPRESSAO:
                float valor = avaliaExpressao(regra, contexto, regra.getExpressao());
                valor = ajustaLimites(regra, valor);

                return new Valor(valor);
        }

        return new Valor(-999f);
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
        Expression exp = new Expression(expressao);

        defineContexto(regra, contexto, exp);

        return exp.eval().floatValue();
    }

    private void defineContexto(Regra regra, Map<String, Valor> contexto, Expression exp) {
        // Variáveis utilizadas na avaliação da expressão
        List<String> utilizadas = regra.getDependeDe();

        // Recuperar o contexto
        for (String dependeDe : utilizadas) {
            Valor valor = contexto.get(dependeDe);
            float real = valor.getFloat();
            BigDecimal bd = new BigDecimal(real);
            exp.setVariable(dependeDe, bd);
        }
    }
}
