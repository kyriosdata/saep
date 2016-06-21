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

    public Map<String, Valor> avalia(Radoc relatos) {
        return contexto;
    }

    public void defineRegras(Regras regras) {

    }

    @Override
    public Valor avaliaRegra(Regra regra, Map<String, Valor> contexto, List<Relato> relatos) {
        switch (regra.getTipo()) {
            case Regras.PONTOS_POR_RELATO:
                float ppr = regra.getPontosPorRelato();
                float pontos = ppr * relatos.size();

                return new Valor(pontos);
        }

        return new Valor(-999f);
    }

    /**
     * Avalia o conjunto de relatos conforme a sentença
     * cujo código é fornecido.
     *
     * @param relatos Conjunto de relatos a ser avaliado.
     * @param codigo  Código único da sentença a ser utilizada
     *                na avaliação dos relatos.
     * @return Pontuação obtida pela avaliação.
     */
    public float avalia(Relatos relatos, int codigo) {

        // Recupera variável associado ao resultado
        // (identificador do depósito do resultado)
        String variavel = regras.getVariavel(codigo);

        switch (regras.getTipo(codigo)) {
            case Regras.PONTOS_POR_RELATO:
                float ppr = regras.getPontosPorRelato(codigo);
                float pontos = ppr * relatos.getTotal();

                // Atualiza contexto
                contexto.put(variavel, new Valor(pontos));
                return pontos;

            case Regras.EXPRESSAO:
                float valor = avaliaExpressao(codigo);

                // Deposita resultado produzido no contexto
                contexto.put(variavel, new Valor(valor));

                return valor;

            case Regras.CONDICIONAL:
                float condicao = avaliaExpressao(codigo);
                int cexpr = condicao == 0
                        ? regras.getCodigoSentencaSenao(codigo)
                        : regras.getCodigoSentencaEntao(codigo);

                // Avalia "então" ou "senão"
                float resultado = avaliaExpressao(cexpr);

                // Deposita resultado produzido no contexto
                contexto.put(variavel, new Valor(resultado));

                return resultado;

            case Regras.SOMATORIO:
                BigDecimal somatorio = new BigDecimal(0);
                for (int i = 0; i < relatos.getTotal(); i++) {
                    somatorio = somatorio.add(avaliaExpressaoSomatorio(codigo, i, relatos));
                }

                return somatorio.floatValue();
        }

        return -999f;
    }

    /**
     * Avalia expressão associada a uma regra.
     *
     * @param regra O identificador único da regra.
     * @return O valor da expressão associada à regra.
     */
    private float avaliaExpressao(int regra) {
        Expression exp = recuperaExpressao(regra);

        defineContexto(regra, exp);

        return exp.eval().floatValue();
    }

    /**
     * Define contexto de valores de variáveis empregados pela
     * expressão que avalia a regra.
     * <p>
     * Antes da avaliação de uma expressão é necessário
     * definir os valores das variáveis empregadas pela
     * expressão. Por exemplo, se a expressão é "x + 1",
     * então antes de avaliá-la é necessário definir o
     * valor "x", ou o contexto da expressão.
     *
     * @param regra O identificador único da regra.
     * @param exp   A expressão para a qual o contexto
     *              é definido.
     */
    private void defineContexto(int regra, Expression exp) {
        // Variáveis utilizadas na avaliação da expressão
        List<String> utilizadas = regras.getDependencias(regra);

        // Recuperar o contexto
        for (String dependeDe : utilizadas) {
            Valor valor = contexto.get(dependeDe);
            float real = valor.getFloat();
            BigDecimal bd = new BigDecimal(real);
            exp.setVariable(dependeDe, bd);
        }
    }

    /**
     * Recupera a expressão que define a avaliação de
     * uma regra.
     *
     * @param regra O identificador único da regra.
     * @return {@link Expression} empregada para avaliar
     * a regra.
     */
    private Expression recuperaExpressao(int regra) {
        return new Expression(regras.getSentenca(regra));
    }

    private BigDecimal avaliaExpressaoSomatorio(int codigo, int registro, Relatos repo) {
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
     * <p>
     * Uma expressão pode fazer uso de variáveis definidas por outras
     * regras, por exemplo, "x + 1" onde o "x" identifica o valor
     * produzido por uma regra, enquanto para "10 * ch / 32", o valor
     * de "ch" decorre não de uma regra, mas de um atributo de um
     * relato.
     *
     * @param regra    O identificador da regra.
     * @param exp      A expressão para a qual o contexto é definido.
     * @param registro O identificador único do registro (relato).
     * @param repo     O repositório contendo valores de relatos.
     */
    private void defineContextoPorCampos(int regra, Expression exp, int registro, Relatos repo) {
        List<String> utilizadas = regras.getDependencias(regra);

        // Recuperar o atributo (valor) do registro (relato)
        for (String campo : utilizadas) {
            Valor valor = repo.get(registro, campo);
            if (valor != null) {
                BigDecimal bd = new BigDecimal(valor.getFloat());
                exp.setVariable(campo, bd);
            }
        }
    }
}
