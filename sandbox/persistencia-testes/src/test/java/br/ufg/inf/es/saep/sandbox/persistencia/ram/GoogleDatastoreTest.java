package br.ufg.inf.es.saep.sandbox.persistencia.ram;

import br.ufg.inf.es.saep.sandbox.dominio.Regra;
import br.ufg.inf.es.saep.sandbox.dominio.Resolucao;
import com.google.cloud.AuthCredentials;
import com.google.cloud.datastore.*;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

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

        List<String> dd = new ArrayList<>(1);
        dd.add("e");
        Regra r1 = new Regra("v1", Regra.EXPRESSAO, "dr1", 1, 0, "e", null, null, "t", 0, dd);
        Regra r2 = new Regra("v2", Regra.PONTOS, "dr2", 1, 0, null, null, null, "t", 0, null);

        List<Regra> regras = new ArrayList<>(2);
        regras.add(r1);
        regras.add(r2);

        Resolucao resolucao = new Resolucao("r", "resolucao", "descricao", new Date(), regras);

        ObjectMapper mapper = new ObjectMapper();
        String json = toString(resolucao);
        System.out.println(json);

        System.out.println(toString(resolucao.getRegras()));

        Map<String, Object> lido = mapper.readValue(json, Map.class);
        for (String chave : lido.keySet()) {
            System.out.println(chave + " " + lido.get(chave));
        }

        List<Object> regrasRecuperadas = (ArrayList<Object>)lido.get("regras");
        if (regrasRecuperadas != null) {
            LinkedHashMap<String, Object> regra = (LinkedHashMap<String, Object>)regrasRecuperadas.get(0);
            System.out.println(regra.get("descricao"));
        }
    }

    public String toString(Resolucao resolucao) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(resolucao);
    }

    public String toString(List<Regra> regras) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(regras);
    }

    public List<Object> fromString(String regras) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Object> lista = mapper.readValue(regras, List.class);
        System.out.println(lista.get(0));
        return lista;
    }

    public Entity toEntity(Resolucao resolucao) throws IOException {
        Key key = keyResolucao.newKey(resolucao.getId());
        Entity re = Entity.builder(key)
                .set("nome", resolucao.getNome())
                .set("descricao", resolucao.getDescricao())
                .set("data", resolucao.getDataAprovacao().getTime())
                .set("regras", toString(resolucao.getRegras()))
                .build();

        return re;
    }

    @Test
    public void insereResolucao() throws IOException {
        Resolucao resolucao = criaResolucaoComDuasRegras();

        Entity entidade = toEntity(resolucao);
        gds.put(entidade);

        Key key = keyResolucao.newKey(resolucao.getId());
        gds.get(key);
    }

    private Resolucao fromEntity(String id) {
        Key key = keyResolucao.newKey(id);
        Entity dsr = gds.get(key);

        String nome = dsr.getString("nome");
        String descricao = dsr.getString("descricao");
        DateTime dataAprovacao = dsr.getDateTime("data");
        String regras = dsr.getString("regras");

        return null;
    }

    private Resolucao criaResolucaoComDuasRegras() {
        List<String> dd = new ArrayList<>(1);
        dd.add("e");

        Regra r1 = new Regra("v1", Regra.EXPRESSAO, "dr1", 1, 0, "e", null, null, "t", 0, dd);
        Regra r2 = new Regra("v2", Regra.PONTOS, "dr2", 1, 0, null, null, null, "t", 0, null);

        List<Regra> regras = new ArrayList<>(2);
        regras.add(r1);
        regras.add(r2);

        String id = UUID.randomUUID().toString();
        return new Resolucao(id, "resolucao", "descricao", new Date(), regras);
    }
}
