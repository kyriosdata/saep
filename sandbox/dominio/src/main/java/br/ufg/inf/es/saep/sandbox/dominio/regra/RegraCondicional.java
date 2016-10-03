/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio.regra;

import br.ufg.inf.es.saep.sandbox.dominio.Avaliavel;
import br.ufg.inf.es.saep.sandbox.dominio.Valor;
import br.ufg.inf.es.saep.sandbox.dominio.excecoes.CampoExigidoNaoFornecido;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Regra que implementa condição ("se" ou "if").
 */
public class RegraCondicional extends Regra {

    /**
     * Sentença que reflete a condição da regra.
     */
    private String condicao;

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
    private Expressao exprCondicao;
    private Expressao exprEntao;
    private Expressao exprSenao;

    /**
     * Contexto do qual variáveis (valores) serão consultados)
     */
    private Map<String, Float> ctx;

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
     * @param entao         A expressão que dará origem ao valor da regra caso
     *                      a condição correspondente seja avaliada como verdadeira.
     * @param senao         A expressão que dará origem ao valor da regra caso a
     *                      condição correspondente seja avaliada como falsa.
     * @throws CampoExigidoNaoFornecido Caso um campo obrigatório para a
     *                                  definição de uma regra não seja fornecido.
     */
    public RegraCondicional(String variavel, String descricao, float valorMaximo, float valorMinimo, String condicao, String entao, String senao) {
        super(variavel, descricao, valorMaximo, valorMinimo);

        if (entao == null || entao.isEmpty()) {
            throw new CampoExigidoNaoFornecido("entao");
        }

        this.condicao = condicao;
        this.entao = entao;
        this.senao = senao;
    }

    /**
     * Recupera a expressão "então" associada à regra
     * do tipo {@link #CONDICIONAL}.
     *
     * @return A expressão "então".
     */
    public String getEntao() {
        return entao;
    }

    /**
     * Recupera a expressão "senão" associada à regra
     * do tipo {@link #CONDICIONAL}.
     *
     * @return A expressão "senão".
     */
    public String getSenao() {
        return senao;
    }

    @Override
    public void preparacao(Parser parser) {
        if (parser == null) {
            throw new CampoExigidoNaoFornecido("parser");
        }

        List<String> dc = parser.dependencias(condicao);
        List<String> de = parser.dependencias(entao);
        List<String> ds = parser.dependencias(senao);

        List<String> dd = new ArrayList<>(dc);
        dd.addAll(de);
        dd.addAll(ds);

        ctx = new HashMap<>(dd.size());
        for (String dep : dd) {
            ctx.put(dep, 0f);
        }

        exprCondicao = parser.ast(condicao);
        exprEntao = parser.ast(entao);
        exprSenao = parser.ast(senao);
    }

    @Override
    public Valor avalie(List<Avaliavel> avaliaveis, Map<String, Valor> contexto) {
        // Define o valor zero (padrão) ou o fornecido no contexto.
        for(String dd : ctx.keySet()) {
            float valor = 0f;
            if (contexto.containsKey(dd)) {
                valor = contexto.get(dd).getReal();
            }

            ctx.put(dd, valor);
        }

        float resultado = exprCondicao.valor(ctx) > 0.0001f
                ? exprEntao.valor(ctx)
                : exprSenao.valor(ctx);

        return new Valor(resultado);
    }
}
