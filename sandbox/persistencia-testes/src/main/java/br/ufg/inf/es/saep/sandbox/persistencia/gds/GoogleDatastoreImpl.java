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
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação do repositório de resoluções usando
 * Google Datastore (NoSQL).
 *
 */
public class GoogleDatastoreImpl implements ResolucaoRepository {

    private static String RESOLUCAO_KIND = "RESOLUCAO";
    private static Datastore gds;
    private static KeyFactory keyRadoc;
    private static KeyFactory keyResolucao;
    private static KeyFactory keyTipo;

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

            keyRadoc = gds.newKeyFactory().kind("RADOC");
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

    /**
     * Monta entidade correspondente à resolução.
     *
     * @param resolucao A resolução.
     *
     * @return Entidade obtida da resolução.
     *
     * @throws IOException
     */
    private Entity resolucaoToEntity(Resolucao resolucao) throws IOException {
        Key key = keyResolucao.newKey(resolucao.getId());
        Entity re = Entity.builder(key)
                .set("objeto", new Gson().toJson(resolucao)).build();

        return re;
    }

    private Entity tipoToEntity(Tipo tipo) throws IOException {
        Key key = getKeyTipo(tipo.getId());
        Entity re = Entity.builder(key)
                .set("objeto", new Gson().toJson(tipo)).build();

        return re;
    }

    private Resolucao resolucaoFromEntity(String id) throws IOException {
        Key key = keyResolucao.newKey(id);
        Entity dsr = gds.get(key);

        if (dsr == null) {
            return null;
        }

        String objeto = dsr.getString("objeto");

        Type tipo = new TypeToken<Resolucao>(){}.getType();
        return (Resolucao)new Gson().fromJson(objeto, tipo);
    }

    @Override
    public Resolucao byId(String s) {
        try {
            return resolucaoFromEntity(s);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public String persiste(Resolucao resolucao) {
        try {
            Entity entidade = resolucaoToEntity(resolucao);
            gds.put(entidade);

            return resolucao.getId();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public boolean remove(String s) {
        try {
            Key key = getKeyResolucao(s);
            gds.delete(key);
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
            Entity entidade = tipoToEntity(tipo);
            gds.put(entidade);
        } catch (IOException e) {
            // Nada definido aqui, provavelmente
            // contrato incompleto!!!
        }
    }

    @Override
    public void removeTipo(String s) {
        try {
            Key key = getKeyTipo(s);
            gds.delete(key);
        } catch (Exception exp) {
            // Nada definido aqui, provavelmente
            // contrato incompleto!!!
        }
    }

    @Override
    public Tipo tipoPeloCodigo(String s) {
        try {
            Key key = keyTipo.newKey(s);
            Entity dsr = gds.get(key);

            if (dsr == null) {
                return null;
            }

            String objeto = dsr.getString("objeto");

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
