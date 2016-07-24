package br.ufg.inf.es.saep.sandbox.persistencia.ram;

import br.ufg.inf.es.saep.sandbox.dominio.Atributo;
import br.ufg.inf.es.saep.sandbox.dominio.Regra;
import br.ufg.inf.es.saep.sandbox.dominio.Resolucao;
import br.ufg.inf.es.saep.sandbox.dominio.Tipo;
import br.ufg.inf.es.saep.sandbox.persistencia.gds.GoogleDatastoreImpl;
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

    private static GoogleDatastoreImpl instancia;

    @BeforeClass
    public static void setUpClass() throws IOException {
        instancia = new GoogleDatastoreImpl();
    }

    @Test
    public void insereRecuperaConsultaRemoveTipo() {
        Tipo t = criaTipoComDoisAtributos();

        // Não encontra tipo não inserido
        assertNull(instancia.tipoPeloCodigo(t.getId()));

        // Persiste o tipo
        instancia.persisteTipo(t);

        // Encontra o persistido, identico ao inserido
        Tipo tipoRecuperado = instancia.tipoPeloCodigo(t.getId());
        assertEquals(t, tipoRecuperado);

        // Remove o tipo persistido
        instancia.removeTipo(tipoRecuperado.getId());

        // Não encontra tipo removido
        assertNull(instancia.tipoPeloCodigo(t.getId()));
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
        assertNull(instancia.byId(resolucao.getId()));

        // Realiza a inserção (criação)
        instancia.persiste(resolucao);

        // Recupera (agora existe)
        Resolucao rr = instancia.byId(resolucao.getId());
        assertNotNull(rr);

        // Remove o que foi recuperado
        assertTrue(instancia.remove(resolucao.getId()));

        // Verifica que não existe mais.
        assertNull(instancia.byId(resolucao.getId()));
    }

    @Test
    public void recuperaResolucoes() throws IOException {
        Resolucao resolucao = criaResolucaoComDuasRegras();

        // Não pode conter resolução ainda não inserida.
        List<String> existentes = instancia.resolucoes();
        assertFalse(existentes.contains(resolucao.getId()));

        // Realiza a inserção (criação)
        instancia.persiste(resolucao);

        List<String> aposInsercao = instancia.resolucoes();
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

        String id = UUID.randomUUID().toString();
        return new Resolucao(id, "resolucao", "descricao", new Date(), regras);
    }
}
