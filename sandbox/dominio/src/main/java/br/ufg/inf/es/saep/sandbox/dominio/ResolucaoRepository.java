/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.List;

/**
 * Serviços oferecidos para abstração da implementação da
 * persistência de resoluções.
 *
 * <p>Uma resolução é formada por um conjunto de regras.
 * Está além do escopo do SAEP a edição de resoluções.
 * Dessa forma, a persistência não inclui atualização,
 * mas apenas consulta, acréscimo e remoção.
 *
 * <p>Dada a sensibilidade, os raros usuários autorizados
 * e a frequência, a edição pode ser realizada por pessoal
 * técnico que produzirá uma instância de {@link Radoc} a
 * ser recebida pelo presente repositório.
 *
 * @see Resolucao
 */
public interface ResolucaoRepository {

    /**
     * Recupera a instância de {@code Resolucao} correspondente
     * ao identificador.
     *
     * @param identificador O identificador único da resolução a
     *                      ser recuperada.
     *
     * @return {@code Resolucao} identificada por {@code identificador}.
     * O retorno {@code null} indica que não existe resolução
     * com o identificador fornecido.
     *
     * @see #persiste(Resolucao)
     */
    Resolucao byId(String identificador);

    /**
     * Persiste uma nova resolução.
     *
     * @param resolucao A resolução a ser persistida.
     *
     * @return O identificador único da resolução, conforme
     * fornecido em propriedade do objeto fornecido. Observe que
     * o método retorna {@code null} para indicar que a
     * operação não foi realizada de forma satisfatória,
     * possivelmente por já existir resolução com
     * identificador semelhante.
     *
     * @see #byId(String)
     */
    String persiste(Resolucao resolucao);

    /**
     * Remove a resolução com o identificador
     * fornecido.
     *
     * @param identificador O identificador (uso externo) da
     *                      resolução a ser removida.
     *
     * @return O valor {@code true} se a operação foi
     * executada de forma satisfatória e {@code false},
     * caso contrário.
     */
    boolean remove(String identificador);

    /**
     * Recupera a lista dos identificadores das
     * resoluções disponíveis.
     *
     * @return Identificadores das resoluções disponíveis.
     */
    List<String> resolucoes();

    /**
     * Persiste o tipo fornecido.
     * @param tipo
     */
    void persiste(Tipo tipo);

    /**
     * Recupera o tipo com o código fornecido.
     *
     * @param codigo O código único do tipo.
     *
     * @return A instância de {@link Tipo} cujo
     * código único é fornecido. Retorna {@code null}
     * caso não exista tipo com o código indicado.
     */
    Tipo byCodigo(String codigo);

    /**
     * Recupera a lista de tipos cujos nomes
     * são similares àquele fornecido.
     *
     * Um nome é dito similar se contém a sequência
     * indicada.
     *
     * @param nome Sequência que será empregada para
     *             localizar tipos por nome.
     *
     * @return A coleção de tipos cujos nomes satisfazem
     * um padrão de semelhança com a sequência indicada.
     */
    List<Tipo> byNome(String nome);
}
