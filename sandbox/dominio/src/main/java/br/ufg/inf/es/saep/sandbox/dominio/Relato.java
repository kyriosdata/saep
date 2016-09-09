/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.Map;
import java.util.Set;

/**
 * Encapsula conjunto de atributos que caracterizam
 * o relato de uma atividade ou produto.
 *
 * <p>Cada relato é de uma classe específica,
 * identificado por {@link #classe}.
 *
 * <p>Um relato é um "value object". Ou seja, dois
 * relatos são iguais se todos os seus atributos
 * forem iguais.
 */
public class Relato implements Avaliavel {

    /**
     * Classe do relato. Suficiente para
     * determinar o conjunto de atributos
     * esperados.
     */
    private String classe;

    /**
     * Os atributos do relato.
     */
    private Map<String, Valor> atributos;

    /**
     * Cria um relato a partir do classe e atributos correspondentes
     * fornecidos.
     *
     * @param tipo    O código do classe do relato.
     * @param valores Conjunto parecerById atributos para os tipos
     *                do relato.
     */
    public Relato(String tipo, Map<String, Valor> valores) {
        if (tipo == null || tipo.isEmpty()) {
            throw new CampoExigidoNaoFornecido("classe");
        }

        if (valores == null || valores.size() == 0) {
            throw new CampoExigidoNaoFornecido("atributos");
        }

        this.classe = tipo;
        this.atributos = valores;
    }

    /**
     * Recupera o valor do atributo.
     *
     * @param atributo O identificador único do atributo.
     * @return O valor do atributo ou {@code null},
     * caso o atributo não faça parte do relato.
     */
    public Valor get(String atributo) {

        return atributos.get(atributo);
    }

    /**
     * Recupera o conjunto de variáveis (identificadores)
     * dos atributos do relato.
     *
     * @return Conjunto de identificadores dos atributos do
     * relato.
     */
    public Set<String> getVariaveis() {
        return atributos.keySet();
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

        Set<String> keys = atributos.keySet();
        Set<String> otherKeys = outro.atributos.keySet();

        if (keys.size() != otherKeys.size()) {
            return false;
        }

        for (String key : keys) {
            Valor esseValor = atributos.get(key);
            Valor outroValor = outro.atributos.get(key);

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
