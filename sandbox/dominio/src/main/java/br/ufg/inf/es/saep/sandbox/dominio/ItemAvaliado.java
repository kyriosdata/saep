package br.ufg.inf.es.saep.sandbox.dominio;

/**
 * Representa qualquer relato que é passível
 * de ser avaliado.
 */
public abstract class ItemAvaliado {
    private Regra regra;
    private String descricao;
    private String nome;

    /**
     * Recupera a regra de avaliação do item.
     *
     * @return Regra que avalia o item.
     */
    public Regra getRegra() {
        return regra;
    }

    /**
     * Recupera a descrição do item.
     *
     * @return Sequência que descreve o item.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Recupera o nome único associado ao item.
     *
     * @return Código único do item.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Cria um item que pode ser avaliado.
     *
     * @param regra Regra empregado na avaliação do item.
     *
     * @param descricao Descrição do item.
     *
     * @param nome Código único do item.

     */
    public ItemAvaliado(Regra regra, String descricao, String nome) {
        this.regra = regra;
        this.descricao = descricao;
        this.nome = nome;
    }
}
