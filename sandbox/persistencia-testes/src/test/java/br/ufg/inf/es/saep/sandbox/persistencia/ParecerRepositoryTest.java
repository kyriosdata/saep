package br.ufg.inf.es.saep.sandbox.persistencia;

import br.ufg.inf.es.saep.sandbox.dominio.Parecer;
import br.ufg.inf.es.saep.sandbox.dominio.ParecerRepository;
import br.ufg.inf.es.saep.sandbox.dominio.Regra;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertNull;

/**
 * Testes do avaliador de regras
 */
public class ParecerRepositoryTest {

    private static final String REPOSITORIO = "br.ufg.inf.es.saep.sandbox.persistencia.ParecerRepositoryRam";
    private ParecerRepository repo;
    private List<String> dependencias;
    private Regra regra;
    private List<Regra> regras;

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

        // Variáveis utilitárias para simplificação da montagem de testes.
        dependencias = new ArrayList<>();
        regra = new Regra("v", Regra.PONTOS, "pontos", 10, 0, null, null, null, "t", 1, dependencias);
        regras = new ArrayList<>();
        regras.add(regra);
    }

    @Test
    public void naoHaComoRecuperarParecerInexistente() {

        assertNull(repo.byId(UUID.randomUUID().toString()));
    }

    @Test
    public void insereRecuperaParecer() {
        Parecer p = new Parecer();
    }
}
