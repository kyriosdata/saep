package br.ufg.inf.es.saep.sandbox.dominio;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class RelatoTest {

    Map<String, Valor> valores;
    Map<String, Valor> vazio;

    @Before
    public void defineValores() {
        valores = new HashMap<>(1);
        valores.put("nome", new Valor(99f));

        vazio = new HashMap<>(0);
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void tipoNaoPodeSerNull() {
        new Relato(null, valores);
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void tipoNaoPodeSerVazio() {
        new Relato("", valores);
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void valoresNaoPodeSerNull() {
        new Relato("tipo", null);
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void valoresNaoPodeSerVazio() {
        new Relato("tipo", vazio);
    }

    @Test
    public void criaRecupera() {
        Relato r = new Relato("a", valores);

        assertEquals("a", r.getTipo());
        assertEquals(99f, r.get("nome").getFloat(), 0.0001f);
    }

    @Test
    public void relatosDistintosPorTipo() {
        Relato r1 = new Relato("t1", valores);
        Relato r2 = new Relato("t2", valores);

        assertNotEquals(r1, r2);
    }

    @Test
    public void relatoDistintoDeNull() {
        Relato r1 = new Relato("t1", valores);
        assertNotEquals(r1, null);
    }

    @Test
    public void relatoIgualASi() {
        Relato r1 = new Relato("t1", valores);
        assertEquals(r1, r1);
    }

    @Test
    public void relatoDistintoDeOutroTipoDeObjeto() {
        Relato r1 = new Relato("t1", valores);
        assertNotEquals(r1, "errado");
    }

    @Test
    public void relatosDistintosPorValores() {
        Relato r1 = new Relato("t", valores);

        Map<String, Valor> outros = new HashMap<>(1);
        outros.put("nome", new Valor(false));

        Relato r2 = new Relato("t", outros);

        assertNotEquals(r1, r2);
    }

    @Test
    public void relatosDistintosPorQuantidadeDeValores() {
        Relato r1 = new Relato("t", valores);

        Map<String, Valor> outros = new HashMap<>(2);
        outros.put("nome", new Valor(false));
        outros.put("casa", new Valor(true));

        Relato r2 = new Relato("t", outros);

        assertNotEquals(r1, r2);
    }

    @Test
    public void relatosIguaisHashCodeIguais() {
        Relato r1 = new Relato("h", valores);
        Relato r2 = new Relato("h", valores);

        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
    }
}

