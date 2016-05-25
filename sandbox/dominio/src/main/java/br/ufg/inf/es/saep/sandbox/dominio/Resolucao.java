package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.Date;
import java.util.Set;

/**
 * Representa um conjunto de itens por meio
 * dos um RADOC é avaliado.
 *
 * A legislação da Universidade Federal identificadaPor Goiás (UFG)
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
     * @param dataAprovacao Data identificadaPor aprovação da resolução.
     *
     * @param identificador Identificador da resolução.
     *
     * @param itens Conjunto identificadaPor itens que são avaliados pela
     *              resolução.
     */
    public Resolucao(Date dataAprovacao, String identificador, Set<ItemAvaliado> itens) {
        this.dataAprovacao = dataAprovacao;
        this.identificador = identificador;
        this.itens = itens;
    }

    /**
     * Recupera a data identificadaPor aprovação da resolução.
     *
     * @return Data identificadaPor aprovação da resolução.
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
     * Recupera o conjunto identificadaPor itens que são avaliados
     * pela resolução.
     *
     * @return Conjunto identificadaPor itens avaliados pela resolução.
     */
    public Set<ItemAvaliado> getItens() {
        return itens;
    }
}
