package br.ufg.inf.es.saep.sandbox.dominio;

import org.junit.Test;
import static org.junit.Assert.*;

public class NotaTest {

    @Test
    public void montaRecuperaCorretamenteUmaNota() {
        Avaliavel o = new Pontuacao("o", new Valor("o"));
        Avaliavel s = new Pontuacao("s", new Valor("o"));

        assertNotEquals(o, s);

        Nota n = new Nota(o, s, "simples erro");
        assertEquals(o, n.getItemOriginal());
        assertEquals(s, n.getItemNovo());
        assertEquals("simples erro", n.getJustificativa());
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void origemNullGeraExcecao() {
        Avaliavel o = new Pontuacao("o", new Valor("o"));
        new Nota(null, o, "simples erro");
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void destinoNullGeraExcecao() {
        Avaliavel o = new Pontuacao("o", new Valor("o"));
        new Nota(o, null, "simples erro");
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void justificativaNullGeraExcecao() {
        Avaliavel o = new Pontuacao("o", new Valor("o"));
        new Nota(o, o, null);
    }
}

