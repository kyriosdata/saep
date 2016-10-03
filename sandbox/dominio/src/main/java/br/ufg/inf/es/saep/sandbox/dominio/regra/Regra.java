/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio.regra;

import br.ufg.inf.es.saep.sandbox.dominio.Avaliavel;
import br.ufg.inf.es.saep.sandbox.dominio.excecoes.CampoExigidoNaoFornecido;
import br.ufg.inf.es.saep.sandbox.dominio.Valor;

import java.util.List;
import java.util.Map;

/**
 * Uma regra define como avaliar um ou mais
 * objetos avaliáveis. Um objeto é avaliável
 * se implementa a interface {@link Avaliavel}).
 *
 * <p>A avaliação de uma regra pode dependenter de
 * valores devidamente identificados em um contexto.
 * Quando a regra é avaliada, a "variável" correspondente
 * passa a existir no contexto e, dessa forma, torna-se
 * disponível para outras regras.
 *
 * <p>Uma regra só é avaliada após a avaliação das
 * regras das quais depende. A dependência entre
 * regras é estabelecida por meio de suas variáveis.
 * Por exemplo, uma regra que possui como "variável"
 * o identificador "x" deve ser avaliada antes de
 * qualquer regra que depende do identificador "x".
 *
 * <p>Em um caso comum, uma regra é estabelecida para
 * identificar quantos pontos são obtidos por relatos
 * de um dado tipo, por exemplo, quantos pontos por
 * livro publicado com corpo editorial.
 *
 * <p>Uma regra pode ser empregada para obter a média
 * de pontos obtidos com o ensino em determinado período.
 * Nesse caso, não se trata de uma simples contagem ou
 * consulta a propriedades de relatos. A regra em questão
 * é aplicada sobre um conjunto de entrada no qual cada
 * elemento possui um atributo devidamente identificado,
 * sobre o qual a média será calculada.
 *
 * <p>O resultado da avaliação de uma regra é depositada
 * em uma variável. Em consequência, para um determinado
 * conjunto de regras, o identificador da variável que
 * irá reter o valor final de uma regra deve ser única
 * nesse conjunto.
 */
public abstract class Regra {

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
     * Identificador de tipoRelato de regra cuja pontuação é
     * obtida da média da avaliação da expressão da regra
     * para cada um dos elementos do conjunto de
     * {@link Avaliavel}.
     */
    public static final int DATAS_COMPARACAO = 5;

    /**
     * Identificador de tipoRelato de regra cuja pontuação é
     * obtida da média da avaliação da expressão da regra
     * para cada um dos elementos do conjunto de
     * {@link Avaliavel}.
     */
    public static final int DATAS_DIFERENCA = 6;

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
     * <p>Trata-se de chave natural para um dado
     * conjunto de regras.
     */
    private String variavel;

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
     * @throws CampoExigidoNaoFornecido Caso um campo obrigatório para a
     *                                  definição de uma regra não seja fornecido.
     */
    public Regra(String variavel,
                 String descricao,
                 float valorMaximo,
                 float valorMinimo) {

        if (variavel == null || variavel.isEmpty()) {
            throw new CampoExigidoNaoFornecido("variavel");
        }

        if (descricao == null || descricao.isEmpty()) {
            throw new CampoExigidoNaoFornecido("descricao");
        }

        this.descricao = descricao;
        this.valorMaximo = valorMaximo;
        this.valorMinimo = valorMinimo;
        this.variavel = variavel;
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
     * @return Valor máximo parecerById pontuação admitido pela regra.
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
     * Recupera o identificador da variável
     * que irá reter o resultado da avaliação da regra.
     *
     * <p>Esse identificador permite que regras
     * possam ser definidas com base nos resultados de
     * outras regras, e não apenas de atributos de
     * itens que podem ser avaliados.
     *
     * @return O identificador que dá nome ao resultado da
     *      avaliação da regra.
     */
    public String getVariavel() {
        return variavel;
    }

    /**
     * Lista de dependeDe diretamente empregados
     * pela expressão cuja avaliação dá origem à
     * pontuação da regra.
     *
     * @return Lista de dependeDe diretamente empregados
     *      para avaliação da regra.
     */
    public List<String> getDependeDe() {
        return dependeDe;
    }

    /**
     * Define a lista de variáveis das quais a presente
     * regra depende.
     *
     * @param dependencias Lista de identificadores de
     *                     variáveis dos quais a presente
     *                     regra depende.
     */
    public void setDependeDe(List<String> dependencias) {
        dependeDe = dependencias;
    }

    @Override
    public boolean equals(Object outro) {
        if (this == outro) {
            return true;
        }

        if (outro == null || getClass() != outro.getClass()) {
            return false;
        }

        Regra regra = (Regra) outro;

        return variavel.equals(regra.variavel);
    }

    @Override
    public int hashCode() {
        return variavel.hashCode();
    }

    /**
     * A avaliação de uma regra produz um valor, que pode
     * estar fora dos limites estabelecidos pela regra.
     * Esse método ajusta um valor conforme os limites
     * estabelecidos pela regra.
     *
     * <p>Um valor abaixo do limite inferior é corrigido
     * para o limite inferior e, um valor acima do limte
     * superior, é corrigido para o limite superior.
     *
     * @param valor Valor a ser ajustado.
     *
     * @return O valor fornecido, caso esteja dentro dos limites
     * estabelecidos pela regra ou o valor resultante da
     * aplicação dos limites da regra.
     */
    public float ajustaLimites(float valor) {
        if (valor < getValorMinimo()) {
            return getValorMinimo();
        }

        if (valor > getValorMaximo()) {
            return getValorMaximo();
        }

        return valor;
    }

    /**
     * Oportunidade no ciclo de vida da regra para
     * alguma operação prévia antes que possa ser
     * executada.
     *
     * <p>Convém observar que nem toda regra depende
     * do emprego de um analisador sintático.
     */
    public void preparacao(Parser parser) {
    }

    /**
     * Avalia a regra, produzindo um valor a ser associado
     * à variável definida para a regra.
     *
     * <p>A avaliação de uma regra depende de valores de itens
     * avaliáveis e/ou do valor de variáveis. Em tempo, as
     * variáveis são definidas pela avaliação de regras.
     *
     * <p>Uma regra deve ser chamada apenas após a avaliação
     * de regras que produzem valores para as variáveis das
     * quais depende. Caso contrário, valores padrão serão
     * empregados.
     *
     * @param avaliaveis Conjunto de itens a serem avaliados.
     *
     * @param contexto Valores de variáveis que podem ser
     *                 necessárias para avaliação da regra.
     *
     * @return Resultado da avaliação da regra.
     */
    public abstract Valor avalie(List<Avaliavel> avaliaveis, Map<String, Valor> contexto);
}
