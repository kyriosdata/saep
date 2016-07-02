/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.Map;
import java.util.Set;

/**
 * Encapsula conjunto de valores que caracterizam
 * o relato de uma atividade ou produto.
 *
 * <p>Cada relato é de um tipo específico, que
 * representa um tipo de atividade ou tipo de
 * produto. Esse tipo é identificado por
 * {@link #tipo}.
 *
 * <p>Um relato é um "value object". Ou seja, dois
 * relatos são iguais se todos os seus atributos
 * forem iguais.
 */
public class Relato implements Avaliavel {
    private String tipo;
    private Map<String, Valor> valores;

    /**
     * Cria um relato a partir do tipo e valores correspondentes
     * fornecidos.
     *
     * @param tipo    O código do tipo do relato.
     * @param valores Conjunto byId valores para os tipos
     *                do relato.
     */
    public Relato(String tipo, Map<String, Valor> valores) {
        if (tipo == null || tipo.isEmpty()) {
            throw new CampoExigidoNaoFornecido("tipo");
        }

        if (valores == null || valores.size() == 0) {
            throw new CampoExigidoNaoFornecido("valores");
        }

        this.tipo = tipo;
        this.valores = valores;
    }

    /**
     * Recupera o valor do atributo.
     *
     * @param atributo O identificador único do atributo.
     * @return O valor do atributo ou {@code null},
     * caso o atributo não faça parte do relato.
     */
    public Valor get(String atributo) {

        return valores.get(atributo);
    }

    /**
     * Recupera o tipo do relato.
     *
     * @return O identificador único do tipo do relato.
     */
    public String getTipo() {
        return tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Relato outro = (Relato) o;

        if (!tipo.equals(outro.tipo)) {
            return false;
        }

        Set<String> keys = valores.keySet();
        Set<String> otherKeys = outro.valores.keySet();

        if (keys.size() != otherKeys.size()) {
            return false;
        }

        for (String key : keys) {
            Valor esseValor = valores.get(key);
            Valor outroValor = outro.valores.get(key);

            if (!esseValor.equals(outroValor)) {
                return false;
            }
        }

        return true;

    }

    @Override
    public int hashCode() {
        return tipo.hashCode();
    }
}
