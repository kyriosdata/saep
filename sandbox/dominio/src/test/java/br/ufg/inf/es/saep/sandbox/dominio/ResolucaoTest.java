package br.ufg.inf.es.saep.sandbox.dominio;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ResolucaoTest {

    private static List<Regra> regras;

    @BeforeClass
    public static void setUp() {
        Regra r = new Regra("v", 1, "d", 1, 0, "a", null, null, null, 1, new ArrayList<>());
        regras = new ArrayList<>();
        regras.add(r);
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void resolucaoIdNullGeraExcecao() {

        new Resolucao(null, "r", "d", new Date(), regras);
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void resolucaoIdVazioGeraExcecao() {

        new Resolucao("", "r", "d", new Date(), regras);
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void descricaoNullGeraExcecao() {

        new Resolucao("id", "d", null, new Date(), regras);
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void descricaoVaziaGeraExcecao() {

        new Resolucao("id", "d", "", new Date(), regras);
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void dataNullGeraExcecao() {

        new Resolucao("id", "d", "d", null, regras);
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void regrasNullGeraExcecao() {

        new Resolucao("id", "d", "d", new Date(), null);
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void regrasVaziaGeraExcecao() {

        List<Regra> vazias = new ArrayList<>(0);
        new Resolucao("id", "d", "d", new Date(), vazias);
    }

    @Test
    public void regraValidaNaoGeraExcecao() {

        Date dataAprovacao = new Date();
        Resolucao r = new Resolucao("id", "n", "d", dataAprovacao, regras);

        assertEquals("id", r.getId());
        assertEquals("n", r.getNome());
        assertEquals("d", r.getDescricao());
        assertEquals(dataAprovacao, r.getDataAprovacao());
        assertEquals(regras, r.getRegras());
    }

    @Test
    public void desigualdade() {
        Resolucao r = new Resolucao("i", "n", "d", new Date(), regras);

        assertNotEquals(r, null);
        assertNotEquals(r, "casa");
    }

    @Test
    public void resolucoesIguaisSeIdsIguais() {
        Resolucao r1 = new Resolucao("i", "x", "d", new Date(), regras);
        Resolucao r2 = new Resolucao("i", "y", "d", new Date(), regras);
        Resolucao r3 = new Resolucao("a", "y", "d", new Date(), regras);

        assertEquals(r1, r1);
        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());

        assertNotEquals(r1, r3);
    }
}
