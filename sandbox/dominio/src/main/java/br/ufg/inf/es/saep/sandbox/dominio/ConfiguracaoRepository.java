/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import br.ufg.inf.es.saep.sandbox.dominio.regra.Configuracao;

import java.util.List;

/**
 * Operações para oferecer a noção de coleções
 * de configurações em memória.
 *
 * <p>Uma resolução é formada por um conjunto de regras.
 * Está além do escopo do SAEP a edição de resoluções.
 * Dessa forma, a persistência não inclui atualização,
 * mas apenas consulta, acréscimo e remoção.
 *
 * <p>Dada a sensibilidade, os raros usuários autorizados
 * e a frequência, a edição pode ser realizada por pessoal
 * técnico que produzirá uma instância de {@link Configuracao} a
 * ser recebida pelo presente repositório.
 *
 * <p>Não existe opção para atualizar uma {@link Configuracao}.
 * Um parecer disponível, se tem a resolução correspondente
 * alterada, pode dar origem a um resultado distinto.
 * Em consequência, não existe opção para atualização de
 * {@link Configuracao}.
 *
 * @see Configuracao
 */
public interface ConfiguracaoRepository {

    /**
     * Recupera a instância de {@code Configuracao} correspondente
     * ao identificador.
     *
     * @param id O identificador único da resolução a
     *                      ser recuperada.
     *
     * @return {@code Configuracao} identificada por {@code id}.
     * O retorno {@code null} indica que não existe resolução
     * com o identificador fornecido.
     *
     * @see #persiste(Configuracao)
     */
    Configuracao byId(String id);

    /**
     * Persiste uma resolução.
     *
     * @throws
     *  br.ufg.inf.es.saep.sandbox.dominio.excecoes.CampoExigidoNaoFornecido
     *      Caso o identificador não
     *      seja fornecido.
     *
     * @throws
     *  br.ufg.inf.es.saep.sandbox.dominio.excecoes.IdentificadorExistente
     *      Caso uma resolução com identificador
     *      igual àquele fornecido já exista.
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
     * @see #remove(String)
     */
    String persiste(Configuracao resolucao);

    /**
     * Remove a resolução com o identificador
     * fornecido.
     *
     * @see #persiste(Configuracao)
     *
     * @param identificador O identificador único da
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
     *
     * @throws IdentificadorExistente Caso o tipo já
     * esteja persistido no repositório.
     *
     * @param tipo O objeto a ser persistido.
     */
    void persisteTipo(Classe tipo);

    /**
     * Remove o tipo.
     *
     * @throws ResolucaoUsaTipoException O tipo
     * é empregado por pelo menos uma resolução.
     *
     * @param codigo O identificador do tipo a
     *               ser removido.
     */
    void removeTipo(String codigo);

    /**
     * Recupera o tipo com o código fornecido.
     *
     * @param codigo O código único do tipo.
     *
     * @return A instância de {@link Classe} cujo
     * código único é fornecido. Retorna {@code null}
     * caso não exista tipo com o código indicado.
     */
    Classe tipoPeloCodigo(String codigo);

    /**
     * Recupera a lista de tipos cujos nomes
     * são similares àquele fornecido. Um nome é
     * similar àquele do tipo caso contenha o
     * argumento fornecido. Por exemplo, para o nome
     * "casa" temos que "asa" é similar.
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
    List<Classe> tiposPeloNome(String nome);
}
