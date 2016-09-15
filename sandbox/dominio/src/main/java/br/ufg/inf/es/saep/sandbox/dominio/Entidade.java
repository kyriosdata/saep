/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import br.ufg.inf.es.saep.sandbox.dominio.excecoes.CampoExigidoNaoFornecido;

import java.util.UUID;

/**
 * Superclasse de toda entidade do domínio.
 */
public abstract class Entidade {

    /**
     * Identificador único da entidade.
     */
    private String id;

    /**
     * Constrói uma entidade com identificador
     * único gerado aleatoriamente.
     */
    public Entidade() {
        id = UUID.randomUUID().toString();
    }

    /**
     * Constrói uma entidade com o identificador
     * únido fornecido.
     *
     * @param id O identificador únido da entidade.
     *
     * @throws CampoExigidoNaoFornecido Se o identificador
     *      fornecido é {@code null} ou vazio.
     */
    public Entidade(String id) {
        if (id == null || id.isEmpty()) {
            throw new CampoExigidoNaoFornecido("id");
        }

        this.id = id;
    }

    /**
     * Recupera o identificador único da entidade.
     *
     * @return Identificador único da entidade.
     */
    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object outro) {
        if (this == outro) {
            return true;
        }

        if (outro == null || getClass() != outro.getClass()) {
            return false;
        }

        Entidade entidade = (Entidade) outro;

        return id.equals(entidade.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
