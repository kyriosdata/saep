package br.ufg.inf.es.saep.sandbox.persistencia.gds;

import br.ufg.inf.es.saep.sandbox.dominio.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;

/**
 * Experimentos preliminares com Google Datastore.
 *
 */
public class ParecerRepositoryGoogleDatastoreTest {

    private static ParecerRepositoryGoogleDatastore repo;

    @BeforeClass
    public static void setUpClass() throws IOException {
        repo = new ParecerRepositoryGoogleDatastore();
        repo.setDatastore(GoogleDatastoreFactory.getInstance());
    }

    @Test
    public void persisteRecuperaRemoveRadoc() {
        String id = UUID.randomUUID().toString();
        Radoc r = criaRadoc(id);

        // Radoc não persistido não pode ser recuperado
        assertNull(repo.radocById(id));

        // Persiste radoc
        assertNotNull(repo.persisteRadoc(r));

        Radoc recuperado = repo.radocById(id);
        assertEquals(r, recuperado);
    }

    @Test
    public void persisteRecuperaRemove() {
        String id = UUID.randomUUID().toString();
        Parecer p = criaParecer(id);

        // Parecer não está disponível (não persistido)
        assertNull(repo.parecerById(p.getId()));

        // Persiste parecer recém-criado
        repo.persisteParecer(p);

        Parecer recuperado = repo.parecerById(id);
        assertEquals(p, recuperado);
    }

    private Parecer criaParecer(String id) {
        Radoc r = criaRadoc("id");

        List<String> radocs = new ArrayList(1);
        radocs.add(r.getId());

        List<Pontuacao> pontuacoes = new ArrayList<>(1);
        pontuacoes.add(new Pontuacao("p", new Valor(23f)));

        return new Parecer(id, "a", radocs, pontuacoes, "f", null);
    }

    private Radoc criaRadoc(String id) {
        Map<String, Valor> valores = new HashMap<>(1);
        valores.put("ano", new Valor(2016));

        List<Relato> relatos = new ArrayList<>(3);

        relatos.add(new Relato("a", valores));
        relatos.add(new Relato("b", valores));
        relatos.add(new Relato("c", valores));

        return new Radoc(id, 1999, relatos);
    }
}