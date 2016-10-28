/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio.infraestrutura;

import br.ufg.inf.es.saep.sandbox.dominio.Valor;
import com.google.gson.*;

import java.lang.reflect.Type;

import static br.ufg.inf.es.saep.sandbox.dominio.Valor.DATA;
import static br.ufg.inf.es.saep.sandbox.dominio.Valor.LOGICO;
import static br.ufg.inf.es.saep.sandbox.dominio.Valor.REAL;

/**
 * Define processo de conversão de "string" em instância
 * de Valor.
 *
 * @see Valor
 */
public class ValorDeserializer implements JsonDeserializer<Valor> {

    @Override
    public Valor deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        byte tipo = jsonObject.get("tipo").getAsByte();
        JsonElement valor = jsonObject.get("valor");

        if (tipo == Valor.STRING) {
            return new Valor(valor.getAsString());
        } else if (tipo == REAL) {
            return new Valor(valor.getAsFloat());
        } else if (tipo == LOGICO) {
            return new Valor(valor.getAsBoolean());
        } else if (tipo == DATA) {
            return Valor.dataFromString(valor.getAsString());
        }

        return null;
    }
}
