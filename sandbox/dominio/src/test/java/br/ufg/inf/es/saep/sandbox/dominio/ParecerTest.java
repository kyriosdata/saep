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

        assertThrows(CampoExigidoNaoFornecido.class, () -> new Contagem(null, "rid",  radocs, pontuacoes, null, null));
        assertThrows(CampoExigidoNaoFornecido.class, () -> new Contagem("", "rid",  radocs, pontuacoes, null, null));
        assertThrows(CampoExigidoNaoFornecido.class, () -> new Contagem("1", null,  radocs, pontuacoes, null, null));
        assertThrows(CampoExigidoNaoFornecido.class, () -> new Contagem("1", "",  radocs, pontuacoes, null, null));
        assertThrows(CampoExigidoNaoFornecido.class, () -> new Contagem("1", "r", null, pontuacoes, null, null));
        assertThrows(CampoExigidoNaoFornecido.class, () -> new Contagem("1", "r", new ArrayList<>(0), pontuacoes, null, null));
        assertThrows(CampoExigidoNaoFornecido.class, () -> new Contagem("1", "r", radocs, null, null, null));
        assertThrows(CampoExigidoNaoFornecido.class, () -> new Contagem("1", "r", radocs, new ArrayList<>(0), null, null));
    }

    @Test
    public void parecerRecuperadoConformeCriado() {
        Contagem p = new Contagem("1", "r", radocs, pontuacoes, null, null);

        assertEquals("1", p.getId());
        assertEquals("r", p.getResolucaoId());
        assertEquals(radocs, p.getRadocsIds());
        assertEquals(pontuacoes, p.getPontuacoes());
        assertNull(p.getFundamentacao());
        assertNull(p.getObservacoes());
    }

    @Test
    public void construtorGeraId() {
        Contagem p = new Contagem("r", radocs, pontuacoes, null, null);
        assertNotNull(p.getId());
    }

    @Test
    public void pareceresIguais() {
        Contagem p1 = new Contagem("1", "a", radocs, pontuacoes, "f", null);
        Contagem p2 = new Contagem("1", "b", radocs, pontuacoes, "o", null);

        assertEquals(p1, p1);
        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    public void pareceresDistintos() {
        Contagem p1 = new Contagem("1", "a", radocs, pontuacoes, "f", null);
        Contagem p2 = new Contagem("2", "a", radocs, pontuacoes, "f", null);

        assertNotEquals(p1, p2);
        assertFalse(p1.equals("banana"));
        assertFalse(p1.equals(null));
    }
}