/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

/**
 * Abstração dos serviços de persistência de pareceres.
 *
 * Um parecer é o resultado produzido pela avaliação
 * de um conjunto de relatos (RADOC) conforme uma dada
 * resolução. O parecer pode ser produzido pela Comissão
 * de Avaliação Docente (CAD) ou automaticamente pelo
 * SAEP.
 *
 */
public interface ParecerRepository {

    /**
     * Adiciona a alteração ao parecer.
     *
     * @param parecer O identificador único do parecer.
     *
     * @param alteracao A alteração a ser acrescentada ao
     * pareder.
     */
    void adicionaAlteracao(String parecer, Alteracao alteracao);

    /**
     * Acrescenta o parecer ao repositório.
     *
     * @param parecer O parecer a ser persistido.
     */
    void persisteParecer(Parecer parecer);

    /**
     * Altera a fundamentação do parecer.
     *
     * <p>Fundamentação é o texto propriamente dito do
     * parecer. Não confunda com as alterações de
     * valores (dados de relatos ou das pontuações).
     *
     * @param fundamentacao Novo texto da pontuação.
     */
    void atualizaFundamentacao(String fundamentacao);

    /**
     * Recupera o parecer pelo identificador.
     *
     * @param id O identificador do parecer.
     *
     * @return O parecer recuperado ou o valor {@code null},
     * caso o identificador não defina um parecer.
     */
    Parecer byId(String id);

    /**
     * Remove o parecer.
     *
     * @param id O identificador único do parecer.
     */
    void remove(String id);
}
