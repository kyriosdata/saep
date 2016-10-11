package br.ufg.inf.es.saep.sandbox.dominio;

import br.ufg.inf.es.saep.sandbox.dominio.excecoes.CampoExigidoNaoFornecido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParecerTest {
    private List<String> radocs;
    private List<Pontuacao> pontuacoes;

    @BeforeEach
    public void setUp() {
        radocs = new ArrayList<>(1);
        radocs.add("radoc");

        pontuacoes = new ArrayList<>(1);
        pontuacoes.add(new Pontuacao("p", new Valor(23f)));
    }

    @Test
    public void situacoesExcepcionaisDeConstrucao() {

        assertThrows(CampoExigidoNaoFornecido.class, () -> new Parecer(null, "rid",  radocs, pontuacoes, null, null));
        assertThrows(CampoExigidoNaoFornecido.class, () -> new Parecer("", "rid",  radocs, pontuacoes, null, null));
        assertThrows(CampoExigidoNaoFornecido.class, () -> new Parecer("1", null,  radocs, pontuacoes, null, null));
        assertThrows(CampoExigidoNaoFornecido.class, () -> new Parecer("1", "",  radocs, pontuacoes, null, null));
        assertThrows(CampoExigidoNaoFornecido.class, () -> new Parecer("1", "r", null, pontuacoes, null, null));
        assertThrows(CampoExigidoNaoFornecido.class, () -> new Parecer("1", "r", new ArrayList<>(0), pontuacoes, null, null));
        assertThrows(CampoExigidoNaoFornecido.class, () -> new Parecer("1", "r", radocs, null, null, null));
        assertThrows(CampoExigidoNaoFornecido.class, () -> new Parecer("1", "r", radocs, new ArrayList<>(0), null, null));
    }

    @Test
    public void parecerRecuperadoConformeCriado() {
        Parecer p = new Parecer("1", "r", radocs, pontuacoes, null, null);

        assertEquals("1", p.getId());
        assertEquals("r", p.getResolucaoId());
        assertEquals(radocs, p.getRadocsIds());
        assertEquals(pontuacoes, p.getPontuacoes());
        assertNull(p.getFundamentacao());
        assertNull(p.getObservacoes());
    }

    @Test
    public void construtorGeraId() {
        Parecer p = new Parecer("r", radocs, pontuacoes, null, null);
        assertNotNull(p.getId());
    }

    @Test
    public void pareceresIguais() {
        Parecer p1 = new Parecer("1", "a", radocs, pontuacoes, "f", null);
        Parecer p2 = new Parecer("1", "b", radocs, pontuacoes, "o", null);

        assertEquals(p1, p1);
        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    public void pareceresDistintos() {
        Parecer p1 = new Parecer("1", "a", radocs, pontuacoes, "f", null);
        Parecer p2 = new Parecer("2", "a", radocs, pontuacoes, "f", null);

        assertNotEquals(p1, p2);
        assertFalse(p1.equals("banana"));
        assertFalse(p1.equals(null));
    }
}