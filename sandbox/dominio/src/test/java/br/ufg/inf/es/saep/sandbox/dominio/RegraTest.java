package br.ufg.inf.es.saep.sandbox.dominio;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

public class RegraTest {

    private List<String> dd;

    @Before
    public void setUp() {
        dd = new ArrayList<>(1);
        dd.add("a");
    }

    @Test(expected = TipoDeRegraInvalido.class)
    public void tipoInvalidoGeraExcecao() {
        new Regra("v", -1, "d", 0, 0, "1", null, null, "t", 0, dd);
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void descricaoNullGeraExcecao() {
        new Regra("v", 1, null, 0, 0, "1", null, null, "t", 0, dd);
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void descricaoVaziaGeraExcecao() {
        new Regra("v", 1, "", 0, 0, "1", null, null, "t", 0, dd);
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void variavelNullGeraExcecao() {
        new Regra(null, 1, "d", 0, 0, "1", null, null, "t", 0, dd);
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void variavelVaziaGeraExcecao() {
        new Regra("", 1, "d", 0, 0, "1", null, null, "t", 0, dd);
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void tipoObrigatorioSeRegraPorPontos() {
        new Regra("v", Regra.PONTOS, "d", 0, 0, "1", null, null, null, 0, dd);
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void tipoNaoPodeSerVazioSeRegraPorPontos() {
        new Regra("v", Regra.PONTOS, "d", 0, 0, "1", null, null, "", 0, dd);
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void expressaoNullGeraExcecao() {
        new Regra("v", 1, "d", 0, 0, null, null, null, "t", 0, dd);
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void expressaoVaziaGeraExcecao() {
        new Regra("v", 1, "d", 0, 0, "", null, null, "t", 0, dd);
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void dependeDeNullGeraExcecao() {
        new Regra("v", 1, "d", 0, 0, "1", null, null, "t", 0, null);
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void seCondicionalEntaoNullGeraExcecao() {
        new Regra("v", Regra.CONDICIONAL, "d", 0, 0, "1", null, null, "t", 0, dd);
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void seCondicionalEntaoVazioGeraExcecao() {
        new Regra("v", Regra.CONDICIONAL, "d", 0, 0, "1", "", null, "t", 0, dd);
    }

    @Test
    public void defineRecuperaCorretamente() {
        int t = Regra.CONDICIONAL;
        Regra r;

        r = new Regra("v", t, "d", -1, 4, "a", "b", "c", null, 0, dd);

        assertEquals(t, r.getTipo());
        assertEquals("d", r.getDescricao());
        assertEquals(-1, r.getValorMaximo(), 0.0001d);
        assertEquals(4, r.getValorMinimo(), 0.0001d);
        assertEquals("v", r.getVariavel());
        assertEquals("a", r.getExpressao());
        assertEquals("b", r.getEntao());
        assertEquals("c", r.getSenao());
        assertNull(r.getTipoRelato());
        assertEquals(0, r.getPontosPorItem(), 0.0001d);
        assertEquals("a", dd.get(0));
    }

    @Test
    public void regrasSaoIguaisSeVariaveisIguais() {
        Regra r1 = new Regra("variavel (key)", Regra.EXPRESSAO, "d", -1, 4, "a", "b", "c", "r", 0, dd);
        Regra r2 = new Regra("variavel (key)", Regra.PONTOS, "d", -1, 4, "a", "b", "c", "r", 0, dd);

        assertEquals(r1, r1);
        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
        assertNotEquals(r1, "nao pode ser igual");
    }
}
