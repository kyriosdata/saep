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
 * <p>Uma regra produz como resultado, quando avaliada, um único valor.
 * Esse valor pode ser recuperado pelo identificador da variável da
 * regra. O valor pode ser associado à própria regra ou ao relato
 * avaliado.
 *
 * <p>Se for associado à regra, então teremos um único valor
 * produzido pela avaliação da regra. Esse valor será acessível
 * pelo identificador da regra.
 *
 * <p>Se for associado ao relato avaliado, então um valor é produzido
 * para cada relato avaliado pela regra. Nesse caso, para recuperar
 * tal valor é necessário o relato e o identificador da variável
 * da regra. Observe que nesse caso a regra pode ser vista como
 * um meio para acrescentar atributos a um relato.
 */
package br.ufg.inf.es.saep.sandbox.dominio.regra;
