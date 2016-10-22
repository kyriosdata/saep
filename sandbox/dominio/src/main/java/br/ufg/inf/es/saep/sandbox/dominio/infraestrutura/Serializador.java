/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio.infraestrutura;

import br.ufg.inf.es.saep.sandbox.dominio.Pontuacao;
import br.ufg.inf.es.saep.sandbox.dominio.Valor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Serviço para conversão de objetos do domínio em
 * sequências de caracteres e, no sentido inverso,
 * recuperação de objetos a partir de tais sequências.
 */
public class Serializador {

    private static Gson gson;
    private static Type valorType;
    private static Type pontuacaoType;

    /**
     * Cria instância de serializar preparada
     * para realizar conversões entre objetos e
     * sequências de caracters.
     */
    public Serializador() {
        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(Valor.class, new ValorSerializer());
        gb.registerTypeAdapter(Valor.class, new ValorDeserializer());
        gson = gb.create();

        valorType = new TypeToken<Valor>() {}.getType();
        pontuacaoType = new TypeToken<Pontuacao>() {}.getType();
    }

    /**
     * Converte instância de {@link Valor} em
     * sequência de caracteres.
     *
     * @param v Instância de {@link Valor}.
     *
     * @return Sequência de caracteres correspondente ao
     * objeto fornecido no primeiro parâmetro.
     */
    public String toJson(Valor v) {
        return gson.toJson(v, valorType);
    }

    /**
     * Converte sequência de caracteres em instância
     * de {@link Valor}.
     *
     * @param json Sequência de caracteres correspondente
     *             a uma instância de {@link Valor}.
     *
     * @return Instância de {@link Valor} obtida da
     * sequência de caracteres fornecida.
     */
    public Valor valor(String json) {
        return gson.fromJson(json, valorType);
    }

    public String toJson(Pontuacao v) {
        return gson.toJson(v, pontuacaoType);
    }

    public Pontuacao pontuacao(String json) {
        return gson.fromJson(json, pontuacaoType);
    }
}


