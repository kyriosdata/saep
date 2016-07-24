/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.persistencia.gds;

import br.ufg.inf.es.saep.sandbox.dominio.Resolucao;
import br.ufg.inf.es.saep.sandbox.dominio.ResolucaoRepository;
import br.ufg.inf.es.saep.sandbox.dominio.Tipo;
import com.google.cloud.AuthCredentials;
import com.google.cloud.datastore.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação do repositório de resoluções usando
 * Google Datastore (NoSQL).
 *
 */
public class GoogleDatastoreImpl implements ResolucaoRepository {

    private String RESOLUCAO_KIND = "RESOLUCAO";
    private Datastore gds;
    private KeyFactory keyResolucao;
    private KeyFactory keyTipo;
    private final String PAYLOAD = "objeto";

    public GoogleDatastoreImpl() {

        // Guardar de outro jeito (consulte issue para detalhes):
        // https://github.com/kyriosdata/saep/issues/5
        // (por enquanto, evita "segredo" em local público)
        String maven = System.getenv("HOME") + "/.m2/";
        String credencial = maven + "saep-ufg.json";

        try {
            FileInputStream json = new FileInputStream(credencial);

            DatastoreOptions options = DatastoreOptions.builder()
                    .projectId("saepufg")
                    .authCredentials(AuthCredentials.createForJson(json))
                    .build();

            gds = options.service();

            keyResolucao = gds.newKeyFactory().kind(RESOLUCAO_KIND);
            keyTipo = gds.newKeyFactory().kind("TIPO");
        } catch (Exception e) {
            // TODO Não definido no contrato (verificar)
            throw new RuntimeException("persistencia indisponivel");
        }
    }

    private Key getKeyResolucao(String id) {
        return keyResolucao.newKey(id);
    }

    private Key getKeyTipo(String id) {
        return keyTipo.newKey(id);
    }

    @Override
    public Resolucao byId(String s) {
        try {
            Entity dsr = gds.get(getKeyResolucao(s));

            if (dsr == null) {
                return null;
            }

            String objeto = dsr.getString(PAYLOAD);

            Type tipo = new TypeToken<Resolucao>(){}.getType();
            return (Resolucao)new Gson().fromJson(objeto, tipo);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String persiste(Resolucao resolucao) {
        try {
            Key key = getKeyResolucao(resolucao.getId());
            String json = new Gson().toJson(resolucao);

            Entity entidade = Entity.builder(key).set(PAYLOAD, json).build();
            gds.put(entidade);

            return resolucao.getId();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean remove(String s) {
        try {
            gds.delete(getKeyResolucao(s));
            return true;
        } catch (Exception exp) {
            return false;
        }
    }

    @Override
    public List<String> resolucoes() {
        Query<Key> all = Query.keyQueryBuilder().kind(RESOLUCAO_KIND).build();
        QueryResults<Key> resultados = gds.run(all);

        List<String> retorno = new ArrayList<>();
        while (resultados.hasNext()) {
            retorno.add(resultados.next().name());
        }

        return retorno;
    }

    @Override
    public void persisteTipo(Tipo tipo) {
        try {
            Key key = getKeyTipo(tipo.getId());
            String json = new Gson().toJson(tipo);

            gds.put(Entity.builder(key).set(PAYLOAD, json).build());
        } catch (Exception e) {
            // TODO Nada definido aqui, contrato incompleto!!!
        }
    }

    @Override
    public void removeTipo(String s) {
        try {
            gds.delete(getKeyTipo(s));
        } catch (Exception exp) {
            // TODO Nada definido aqui, contrato incompleto!!!
        }
    }

    @Override
    public Tipo tipoPeloCodigo(String s) {
        try {
            Entity dsr = gds.get(getKeyTipo(s));

            if (dsr == null) {
                return null;
            }

            String objeto = dsr.getString(PAYLOAD);

            Type tipo = new TypeToken<Tipo>(){}.getType();
            return (Tipo)new Gson().fromJson(objeto, tipo);

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Tipo> tiposPeloNome(String s) {
        return null;
    }
}
