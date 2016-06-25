/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.Map;

/**
 * Encapsula conjunto de valores que caracterizam
 * o relato de uma atividade ou produto.
 *
 * Cada relato é de um tipo específico, que
 * representa um tipo de atividade ou tipo de
 * produto. Esse tipo é identificado por
 * {@link #tipo}.
 *
 */
public class Relato implements Avaliavel {
    private String tipo;
    private Map<String, Valor> valorPorNome;

    /**
     * Cria um relato a partir do tipo e valores correspondentes
     * fornecidos.
     *
     * @param tipo O código do tipo do relato.
     *
     * @param valores Conjunto byId valores para os tipos
     *                do relato.
     */
    public Relato(String tipo, Map<String, Valor> valores) {
        this.tipo = tipo;
        valorPorNome = valores;
    }

    /**
     * Recupera o valor do atributo.
     *
     * @param atributo O identificador único do atributo.
     *
     * @return O valor do atributo.
     */
    public Valor get(String atributo) {
        return valorPorNome.get(atributo);
    }

    public String getTipo() {
        return tipo;
    }
}
