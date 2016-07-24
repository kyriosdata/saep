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

        // Após removido não pode ser recuperado
        repo.removeRadoc(id);
        assertNull(repo.radocById(id));
    }

    @Test
    public void persisteRecuperaRemoveParecer() {
        String id = UUID.randomUUID().toString();
        Parecer p = criaParecer(id);

        // Parecer não está disponível (não persistido)
        assertNull(repo.parecerById(p.getId()));

        // Tenta persistir parecer recém-criado
        // (sem a persistência dos RADOCs nada é persistido)
        repo.persisteParecer(p);
        assertNull(repo.parecerById(p.getId()));

        // Primeiro deve persistir os RADOCs
        // (abaixo são arbitrariamente criados com Ids esperados)
        for (String rId : p.getRadocsIds()) {
            Radoc r = criaRadoc(rId);
            repo.persisteRadoc(r);
        }

        // Agora deve persistir (radocs existem)
        repo.persisteParecer(p);
        Parecer recuperado = repo.parecerById(id);
        assertEquals(p, recuperado);

        // Não remove os radocs
        repo.removeParecer(p.getId());
        assertNull(repo.parecerById(p.getId()));

        // Remova os RADOCs criados.
        for (String rId : p.getRadocsIds()) {
            repo.removeRadoc(rId);
        }
    }

    private Parecer criaParecer(String id) {
        String guid = UUID.randomUUID().toString();
        Radoc r = criaRadoc(guid);

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