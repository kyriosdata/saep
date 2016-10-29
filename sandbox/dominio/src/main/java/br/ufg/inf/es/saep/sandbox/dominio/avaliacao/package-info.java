/**
 * Classes que implementam a avaliação de regras com base
 * em relatos.
 *
 * <p>A avaliação recebe como entrada um conjunto de
 * regras e um conjunto de avaliáveis. Todas as regras
 * são avaliadas para o conjunto de avaliáveis fornecido.
 *
 * <p>Primeiro as regras são "ordenadas" conforme a ordem
 * de execução delas. A ordem deve ser tal que uma regra
 * seja executada apenas após a avaliação das
 * regras das quais depende. A dependência é definida pelas
 * variáveis utilizadas pela regra. Por exemplo, uma regra
 * cujo resultado é associado à variável "x" será avaliada
 * antes de uma regra cuja avaliação emprega a expressão
 * "10 * x", por exemplo.
 *
 * <p>Conforme a sequência de execução, uma a uma as regras
 * são executadas. Observando que o resultado da execução
 * de uma regra torna-se disponível para a avaliação das
 * regras posteriores. Em tempo, a avaliação de uma regra
 * produz um único valor associado à variável definida pela
 * regra. Contudo, se a regra se aplica a um conjunto de
 * avaliáveis, então cada avaliável terá o seu próprio
 * valor para essa variável, decorrente da execução da regra
 * para o avaliável em questão.
 */
package br.ufg.inf.es.saep.sandbox.dominio.avaliacao;
