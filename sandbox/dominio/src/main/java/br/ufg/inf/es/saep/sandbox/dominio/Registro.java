/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import java.math.BigDecimal;

/**
 * Registro de uma atividade ou produto.
 */
public class Registro {
    /**
     * Recupera o valor do atributo para o registro.
     * @param atributo O nome do atributo.
     * @param registro O identificador do registro.
     *
     * @return O valor do atributo do registro.
     */
    public BigDecimal get(int registro, String atributo) {
        return new BigDecimal(registro);
    }
}
