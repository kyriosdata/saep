/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio.regra;

import br.ufg.inf.es.saep.sandbox.dominio.Avaliavel;
import br.ufg.inf.es.saep.sandbox.dominio.Valor;
import br.ufg.inf.es.saep.sandbox.dominio.excecoes.CampoExigidoNaoFornecido;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Regra que estabelece um valor obtido do somatório
 * da avaliação de uma dada expressão para todos os
 * relatos fornecidos de uma determinada classe.
 *
 * @see RegraCondicional
 * @see RegraExpressao
 * @see RegraPontosPorRelato
 */
public class RegraSomatorio extends RegraExpressao {

    /**
     * Identificador da classe dos relatos que deverão
     * ser considerados. Do conjunto de relatos, apenas
     * aqueles da classe indicada serão considerados.
     */
    private String classe;

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
     * @param expressao     A expressão empregada para avaliar a regra,
     *                      conforme o tipo.
     * @param classe
     * @throws CampoExigidoNaoFornecido Caso um campo obrigatório para a
     *                                  definição de uma regra não seja fornecido.
     */
    public RegraSomatorio(String variavel, String descricao, float valorMaximo, float valorMinimo, String expressao, String classe) {
        super(variavel, descricao, valorMaximo, valorMinimo, expressao);
    }

    @Override
    public Valor avalie(List<Avaliavel> avaliaveis, Map<String, Valor> contexto) {
        float somatorio = 0f;

        for (Avaliavel avaliavel : avaliaveis) {
            for(String dd : ctx.keySet()) {
                ctx.put(dd, prioridade(dd, avaliavel, contexto));
            }

            somatorio = somatorio + ast.valor(ctx);
        }

        return new Valor(somatorio);
    }

    /**
     * Define o valor para uma dada variável dado um objeto avaliável e o contexto.
     *
     * <p>Caso o avaliável possua a variável, então o valor do avaliável prevalece.
     * Caso contrário o valor é procurado no contexto. Se encontrado, então esse é
     * o valor retornado. Se o valor não é encontrado nem no avaliável e nem no
     * contexto, então o valor zero é retornado.
     *
     * @param variavel Identificador da variável cujo valor é desejado.
     *
     * @param avaliavel Objeto avaliável que pode ou não conter o valor para a variável.
     *
     * @param ctx   Contexto que definirá o valor da variável caso contenha a variável
     *              em questão e essa não esteja presente no objeto avaliável.
     *
     * @return O valor da variável conforme definido no avaliável, no contexto ou o valor
     * zero, o que for encontrado primeiro, nessa ordem.
     */
    private float prioridade(String variavel, Avaliavel avaliavel, Map<String, Valor> ctx) {
        Valor v = avaliavel.get(variavel);
        if (v != null) {
            return v.getReal();
        }

        if (ctx.containsKey(variavel)) {
            return ctx.get(variavel).getReal();
        }

        return 0f;
    }
}
