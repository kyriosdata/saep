package br.ufg.inf.es.saep.sandbox.infraestrutura;

import br.ufg.inf.es.saep.sandbox.dominio.Valor;
import com.google.gson.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.time.LocalDate;

public class SerializacaoTest {

    @Test
    public void serializacaoFuncao() {

        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(Valor.class, new ValorSerializer());

        Gson gson = gb.create();

        Valor v = new Valor(LocalDate.now());
        System.out.println(gson.toJson(v));

    }
}

class ValorSerializer implements JsonSerializer<Valor> {

    @Override
    public JsonElement serialize(Valor valor, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject obj = new JsonObject();
        byte tipo = valor.getTipo();

        obj.addProperty("tipo", tipo);
        switch (tipo) {
            case Valor.REAL:
                obj.addProperty("valor", valor.getReal());
                break;
            case Valor.LOGICO:
                obj.addProperty("valor", valor.getBoolean());
                break;
            case Valor.DATA:
                obj.addProperty("valor", valor.getData().toString());
                break;
            case Valor.STRING:
                obj.addProperty("valor", valor.getString());
                break;
        }

        return obj;
    }
}

