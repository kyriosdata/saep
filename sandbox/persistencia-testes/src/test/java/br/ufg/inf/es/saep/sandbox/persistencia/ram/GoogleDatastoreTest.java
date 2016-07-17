package br.ufg.inf.es.saep.sandbox.persistencia.ram;

import br.ufg.inf.es.saep.sandbox.dominio.Regra;
import com.google.cloud.AuthCredentials;
import com.google.cloud.datastore.*;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

/**
 * Experimentos preliminares com Google Datastore e Jackson.
 * Jackson para serializar objeto em String e realizar o
 * processo inverso. Google Datastore para o registro em
 * meio persistente.
 */
public class GoogleDatastoreTest {

    private static Datastore gds;
    private static KeyFactory keyRadoc;
    private static KeyFactory keyResolucao;

    @BeforeClass
    public static void setUpClass() throws IOException {

        // Guardar de outro jeito (consulte issue para detalhes):
        // https://github.com/kyriosdata/saep/issues/5
        // (por enquanto, evita "segredo" em local público)
        String maven = System.getenv("HOME") + "/.m2/";
        String credencial = maven + "saep-ufg.json";

        FileInputStream json = new FileInputStream(credencial);

        DatastoreOptions options = DatastoreOptions.builder()
                .projectId("saepufg")
                .authCredentials(AuthCredentials.createForJson(json))
                .build();

        gds = options.service();

        keyRadoc = gds.newKeyFactory().kind("RADOC");
        keyResolucao = gds.newKeyFactory().kind("RESOLUCAO");
    }

    @Test
    public void criaRadocArbitrarioDepoisRemove() {

        // id arbitrário (assegura que não existe o radoc)
        String id = UUID.randomUUID().toString();
        Key key = keyRadoc.newKey(id);

        assertNull(gds.get(key));

        // Cria radoc com id arbitrário
        Entity radoc = Entity.builder(key).set("relatos", "radoc 3").build();
        gds.put(radoc);

        assertEquals("radoc 3", gds.get(key).getString("relatos"));

        gds.delete(key);
        assertNull(gds.get(key));
    }

    @Test
    public void serializarRegra() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        List<String> dd = new ArrayList<>(1);
        dd.add("e");
        Regra r = new Regra("v", Regra.PONTOS, "d", 1, 0, "e", null, null, "t", 0, dd);

        String json = mapper.writeValueAsString(r);
        System.out.println(json);

        Map<String, Object> lido = mapper.readValue(json, Map.class);
        for (String chave : lido.keySet()) {
            System.out.println(chave + " " + lido.get(chave));
        }

        List<Object> dependeDe = (ArrayList<Object>)lido.get("dependeDe");
        if (dependeDe != null) {
            System.out.println(dependeDe.get(0));
        }
    }
}
