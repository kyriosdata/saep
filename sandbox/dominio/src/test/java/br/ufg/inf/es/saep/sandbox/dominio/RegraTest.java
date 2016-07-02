package br.ufg.inf.es.saep.sandbox.dominio;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
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
        new Regra(-1, "d", 0, 0, "v", "1", null, null, "t", 0, dd);
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void descricaoNullGeraExcecao() {
        new Regra(1, null, 0, 0, "v", "1", null, null, "t", 0, dd);
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void descricaoVaziaGeraExcecao() {
        new Regra(1, "", 0, 0, "v", "1", null, null, "t", 0, dd);
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void variavelNullGeraExcecao() {
        new Regra(1, "d", 0, 0, null, "1", null, null, "t", 0, dd);
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void variavelVaziaGeraExcecao() {
        new Regra(1, "d", 0, 0, "", "1", null, null, "t", 0, dd);
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void tipoObrigatorioSeRegraPorPontos() {
        new Regra(Regra.PONTOS, "d", 0, 0, "v", "1", null, null, null, 0, dd);
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void tipoNaoPodeSerVazioSeRegraPorPontos() {
        new Regra(Regra.PONTOS, "d", 0, 0, "v", "1", null, null, "", 0, dd);
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void expressaoNullGeraExcecao() {
        new Regra(1, "d", 0, 0, "v", null, null, null, "t", 0, dd);
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void expressaoVaziaGeraExcecao() {
        new Regra(1, "d", 0, 0, "v", "", null, null, "t", 0, dd);
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void dependeDeNullGeraExcecao() {
        new Regra(1, "d", 0, 0, "v", "1", null, null, "t", 0, null);
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void seCondicionalEntaoNullGeraExcecao() {
        new Regra(Regra.CONDICIONAL, "d", 0, 0, "v", "1", null, null, "t", 0, dd);
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void seCondicionalEntaoVazioGeraExcecao() {
        new Regra(Regra.CONDICIONAL, "d", 0, 0, "v", "1", "", null, "t", 0, dd);
    }

    @Test
    public void defineRecuperaCorretamente() {
        int t = Regra.CONDICIONAL;
        Regra r;

        r = new Regra(t, "d", -1, 4, "v", "a", "b", "c", null, 0, dd);

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
}
