package br.ufg.inf.es.saep.sandbox.dominio;

import org.junit.Test;

import java.util.HashMap;

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

    @Test(expected = AvaliaveisDeTiposDistintos.class)
    public void tentativaDeCriarNotaComAvaliaveisDeTiposDistintos() {
        Avaliavel o = new Pontuacao("o", new Valor("o"));
        HashMap<String, Valor> valores = new HashMap<>(1);
        valores.put("v", new Valor("v"));

        Avaliavel d = new Relato("d", valores);

        new Nota(o, d, "tentativa deve falhar");
    }
}

