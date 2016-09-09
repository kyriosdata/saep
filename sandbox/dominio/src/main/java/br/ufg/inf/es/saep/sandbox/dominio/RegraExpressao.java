/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.List;

/**
 * Regra definida por uma expressão.
 */
public class RegraExpressao extends Regra {

    /**
     * Expressão a ser avaliada para obtenção do
     * resultado da avaliação da regra. Caso a
     * regra seja condicional, então essa expressão
     * é lógica. Caso a regra seja uma contagem por
     * pontos, então o valor é irrelevante.
     */
    private String expressao;
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
     * Identificador único de um tipoRelato de relato.
     * Nem toda regra, convém destacar, refere-se
     * a um relato. Se esse for o caso, esse valor
     * é irrelevante.
     */
    private String tipoRelato;
    /**
     * Quantidade de pontos definidos por item
     * {@link Avaliavel}.
     */
    private float pontosPorItem;

    /**
     * Cria uma regra.
     *
     * @param variavel      O identificador (nome) da variável que retém o
     *                      valor da avaliação da regra. Em um dado conjunto de
     *                      regras, existe uma variável distinta para cada uma
     *                      delas.
     * @param tipo          O tipo da regra. Um dos seguintes valores: {@link #PONTOS},
     *                      {@link #EXPRESSAO}, {@link #CONDICIONAL}, {@link #MEDIA} ou
     *                      {@link #SOMATORIO}.
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
     * @param entao         A expressão que dará origem ao valor da regra caso
     *                      a condição correspondente seja avaliada como verdadeira.
     * @param senao         A expressão que dará origem ao valor da regra caso a
     *                      condição correspondente seja avaliada como falsa.
     * @param tipoRelato    Nome que identifica um relato, empregado em regras
     *                      cuja avaliação é pontos por relato.
     * @param pontosPorItem Total de pontos para cada relato de um dado
     *                      tipo.
     * @param dependeDe     Lista de identificadores (atributos) que são
     *                      empregados na avaliação da regra. Por exemplo,
     *                      se uma regra é definida pela expressão "a + b",
     * @throws CampoExigidoNaoFornecido Caso um campo obrigatório para a
     *                                  definição de uma regra não seja fornecido.
     */
    public RegraExpressao(String variavel, int tipo, String descricao, float valorMaximo, float valorMinimo, String expressao, String entao, String senao, String tipoRelato, float pontosPorItem, List<String> dependeDe) {
        super(variavel, tipo, descricao, valorMaximo, valorMinimo, dependeDe);
        if (expressao == null || expressao.isEmpty()) {
            throw new CampoExigidoNaoFornecido("expressao");
        }

        this.expressao = expressao;

            if (entao == null || entao.isEmpty()) {
                throw new CampoExigidoNaoFornecido("entao");
            }

            this.entao = entao;
            this.senao = senao;
    }

    /**
     * Recupera a expressão associada à regra.
     *
     * @return A expressão empregada pela regra.
     */
    public String getExpressao() {
        return expressao;
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
    public Valor avalie(List<Avaliavel> avaliaveis) {
        //float valor = avaliaExpressao(regra, contexto, regra.getExpressao());
        //valor = ajustaLimites(regra, valor);

        return new Valor(1);
    }
}
