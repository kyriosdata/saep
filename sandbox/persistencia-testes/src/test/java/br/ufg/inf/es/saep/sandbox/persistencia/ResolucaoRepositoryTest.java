package br.ufg.inf.es.saep.sandbox.persistencia;

import br.ufg.inf.es.saep.sandbox.dominio.Regra;
import br.ufg.inf.es.saep.sandbox.dominio.Resolucao;
import br.ufg.inf.es.saep.sandbox.dominio.ResolucaoRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Testes do avaliador de regras
 */
public class ResolucaoRepositoryTest {

    private static final String REPOSITORIO = "br.ufg.inf.es.saep.sandbox.persistencia.ResolucaoRepositoryRam";
    private ResolucaoRepository repo;

    @Before
    public void setUpClass() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> classe = Class.forName(REPOSITORIO);
        repo = (ResolucaoRepository)classe.newInstance();
    }

    @Test
    public void naoHaComoRecuperarResolucaoInexistente() {
        assertNull(repo.byId(UUID.randomUUID().toString()));
    }

    @Test
    public void recuperaResolucaoInserida() {
        List<String> dd = new ArrayList<>();
        Regra regra = new Regra(Regra.PONTOS, "pontos", 10, 0, "p", null, null, null, "t", 1, dd);
        List<Regra> regras = new ArrayList<>();
        regras.add(regra);
        Resolucao r = new Resolucao("r", "nome", "resolução r", new Date(), regras);

        assertEquals("r", repo.persiste(r));

        Resolucao recuperada = repo.byId("r");
        assertEquals(r, recuperada);
    }

}
