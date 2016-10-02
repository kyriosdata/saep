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
 * Regra definida por uma expressão formada por constantes,
 * variáveis (definidas em um contexto) e atributos de
 * itens avaliáveis.
 *
 * <p>Uma expressão pode ser numérica, por exemplo,
 * "ch * 12.5", ou produzir um valor lógico, "3 > 1".
 */
public class RegraExpressao extends Regra {

    /**
     * Expressão a ser avaliada para obtenção do
     * resultado da avaliação da regra.
     */
    private String expressao;

    /**
     * Árvore sintática da expressão executável.
     */
    private Expressao ast;

    /**
     * Variáveis empregadas pela regra com
     * respectivos valores a serem utilizados.
     * Valores devem ser atualizados antes da
     * execução da regra.
     */
    private Map<String, Float> ctx;

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
     * @param dependeDe     Lista de identificadores (atributos) que são
     *                      empregados na avaliação da regra. Por exemplo,
     *                      se uma regra é definida pela expressão "a + b",
     * @throws CampoExigidoNaoFornecido Caso um campo obrigatório para a
     *                                  definição de uma regra não seja fornecido.
     */
    public RegraExpressao(String variavel, int tipo, String descricao, float valorMaximo, float valorMinimo, String expressao, List<String> dependeDe) {
        super(variavel, tipo, descricao, valorMaximo, valorMinimo, dependeDe);
        if (expressao == null || expressao.isEmpty()) {
            throw new CampoExigidoNaoFornecido("expressao");
        }

        this.expressao = expressao;
    }

    @Override
    public void preparacao(Parser parser) {
        if (parser == null) {
            throw new CampoExigidoNaoFornecido("parser");
        }

        List<String> dd = parser.dependencias(expressao);
        ctx = new HashMap<>(dd.size());
        for (String dep : dd) {
           ctx.put(dep, 0f);
        }

        ast = parser.ast(expressao);
    }

    /**
     * Recupera a expressão associada à regra.
     *
     * @return A expressão empregada pela regra.
     */
    public String getExpressao() {
        return expressao;
    }

    @Override
    public Valor avalie(List<Avaliavel> avaliaveis, Map<String, Valor> contexto) {
        for(String dd : getDependeDe()) {
            float valor = 0f;
            if (contexto.containsKey(dd)) {
                valor = contexto.get(dd).getReal();
            }

            ctx.put(dd, valor);
        }

        return new Valor(ast.valor(ctx));
    }
}
