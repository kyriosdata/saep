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

    public Map<String, BigDecimal> avalia(Registros registros) {
        return null;
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
    public float avalia(Registros registros, int codigo) {

        // Recupera variável associado ao resultado
        // (identificador do depósito do resultado)
        String variavel = regras.getVariavel(codigo);

        switch (regras.getTipo(codigo)) {
            case Regras.PONTOS_POR_RELATO:
                float ppr = regras.getPontosPorRelato(codigo);
                float pontos = ppr * registros.getTotal();

                // Atualiza contexto
                contexto.put(variavel, new BigDecimal(pontos));
                return pontos;

            case Regras.EXPRESSAO:
                BigDecimal valor = avaliaExpressao(codigo);

                // Deposita resultado produzido no contexto
                contexto.put(variavel, valor);

                return valor.floatValue();

            case Regras.CONDICIONAL:
                BigDecimal condicao = avaliaExpressao(codigo);
                int cexpr = condicao == BigDecimal.ZERO
                        ? regras.getCodigoSentencaSenao(codigo)
                        : regras.getCodigoSentencaEntao(codigo);

                // Avalia "então" ou "senão"
                BigDecimal resultado = avaliaExpressao(cexpr);

                // Deposita resultado produzido no contexto
                contexto.put(variavel, resultado);

                return resultado.floatValue();

            case Regras.SOMATORIO:
                BigDecimal somatorio = new BigDecimal(0);
                for(int i = 0; i < registros.getTotal(); i++) {
                    somatorio = somatorio.add(avaliaExpressaoSomatorio(codigo, i, registros));
                }

                return somatorio.floatValue();
        }

        return -999f;
    }

    /**
     * Avalia expressão associada a uma regra.
     *
     * @param regra O identificador único da regra.
     *
     * @return O valor da expressão associada à regra.
     */
    private BigDecimal avaliaExpressao(int regra) {
        Expression exp = recuperaExpressao(regra);

        defineContexto(regra, exp);

        return exp.eval();
    }

    /**
     * Define contexto de valores de variáveis empregados pela
     * expressão que avalia a regra.
     *
     * Antes da avaliação de uma expressão é necessário
     * definir os valores das variáveis empregadas pela
     * expressão. Por exemplo, se a expressão é "x + 1",
     * então antes de avaliá-la é necessário definir o
     * valor "x", ou o contexto da expressão.
     *
     * @param regra O identificador único da regra.
     *
     * @param exp A expressão para a qual o contexto
     *            é definido.
     */
    private void defineContexto(int regra, Expression exp) {
        // Variáveis utilizadas na avaliação da expressão
        List<String> utilizadas = regras.getDependencias(regra);

        // Recuperar o contexto
        for (String dependeDe : utilizadas) {
            BigDecimal valor = contexto.get(dependeDe);
            exp.setVariable(dependeDe, valor);
        }
    }

    /**
     * Recupera a expressão que define a avaliação de
     * uma regra.
     *
     * @param regra O identificador único da regra.
     *
     * @return {@link Expression} empregada para avaliar
     * a regra.
     */
    private Expression recuperaExpressao(int regra) {
        return new Expression(regras.getSentenca(regra));
    }

    private BigDecimal avaliaExpressaoSomatorio(int codigo, int registro, Registros repo) {
        Expression exp = recuperaExpressao(codigo);

        // Variáveis utilizadas na avaliação da expressão
        // e que não dependem do registro fornecido.
        defineContexto(codigo, exp);

        defineContextoPorCampos(codigo, exp, registro, repo);

        return exp.eval();
    }

    /**
     * Define valores de atributos do registro (relato) no contexto
     * da expressão empregada na avaliação da regra.
     *
     * Uma expressão pode fazer uso de variáveis definidas por outras
     * regras, por exemplo, "x + 1" onde o "x" identifica o valor
     * produzido por uma regra, enquanto para "10 * ch / 32", o valor
     * de "ch" decorre não de uma regra, mas de um atributo de um
     * relato.
     *
     * @param regra O identificador da regra.
     * @param exp A expressão para a qual o contexto é definido.
     * @param registro O identificador único do registro (relato).
     * @param repo O repositório contendo valores de relatos.
     */
    private void defineContextoPorCampos(int regra, Expression exp, int registro, Registros repo) {
        List<String> utilizadas = regras.getDependencias(regra);

        // Recuperar o atributo (valor) do registro (relato)
        for (String campo : utilizadas) {
            BigDecimal valor = repo.get(registro, campo);
            exp.setVariable(campo, valor);
        }
    }
}
