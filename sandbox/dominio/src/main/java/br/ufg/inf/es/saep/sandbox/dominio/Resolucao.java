package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.Date;
import java.util.Set;

/**
 * Representa um conjunto de regras por meio
 * dos itens que são avaliados.
 *
 * A legislação da Universidade Federal de Goiás (UFG)
 * está organizada por meio de resoluções.
 */
public class Resolucao {
    private Date dataAprovacao;
    private String identificador;
    private Set<ItemAvaliado> itens;

    /**
     * Cria uma resolução a partir dos argumentos
     * identificados.
     *
     * @param dataAprovacao Data de aprovação da resolução.
     *
     * @param identificador Identificador da resolução.
     *
     * @param itens Conjunto de itens que são avaliados pela
     *              resolução.
     */
    public Resolucao(Date dataAprovacao, String identificador, Set<ItemAvaliado> itens) {
        this.dataAprovacao = dataAprovacao;
        this.identificador = identificador;
        this.itens = itens;
    }

    /**
     * Recupera a data de aprovação da resolução.
     *
     * @return Data de aprovação da resolução.
     */
    public Date getDataAprovacao() {
        return dataAprovacao;
    }

    /**
     * Recupera o identificador único da resolução.
     *
     * @return O identificador único da resolução.
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * Recupera o conjunto de itens que são avaliados
     * pela resolução.
     *
     * @return Conjunto de itens avaliados pela resolução.
     */
    public Set<ItemAvaliado> getItens() {
        return itens;
    }
}
