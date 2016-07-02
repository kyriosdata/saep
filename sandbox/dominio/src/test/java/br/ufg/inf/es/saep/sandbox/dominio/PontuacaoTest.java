package br.ufg.inf.es.saep.sandbox.dominio;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class PontuacaoTest {

    @Test
    public void montaRecuperaPontuacao() {
        Pontuacao p = new Pontuacao("p", new Valor(-21.4f));

        assertEquals("p", p.getAtributo());
        assertEquals(-21.4f, p.getValor().getFloat(), 0.0001d);
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void nomeNullGeraExcecao() {
        new Pontuacao(null, new Valor("o"));
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void valorNullGeraExcecao() {
        new Pontuacao("o", null);
    }

    @Test
    public void avaliavelRetornaValorDoAtributo() {
        Avaliavel a = new Pontuacao("a", new Valor(true));

        assertTrue(a.get("a").getBoolean());
    }

    @Test
    public void avaliavelRetornaNullParaAtributoInvalido() {
        Avaliavel a = new Pontuacao("a", new Valor(true));

        assertNull(a.get("outro atributo"));
    }
}

