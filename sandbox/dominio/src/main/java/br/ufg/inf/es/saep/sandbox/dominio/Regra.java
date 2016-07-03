/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.List;

/**
 * Uma regra define como avaliar um conjunto
 * de objetos avaliáveis. Um objeto é avaliável
 * se implementam a interface {@link Avaliavel}).
 *
 * <p>Em um caso comum, uma regra é estabelecida para
 * identificar quantos pontos são obtidos por relatos
 * de um dado tipoRegra, por exemplo, quantos pontos por
 * livro publicado com corpo editorial.
 *
 * <p>Uma regra pode ser empregada para obter a média
 * de pontos obtidos com o ensino em determinado período.
 * Nesse caso, não se trata de uma simples contagem ou
 * consulta a propriedades de relatos. A regra em questão
 * é aplicada sobre um conjunto de entrada no qual cada
 * elemento possui um atributo devidamente identificado,
 * sobre o qual a média será calculada.
 */
public class Regra {

    /**
     * Identificador de tipoRelato de regra cuja pontuação é
     * obtida da quantidade de relatos multiplicada pelos
     * pontos para cada relato.
     */
    public static final int PONTOS = 0;

    /**
     * Identificador de tipoRelato de regra cuja pontuação é
     * obtida da avaliação da expressão da regra.
     */
    public static final int EXPRESSAO = 1;

    /**
     * Identificador de tipoRelato de regra cuja pontuação
     * resultante é a avaliação da expressão "então"
     * ou a avaliação da expressão "senão", conforme
     * a avaliação da condição seja, respectivamente,
     * verdadeira ou falsa.
     */
    public static final int CONDICIONAL = 2;

    /**
     * Identificador de tipoRelato de regra cuja pontuação
     * é obtida do somatório da avaliação da expressão
     * da regra para um dado conjunto de {@link Avaliavel}
     * de entrada.
     */
    public static final int SOMATORIO = 3;

    /**
     * Identificador de tipoRelato de regra cuja pontuação é
     * obtida da média da avaliação da expressão da regra
     * para cada um dos elementos do conjunto de
     * {@link Avaliavel}.
     */
    public static final int MEDIA = 4;

    /**
     * O valor {@link #EXPRESSAO}, ou {@link #CONDICIONAL},
     * ou {@link #SOMATORIO}, ou {@link #MEDIA} ou {@link #PONTOS},
     * que caracteriza o tipo de regra em questão.
     */
    private int tipo;

    /**
     * Descrição da regra.
     */
    private String descricao;

    /**
     * Valor máximo admitido para a avaliação da regra.
     * Se a avaliação de uma regra produzir um valor
     * acima do valor indicado por essa propriedade,
     * então o valor produzido é substituído pelo
     * valor da propriedade.
     */
    private float valorMaximo;

    /**
     * Valor mínimo aditimido para o resultado
     * da avaliação da regra. Se um valor inferior
     * é obtido, então o valor resultante é
     * substituído pelo valor dessa propriedade.
     */
    private float valorMinimo;

    /**
     * Nome da variável (atributo) que guardará
     * o resultado da avaliação da regra.
     *
     * <p>Trata-se de chave natural para uma regra
     * em uma dada resolução.
     */
    private String variavel;

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
     * Lista de identificadores de atributos que são
     * empregados pela expressão que avalia a regra.
     * Caso a regra seja condicional, então acumula
     * os identificados das expressões "então" e
     * "senão". Se a regra é do tipoRelato pontos por item
     * avaliável, então a lista é vazia.
     */
    private List<String> dependeDe;

    /**
     * Recupera o tipo da regra.
     *
     * @return O tipo da regra.
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * Recupera a descrição da regra.
     *
     * @return A descrição da regra.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Recupera o valor máximo admitido para
     * o resultado da regra.
     *
     * @return Valor máximo byId pontuação admitido pela regra.
     */
    public float getValorMaximo() {
        return valorMaximo;
    }

