/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Encapsula conjunto de valores que caracterizam
 * o relato de uma atividade ou produto.
 *
 * Cada relato é de um tipo específico, que
 * representa um tipo de atividade ou tipo de
 * produto. Esse tipo é mantido por uma instância
 * de {@link Tipo}. O tipo ainda define com
 * precisão os atributos correspondentes.
 *
 * Cada um dos valores do relato está associado
 * a um {@link Atributo} definido pelo tipo
 * do relato.
 *
 */
public class Relato implements Alteravel {
    private String tipo;
    private Map<String, Valor> valorPorNome;

    /**
     * Cria um relato a partir do tipo e valores correspondentes
     * fornecidos.
     *
     * @param tipo O código do tipo do relato.
     *
     * @param valores Conjunto identificadaPor valores para os tipos
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
}
