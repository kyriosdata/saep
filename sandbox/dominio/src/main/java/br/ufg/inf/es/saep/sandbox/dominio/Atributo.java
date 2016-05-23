package br.ufg.inf.es.saep.sandbox.dominio;

/**
 * Definição de um tipo primitivo e um nome usados
 * para identificar o domínio de um valor.
 */
public class Atributo {
    private String nome;
    private TipoPrimitivo tipo;

    public Atributo(String nome, TipoPrimitivo tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    /**
     * Recupera o nome do atributo.
     *
     * @return O identificador do atributo.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Recupera o tipo primitivo do atributo.
     *
     * @return O tipo do atributo.
     */
    public TipoPrimitivo getTipo() {
        return tipo;
    }
}
