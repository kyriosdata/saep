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
    private Map<String,BigDecimal> contexto = new HashMap<String, BigDecimal>();

    /**
     * Serviço de acesso às informações de configuração do SAEP.
     * Possivelmente deverá ser "injetado".
     */
    private Regras regras = new Regras();

    public Avaliador(Regras regras) {
        this.regras = regras;
    }

    public Pontuacao avalia(List<Relato> relatos, ItemAvaliado item) {
        // Cardinalidade do conjunto de relatos
        int total = relatos.size();

        // Expressão a ser avaliada
        String expressao = item.getRegra().getExpressao().getExpressao();
        Expression exp = new Expression(expressao);

        // Assume expressão constante, sem necessidade de definir contexto
        double resultado = exp.eval().doubleValue();

        return new Pontuacao(item, resultado);
    }

    /**
     * Avalia o conjunto de registros conforme a sentença
     * cujo código é fornecido.
     *
     * @param registros Conjunto de registros a ser avaliado.
     *
     * @param codigo Código único da sentença a ser utilizada
     *               na avaliação dos registros.
     *
     * @return Pontuação obtida pela avaliação.
     */
    public float avalia(List<Registro> registros, int codigo) {
        // Recupera variável associado ao resultado
        String variavel = regras.getVariavel(codigo);

        float resultado = 0f;

        switch (regras.getTipo(codigo)) {
            case 0:
                float ppr = regras.getPontosPorRelato(codigo);
                resultado = ppr * registros.size();

                // Atualiza contexto
                contexto.put(variavel, new BigDecimal(resultado));
                return resultado;
            case 1:
                // Recupera a expressão
                String sentenca = regras.getSentenca(codigo);
                Expression exp = new Expression(sentenca);

                // Variáveis utilizadas na avaliação da expressão
                List<String> utilizadas = regras.getDependencias(codigo);

                // Define o contexto
                for (String dependeDe : utilizadas) {
                    BigDecimal valor = contexto.get(dependeDe);
                    exp.setVariable(dependeDe, valor);
                }

                // Avalia
                BigDecimal resultadoSentenca = exp.eval();

                // Deposita resultado produzido no contexto
                contexto.put(variavel, resultadoSentenca);
                return resultadoSentenca.floatValue();
        }

        return 0.1f;
    }
}