    /**
     * Recupera o valor mínimo admitido pela regra.
     *
     * @return O valor mínimo admitido pela regra.
     */
    public float getValorMinimo() {
        return valorMinimo;
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

    /**
     * Recupera o tipo do relato associado à regra.
     * O retorno desse método é útil apenas quando o
     * tipo da regras é {@link #PONTOS}.
     *
     * @return O identificador do tipo de relato.
     */
    public String getTipoRelato() {
        return tipoRelato;
    }

    /**
     * Recupera o identificador da variável
     * que irá reter o resultado da avaliação da regra.
     *
     * <p>Esse identificador permite que regras
     * possam ser definidas com base nos resultados de
     * outras regras, e não apenas de atributos de
     * itens que podem ser avaliados.
     *
     * @return O identificador que dá nome ao resultado da
     * avaliação da regra.
     *
     */
    public String getVariavel() {
        return variavel;
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

    /**
     * Lista de dependeDe diretamente empregados
     * pela expressão cuja avaliação dá origem à
     * pontuação da regra.
     *
     * @return Lista de dependeDe diretamente empregados
     * para avaliação da regra.
     */
    public List<String> getDependeDe() {
        return dependeDe;
    }

    /**
     * Cria uma regra.
     *
     * @throws CampoExigidoNaoFornecido Caso um campo obrigatório para a
     * definição de uma regra não seja fornecido.
     *
     * @param tipo O tipo da regra. Um dos seguintes valores: {@link #PONTOS},
     *             {@link #EXPRESSAO}, {@link #CONDICIONAL}, {@link #MEDIA} ou
     *             {@link #SOMATORIO}.
     *
     * @param descricao Texto que fornece alguma explanação sobre a regra.
     *
     * @param valorMaximo O valor máximo a ser utilizado como resultado da
     *                    avaliação da regra. Esse valor é empregado apenas
     *                    se a avaliação resultar em valor superior ao
     *                    expresso por esse parâmetro.
     *
     * @param valorMinimo O valor mínimo a ser utilizado como resultado da
     *                    avaliação da regra. Esse valor é empregado apenas
     *                    se a avaliação resultar em valor inferior ao
     *                    expresso por esse parâmetro.
     *
     * @param variavel O identificador (nome) da variável que retém o
     *                 valor da avaliação da regra.
     *
     * @param expressao A expressão empregada para avaliar a regra,
     *                  conforme o tipo.
     *
     * @param entao A expressão que dará origem ao valor da regra caso
     *              a condição correspondente seja avaliada como verdadeira.
     *
     * @param senao A expressão que dará origem ao valor da regra caso a
     *              condição correspondente seja avaliada como falsa.
     *
     * @param tipoRelato Nome que identifica um relato, empregado em regras
     *                   cuja avaliação é pontos por relato.
     *
     * @param pontosPorItem Total de pontos para cada relato de um dado
     *                      tipo.
     *
     * @param dependeDe Lista de identificadores (atributos) que são
     *                  empregados na avaliação da regra. Por exemplo,
     *                  se uma regra é definida pela expressão "a + b",
     *                  então essa regra dependente de "a" e de "b".
     *
     */
    public Regra(int tipo,
                 String descricao,
                 float valorMaximo,
                 float valorMinimo,
                 String variavel,
                 String expressao,
                 String entao,
                 String senao,
                 String tipoRelato,
                 int pontosPorItem,
                 List<String> dependeDe) {

        if (tipo < 0 || tipo > 4) {
            throw new TipoDeRegraInvalido("tipo");
        }

        if (descricao == null || descricao.isEmpty()) {
            throw new CampoExigidoNaoFornecido("descricao");
        }

        if (variavel == null || variavel.isEmpty()) {
            throw new CampoExigidoNaoFornecido("variavel");
        }

        this.tipo = tipo;
        this.descricao = descricao;
        this.valorMaximo = valorMaximo;
        this.valorMinimo = valorMinimo;
        this.variavel = variavel;

        // A avaliação abaixo seria mais simples se herança fosse
        // empregada. Contudo, fica como alternativa caso novos
        // tipos de regras sejam definidos.

        if (tipo == PONTOS) {
            if (tipoRelato == null || tipoRelato.isEmpty()) {
                throw new CampoExigidoNaoFornecido("tipoRelato");
            }

            this.tipoRelato = tipoRelato;
            this.pontosPorItem = pontosPorItem;
        } else {
            if (expressao == null || expressao.isEmpty()) {
                throw new CampoExigidoNaoFornecido("expressao");
            }

            if (dependeDe == null) {
                throw new CampoExigidoNaoFornecido("dependeDe");
            }

            this.expressao = expressao;
            this.dependeDe = dependeDe;
        }

        if (tipo == CONDICIONAL) {
            if (entao == null || entao.isEmpty()) {
                throw new CampoExigidoNaoFornecido("entao");
            }

            this.entao = entao;
            this.senao = senao;
        }
    }
}
