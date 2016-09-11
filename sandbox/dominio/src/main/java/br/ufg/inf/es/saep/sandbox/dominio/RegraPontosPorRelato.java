/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.List;
import java.util.Map;

/**
 * Regra que identifica total de pontos por dada
 * classe de relato.
 */
public class RegraPontosPorRelato extends Regra {

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
    public RegraPontosPorRelato(String variavel, int tipo, String descricao, float valorMaximo, float valorMinimo, String tipoRelato, float pontosPorItem, List<String> dependeDe) {
        super(variavel, tipo, descricao, valorMaximo, valorMinimo, dependeDe);
        if (tipoRelato == null || tipoRelato.isEmpty()) {
            throw new CampoExigidoNaoFornecido("tipoRelato");
        }

        this.tipoRelato = tipoRelato;
        this.pontosPorItem = pontosPorItem;
    }

    /**
     * Recupera o tipo do relato associado à regra.
     *
     * @return O identificador do tipo de relato.
     */
    public String getTipoRelato() {
        return tipoRelato;
    }

    /**
     * Recupera a quantidade de pontos atribuída a cada
     * item para obtenção do valor da regra.
     *
     * @return Pontos por item avaliável.
     */
    public float getPontosPorItem() {
        return pontosPorItem;
    }

    @Override
    public Valor avalie(List<Avaliavel> avaliaveis, Map<String, Valor> contexto) {
        int total = 0;
        for (Avaliavel avaliavel : avaliaveis) {
            if (tipoRelato.equals(avaliavel.get("classe"))) {
                total = total + 1;
            }
        }

        float pontos = getPontosPorItem() * total;

        return new Valor(ajustaLimites(pontos));
    }
}
