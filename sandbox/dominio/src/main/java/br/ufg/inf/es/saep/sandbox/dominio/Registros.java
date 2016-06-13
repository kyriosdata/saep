/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Registros de uma atividade ou produto.
 */
public class Registros {
    private List<Map<String,BigDecimal>> valores;

    public Registros(List<Map<String,BigDecimal>> valores) {
        this.valores = valores;
    }

    /**
     * Recupera o valor do atributo para o registro
     * (relato de uma atividade ou produto).
     *
     * @param atributo O nome do atributo.
     * @param registro O identificador único do relato.
     *
     * @return O valor do atributo do registro.
     */
    public BigDecimal get(int registro, String atributo) {
        return valores.get(registro).get(atributo);
    }

    public int getTotal() {
        return valores.size();
    }
}
