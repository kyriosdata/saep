/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.persistencia.gds;

import com.google.cloud.AuthCredentials;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;

import java.io.FileInputStream;

/**
 * Fábrica de objeto por meio do qual se obtém
 * acesso aos serviços do Google Datastore (NoSQL).
 *
 */
public class GoogleDatastoreFactory {

    private String RESOLUCAO_KIND = "RESOLUCAO";
    private String TIPO_KIND = "TIPO";
    private Datastore gds;
    private final String PAYLOAD = "objeto";
    private static final String PROJECT_ID = "saepufg";

    public static Datastore getInstance() {

        // Guardar de outro jeito (consulte issue para detalhes):
        // https://github.com/kyriosdata/saep/issues/5
        // (por enquanto, evita "segredo" em local público)
        String maven = System.getenv("HOME") + "/.m2/";
        String credencial = maven + "saep-ufg.json";

        try {
            FileInputStream json = new FileInputStream(credencial);

            DatastoreOptions options = DatastoreOptions.builder()
                    .projectId(PROJECT_ID)
                    .authCredentials(AuthCredentials.createForJson(json))
                    .build();

            return options.service();
        } catch (Exception e) {
            // TODO Não definido no contrato (verificar)
            throw new RuntimeException("persistencia indisponivel");
        }
    }
}
