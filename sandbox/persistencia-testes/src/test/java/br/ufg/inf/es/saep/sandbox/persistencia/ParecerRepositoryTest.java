package br.ufg.inf.es.saep.sandbox.persistencia;

import br.ufg.inf.es.saep.sandbox.dominio.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Testes básicos (funcionais) de implementação
 * do repositório de pareceres.
 */
public class ParecerRepositoryTest {

    private static final String REPOSITORIO = "br.ufg.inf.es.saep.sandbox.persistencia.ParecerRepositoryRam";
    private ParecerRepository repo;

    private List<String> radocsIds;
    private List<Pontuacao> pontuacoes;
    private List<Nota> notas;

    /**
     * Assume que a classe definida por {@link #REPOSITORIO} possui um
     * construtor default (sem argumentos).
     *
     * @throws ClassNotFoundException Classe não encontrada no classpath.
     * @throws IllegalAccessException Não há permissão de acesso à classe.
     * @throws InstantiationException Erro durante a criação da instância.
     */
    @Before
    public void setUpClass() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> classe = Class.forName(REPOSITORIO);
        repo = (ParecerRepository)classe.newInstance();

        radocsIds = new ArrayList<>(1);
        radocsIds.add("radocId");

        pontuacoes = new ArrayList<>(1);
        pontuacoes.add(new Pontuacao("x", new Valor(true)));

        notas = new ArrayList<>(0);
    }

    @Test
    public void naoHaComoRecuperarParecerInexistente() {

        assertNull(repo.parecerById(UUID.randomUUID().toString()));
    }

    @Test
    public void insereRecuperaParecer() {

        Parecer p = new Parecer("rid", radocsIds, pontuacoes, "f", notas);
        repo.persisteParecer(p);
        String id = p.getId();

        Parecer r = repo.parecerById(id);
        assertEquals(p, r);
    }

    @Test
    public void atualizaFundamentacaoDeParecer() {
        Parecer p = new Parecer("rid", radocsIds, pontuacoes, "f", notas);
        repo.persisteParecer(p);
        String id = p.getId();

        Parecer r = repo.parecerById(id);
        assertEquals("f", r.getFundamentacao());

        repo.atualizaFundamentacao(id, "nova fundamentação");
        r = repo.parecerById(id);
        assertEquals("nova fundamentação", r.getFundamentacao());
    }

    @Test
    public void removeParecer() {
        Parecer p = new Parecer("rid", radocsIds, pontuacoes, "f", notas);
        repo.persisteParecer(p);
        String id = p.getId();

        // Verifica existência
        assertEquals(id, repo.parecerById(id).getId());

        repo.removeParecer(id);
        assertNull(repo.parecerById(id));
    }

    @Test
    public void acrescentaNota() {
        Parecer p = new Parecer("rid", radocsIds, pontuacoes, "f", notas);
        repo.persisteParecer(p);
        String id = p.getId();

        // Verifica ausência de notas
        assertEquals(0, repo.parecerById(id).getNotas().size());

        Avaliavel original = new Pontuacao("o", new Valor(false));
        Avaliavel novo = new Pontuacao("o", new Valor(true));

        repo.adicionaNota(id, new Nota(original, novo, "?"));

        Parecer r = repo.parecerById(id);
        List<Nota> notas = r.getNotas();
        assertEquals(1, notas.size());
    }
}
