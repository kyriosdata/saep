/**
 * Definição de regras por meio das quais uma
 * contagem sobre avaliáveis pode ser realizada.
 *
 * <p>Um conjunto de regras é definido por meio de uma configuração
 * ({@link br.ufg.inf.es.saep.sandbox.dominio.regra.Configuracao}),
 * que juntamente com o conjunto de avaliáveis, definem os insumos
 * para o processo de contagem depositar os resultados das avaliações
 * das regras nas suas respectivas variáveis.
 *
 * <p>A avaliação de uma regra pode fazer uso de variáveis (valores)
 * já produzidos pela avaliação de outras regras, e/ou de valores
 * do conjunto de atributos de avaliáveis. Por exemplo, uma regra
 * definida como uma expressão "10 * a", onde "a" é uma variável
 * definida como resultado de uma outra regra, não faz uso de um
 * avaliável. Ou seja, um conjunto de regras pode ser avaliado sem
 * que valores sejam requisitados de avaliáveis.
 *
 * <p>Em outros cenários, a avaliação de uma regra depende do acesso
 * aos valores de atributos de avaliáveis. Ou seja, um avaliável serve
 * como insumo para a avaliação de uma regra.
 *
 * <p>Uma
 */
package br.ufg.inf.es.saep.sandbox.dominio.regra;
