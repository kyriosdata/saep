package br.ufg.inf.es.saep.sandbox.persistencia.ram;

import br.ufg.inf.es.saep.sandbox.dominio.Atributo;
import br.ufg.inf.es.saep.sandbox.dominio.Regra;
import br.ufg.inf.es.saep.sandbox.dominio.Resolucao;
import br.ufg.inf.es.saep.sandbox.dominio.Tipo;
import br.ufg.inf.es.saep.sandbox.persistencia.gds.GoogleDatastoreFactory;
import br.ufg.inf.es.saep.sandbox.persistencia.gds.ResolucaoRepositoryGoogleDatastore;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

import static junit.framework.TestCase.*;
import static org.junit.Assert.assertTrue;

/**
 * Experimentos preliminares com Google Datastore e Jackson.
 * Jackson para serializar objeto em String. Jackson também é
 * empregado para realizar o processo inverso.
 *
 */
public class GoogleDatastoreTest {

    private static ResolucaoRepositoryGoogleDatastore repo;

    @BeforeClass
    public static void setUpClass() throws IOException {
        repo = new ResolucaoRepositoryGoogleDatastore();
        repo.setDatastore(GoogleDatastoreFactory.getInstance());
    }

    @Test
    public void insereRecuperaConsultaRemoveTipo() {
        Tipo t = criaTipoComDoisAtributos();

        // Não encontra tipo não inserido
        assertNull(repo.tipoPeloCodigo(t.getId()));

        // Persiste o tipo
        repo.persisteTipo(t);

        // Encontra o persistido, identico ao inserido
        Tipo tipoRecuperado = repo.tipoPeloCodigo(t.getId());
        assertEquals(t, tipoRecuperado);

        // Remove o tipo persistido
        repo.removeTipo(tipoRecuperado.getId());

        // Não encontra tipo removido
        assertNull(repo.tipoPeloCodigo(t.getId()));
    }

    @Test
    public void consultaTipos() {
        Tipo t1 = criaTipoComDoisAtributos("atencao");
        Tipo t2 = criaTipoComDoisAtributos("atibaia");
        Tipo t3 = criaTipoComDoisAtributos("atitude");

        repo.persisteTipo(t1);
        repo.persisteTipo(t2);
        repo.persisteTipo(t3);

        List<Tipo> busca1 = repo.tiposPeloNome("at");
        assertEquals(3, busca1.size());

        List<Tipo> busca2 = repo.tiposPeloNome("aia");
        assertEquals(1, busca2.size());

        List<Tipo> busca3 = repo.tiposPeloNome("taia");
        assertEquals(0, busca3.size());
    }

    private Tipo criaTipoComDoisAtributos(String id) {
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

        return new Tipo(id, NOME, DESCRICAO, atributos);
    }

    private Tipo criaTipoComDoisAtributos() {
        return criaTipoComDoisAtributos(UUID.randomUUID().toString());
    }

    @Test
    public void insereRecuperaRemoveResolucao() throws IOException {
        Resolucao resolucao = criaResolucaoComDuasRegras();

        // Verifica que não existe.
        assertNull(repo.byId(resolucao.getId()));

        // Realiza a inserção (criação)
        repo.persiste(resolucao);

        // Recupera (agora existe)
        Resolucao rr = repo.byId(resolucao.getId());
        assertNotNull(rr);

        // Remove o que foi recuperado
        assertTrue(repo.remove(resolucao.getId()));

        // Verifica que não existe mais.
        assertNull(repo.byId(resolucao.getId()));
    }

    @Test
    public void recuperaResolucoes() throws IOException {
        Resolucao resolucao = criaResolucaoComDuasRegras();

        // Não pode conter resolução ainda não inserida.
        List<String> existentes = repo.resolucoes();
        assertFalse(existentes.contains(resolucao.getId()));

        // Realiza a inserção (criação)
        repo.persiste(resolucao);

        List<String> aposInsercao = repo.resolucoes();
        assertTrue(aposInsercao.contains(resolucao.getId()));

        // Deve possuir uma instância a mais
        // (assume ausência de operação concorrente)
        assertEquals(existentes.size() + 1, aposInsercao.size());
    }

    private Resolucao criaResolucaoComDuasRegras() {
        List<String> dd = new ArrayList<>(1);
        dd.add("e");

        Regra r1 = new Regra("v1", Regra.EXPRESSAO, "dr1", 1, 0, "e", null, null, "t", 0, dd);
        Regra r2 = new Regra("v2", Regra.PONTOS, "dr2", 1, 0, null, null, null, "t", 0, null);

        List<Regra> regras = new ArrayList<>(2);
        regras.add(r1);
        regras.add(r2);

        return new Resolucao(UUID.randomUUID().toString(), "resolucao", "descricao", new Date(), regras);
    }
}