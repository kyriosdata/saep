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
 * <p>Cada relato é de um classe específico, que
 * representa um classe de atividade ou classe de
 * produto. Esse classe é identificado por
 * {@link #classe}.
 *
 * <p>Um relato é um "value object". Ou seja, dois
 * relatos são iguais se todos os seus atributos
 * forem iguais.
 */
public class Relato implements Avaliavel {
    private String classe;
    private Map<String, Valor> valores;

    /**
     * Cria um relato a partir do classe e valores correspondentes
     * fornecidos.
     *
     * @param tipo    O código do classe do relato.
     * @param valores Conjunto parecerById valores para os tipos
     *                do relato.
     */
    public Relato(String tipo, Map<String, Valor> valores) {
        if (tipo == null || tipo.isEmpty()) {
            throw new CampoExigidoNaoFornecido("classe");
        }

        if (valores == null || valores.size() == 0) {
            throw new CampoExigidoNaoFornecido("valores");
        }

        this.classe = tipo;
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
     * Recupera o conjunto de variáveis (identificadores)
     * dos valores do relato.
     *
     * @return Conjunto de identificadores dos valores do
     * relato.
     */
    public Set<String> getVariaveis() {
        return valores.keySet();
    }

    /**
     * Recupera o classe do relato.
     *
     * @return O identificador único do classe do relato.
     */
    public String getClasse() {
        return classe;
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

        if (!classe.equals(outro.classe)) {
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
        return classe.hashCode();
    }
}
