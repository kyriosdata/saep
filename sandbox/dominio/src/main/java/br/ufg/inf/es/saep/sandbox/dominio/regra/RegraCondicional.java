/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio.regra;

import br.ufg.inf.es.saep.sandbox.dominio.Avaliavel;
import br.ufg.inf.es.saep.sandbox.dominio.Valor;
import br.ufg.inf.es.saep.sandbox.dominio.excecoes.CampoExigidoNaoFornecido;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Regra que implementa condição ("se" ou "if").
 */
public class RegraCondicional extends RegraExpressao {

    /**
     * Maior valor considerado zero.
     */
    private static final float MAIORZERO = 0.0001f;

    /**
     * Expressão a ser avaliada e cujo resultado torna-se
     * o resultado da regra condicional caso a condição
     * seja verdadeira.
     */
    private String entao;

    /**
     * Expressão a ser avaliada e cujo resultado torna-se
     * o resultado da regra condicional caso a condição
     * seja falsa.
     */
    private String senao;

    /**
     * Expressões (árvores sintáticas) das sentenças
     * da regra.
     */
    private Expressao exprEntao;

    /**
     * Expressão a ser executada caso a condição
     * seja falsa.
     */
    private Expressao exprSenao;

    /**
     * Cria uma regra.
     *
     * @param variavel      O identificador (nome) da variável que retém o
     *                      valor da avaliação da regra. Em um dado conjunto de
     *                      regras, existe uma variável distinta para cada uma
     *                      delas.
     * @param descricao     Texto que fornece alguma explanação sobre a regra.
     * @param valorMaximo   O valor máximo a ser utilizado como resultado da
     *                      avaliação da regra. Esse valor é empregado apenas
     *                      se a avaliação resultar em valor superior ao
     *                      expresso por esse parâmetro.
     * @param valorMinimo   O valor mínimo a ser utilizado como resultado da
     *                      avaliação da regra. Esse valor é empregado apenas
     *                      se a avaliação resultar em valor inferior ao
     *                      expresso por esse parâmetro.
     * @param condicao     A expressão empregada para avaliar a regra,
     *                      conforme o tipo.
     * @param sentencaEntao A expressão que dará origem ao valor da regra caso
     *                      a condição correspondente seja avaliada como
     *                      verdadeira.
     * @param sentencaSenao A expressão que dará origem ao valor da regra caso
     *                      a condição correspondente seja avaliada como falsa.
     * @throws CampoExigidoNaoFornecido Caso um campo obrigatório para a
     *                                  definição de uma regra não seja
     *                                  fornecido.
     */
    public RegraCondicional(final String variavel, final String descricao,
                            final float valorMaximo, final float valorMinimo,
                            final String condicao, final String sentencaEntao,
                            final String sentencaSenao) {
        super(variavel, descricao, valorMaximo, valorMinimo, condicao);

        if (sentencaEntao == null || sentencaEntao.isEmpty()) {
            throw new CampoExigidoNaoFornecido("entao");
        }

        this.entao = sentencaEntao;
        this.senao = sentencaSenao;
    }

    /**
     * Recupera a expressão "então" associada à regra.
     *
     * @return A expressão "então".
     */
    public final String getEntao() {
        return entao;
    }

    /**
     * Recupera a expressão "senão" associada à regra.
     *
     * @return A expressão "senão".
     */
    public final String getSenao() {
        return senao;
    }

    @Override
    public final void preparacao(final Parser parser) {
        super.preparacao(parser);

        List<String> de = parser.dependencias(entao);
        List<String> ds = parser.dependencias(senao);

        List<String> dd = new ArrayList<>(2);
        dd.addAll(de);
        dd.addAll(ds);

        for (String dep : dd) {
            ctx.put(dep, 0f);
        }

        exprEntao = parser.ast(entao);
        exprSenao = parser.ast(senao);
    }

    @Override
    public final Valor avalie(final List<Avaliavel> avaliaveis,
                        final Map<String, Valor> contexto) {
        atualizaContexto(contexto);

        float resultado;
        if (ast.valor(ctx) > MAIORZERO) {
            resultado = exprEntao.valor(ctx);
        } else {
            resultado = exprSenao.valor(ctx);
        }

        return new Valor(resultado);
    }
}
