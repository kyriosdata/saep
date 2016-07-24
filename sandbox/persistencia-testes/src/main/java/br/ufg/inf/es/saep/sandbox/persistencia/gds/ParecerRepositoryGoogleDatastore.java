/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.persistencia.gds;

import br.ufg.inf.es.saep.sandbox.dominio.Parecer;
import br.ufg.inf.es.saep.sandbox.dominio.ParecerRepository;
import br.ufg.inf.es.saep.sandbox.dominio.Radoc;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Implementação do repositório de resoluções usando
 * Google Datastore (NoSQL).
 */
public class ParecerRepositoryGoogleDatastore implements ParecerRepository {

    private String RADOC_KIND = "RADOC";
    private String PARECER_KIND = "PARECER";
    private Datastore gds;
    private final String PAYLOAD = "objeto";

    /**
     * Define o objeto por meio do qual acesso aos serviços do
     * Google Datastore serão usufruídos.
     *
     * @param ds Objeto de acesso ao Google Datastore.
     */
    public void setDatastore(Datastore ds) {
        gds = ds;
    }

    private Key getKeyParecer(String id) {
        return gds.newKeyFactory().kind(PARECER_KIND).newKey(id);
    }

    private Key getKeyRadoc(String id) {
        return gds.newKeyFactory().kind(RADOC_KIND).newKey(id);
    }

    private boolean radocsExistem(Parecer parecer) {
        for (String radocId : parecer.getRadocsIds()) {
            if (radocById(radocId) == null) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void persisteParecer(Parecer parecer) {
        try {
            if (!radocsExistem(parecer)) {
                // TODO contrato não indica essa situação!?
                return;
            }

            Key key = getKeyParecer(parecer.getId());
            String json = new Gson().toJson(parecer);

            gds.put(Entity.builder(key).set(PAYLOAD, json).build());
        } catch (Exception e) {
            // TODO Nada definido aqui, contrato incompleto!!!
        }
    }

    @Override
    public void removeParecer(String s) {
        gdsDeleteByKey(getKeyParecer(s));
    }

    private void gdsDeleteByKey(Key key) {
        try {
            gds.delete(key);
        } catch (Exception exp) {
            // TODO Nada definido aqui, contrato incompleto!!!
        }
    }

    @Override
    public Parecer parecerById(String id) {
        try {
            Entity dsr = gds.get(getKeyParecer(id));

            if (dsr == null) {
                return null;
            }

            String objeto = dsr.getString(PAYLOAD);

            Type tipo = new TypeToken<Parecer>() {
            }.getType();
            return (Parecer) new Gson().fromJson(objeto, tipo);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String persisteRadoc(Radoc radoc) {
        try {
            Key key = getKeyRadoc(radoc.getId());
            String json = new Gson().toJson(radoc);

            gds.put(Entity.builder(key).set(PAYLOAD, json).build());
            return radoc.getId();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void removeRadoc(String id) {
        gdsDeleteByKey(getKeyRadoc(id));
    }

    @Override
    public Radoc radocById(String id) {
        try {
            Entity dsr = gds.get(getKeyRadoc(id));

            if (dsr == null) {
                return null;
            }

            String objeto = dsr.getString(PAYLOAD);

            Type tipo = new TypeToken<Radoc>(){}.getType();
            return (Radoc) new Gson().fromJson(objeto, tipo);
        } catch (Exception e) {
            return null;
        }
    }
}
