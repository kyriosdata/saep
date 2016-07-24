package br.ufg.inf.es.saep.sandbox.persistencia.ram;

import br.ufg.inf.es.saep.sandbox.dominio.*;
import com.google.cloud.AuthCredentials;
import com.google.cloud.datastore.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

import static junit.framework.TestCase.*;
import static org.junit.Assert.assertTrue;

/**
 * Experimentos preliminares com Google Datastore e Jackson.
 * Jackson para serializar objeto em String. Jackson também é
 * empregado para realizar o processo inverso.
 *
 */
public class GoogleDatastoreTest implements ResolucaoRepository {

    private static String RESOLUCAO_KIND = "RESOLUCAO";

    private static Datastore gds;
    private static KeyFactory keyRadoc;
    private static KeyFactory keyResolucao;
    private static KeyFactory keyTipo;

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
        keyResolucao = gds.newKeyFactory().kind(RESOLUCAO_KIND);
        keyTipo = gds.newKeyFactory().kind("TIPO");
    }

    @Test
    public void insereRecuperaConsultaRemoveTipo() {
        Tipo t = criaTipoComDoisAtributos();

        // Não encontra tipo não inserido
        assertNull(tipoPeloCodigo(t.getId()));

        // Persiste o tipo
        persisteTipo(t);

        // Encontra o persistido, identico ao inserido
        Tipo tipoRecuperado = tipoPeloCodigo(t.getId());
        assertEquals(t, tipoRecuperado);

        // Remove o tipo persistido
        removeTipo(tipoRecuperado.getId());

        // Não encontra tipo removido
        assertNull(tipoPeloCodigo(t.getId()));
    }

    private Tipo criaTipoComDoisAtributos() {
        // O nome da disciplina
        Atributo nome = new Atributo("nome",
                "nome da disciplina",
                Atributo.STRING);

        // A carga horária da disciplina
        Atributo cha = new Atributo("cha",
                "carga horária da disciplina",
                Atributo.REAL);

        Set<Atributo> atributos = new HashSet<>(2);
        atributos.add(nome);
        atributos.add(cha);

        final String NOME = "Aulas presenciais na graduação";
        final String DESCRICAO = "Aulas presenciais ministradas na graduação";

        String id = UUID.randomUUID().toString();
        return new Tipo(id, NOME, DESCRICAO, atributos);
    }

    @Test
    public void insereRecuperaRemoveResolucao() throws IOException {
        Resolucao resolucao = criaResolucaoComDuasRegras();

        // Verifica que não existe.
        assertNull(byId(resolucao.getId()));

        // Realiza a inserção (criação)
        persiste(resolucao);

        // Recupera (agora existe)
        Resolucao rr = byId(resolucao.getId());
        assertNotNull(rr);

        // Remove o que foi recuperado
        assertTrue(remove(resolucao.getId()));

        // Verifica que não existe mais.
        assertNull(resolucaoFromEntity(resolucao.getId()));
    }

    @Test
    public void recuperaResolucoes() throws IOException {
        Resolucao resolucao = criaResolucaoComDuasRegras();

        // Não pode conter resolução ainda não inserida.
        List<String> existentes = resolucoes();
        assertFalse(existentes.contains(resolucao.getId()));

        // Realiza a inserção (criação)
        persiste(resolucao);

        List<String> aposInsercao = resolucoes();
        assertTrue(aposInsercao.contains(resolucao.getId()));

        // Deve possuir uma instância a mais
        // (assume ausência de operação concorrente)
        assertEquals(existentes.size() + 1, aposInsercao.size());
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
    public Entity resolucaoToEntity(Resolucao resolucao) throws IOException {
        Key key = keyResolucao.newKey(resolucao.getId());
        Entity re = Entity.builder(key)
                .set("objeto", new Gson().toJson(resolucao)).build();

        return re;
    }

    public Entity tipoToEntity(Tipo tipo) throws IOException {
        Key key = keyTipo.newKey(tipo.getId());
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

    private List<Regra> fromString(String json) {
        Type tipo = new TypeToken<List<Regra>>(){}.getType();
        return (List<Regra>)new Gson().fromJson(json, tipo);
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
