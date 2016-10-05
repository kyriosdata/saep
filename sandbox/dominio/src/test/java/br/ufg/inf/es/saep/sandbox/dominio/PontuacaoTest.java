package br.ufg.inf.es.saep.sandbox.dominio;

import br.ufg.inf.es.saep.sandbox.dominio.excecoes.CampoExigidoNaoFornecido;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PontuacaoTest {

    @Test
    public void montaRecuperaPontuacao() {
        Pontuacao p = new Pontuacao("p", new Valor(-21.4f));

        assertEquals("p", p.getAtributo());
        assertEquals(-21.4f, p.getValor().getReal(), 0.0001d);
    }

    @Test
    public void nomeNullGeraExcecao() {

        assertThrows(CampoExigidoNaoFornecido.class, () -> new Pontuacao(null, new Valor("o")));
    }

    @Test
    public void valorNullGeraExcecao() {

        assertThrows(CampoExigidoNaoFornecido.class, () -> new Pontuacao("o", null));
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

