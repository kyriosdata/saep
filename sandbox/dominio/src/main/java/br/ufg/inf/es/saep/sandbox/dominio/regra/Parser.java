/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio.regra;

import java.util.List;

/**
 * Encapsula serviço de compilação de sentenças
 * matemáticas. Tornando o domínio independente da
 * implementação de serviços necessários de compilação
 * e execução de uma sentença.
 *
 * <p>Essa interface deve ser compreendida em conjunto
 * com a interface {@link Expressao}.
 *
 * @see Expressao
 */
public interface Parser {

    /**
     * Obtém expressão executável com base na
     * sentença fornecida.
     *
     * @param sentenca Uma expressão matemática como
     *                 "(x + y)", por exemplo.
     *
     * @return Versão da sentença fornecida apta
     * para ser executada.
     */
    Expressao ast(String sentenca);

    /**
     * Lista de variáveis (identificadores) de variáveis
     * das quais a sentença fornecida depende. A sentença
     * "(x + y)", por exemplo, depende de "x" e de "y".
     *
     * @param sentenca Sentença cujos componentes, variáveis
     *                 empregadas são identificadas pela
     *                 operação.
     *
     * @return Lista dos identificadores de variáveis
     * empregadas pela sentença.
     */
    List<String> dependencias(String sentenca);
}
