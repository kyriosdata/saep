package br.ufg.inf.es.saep.sandbox.dominio;

import br.ufg.inf.es.saep.sandbox.dominio.excecoes.CampoExigidoNaoFornecido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RelatorioTest {

    private List<Relato> relatos;

    @BeforeEach
    public void setUp() {
        Map<String, Valor> valores = new HashMap<>(1);
        valores.put("ano", new Valor(2016));

        relatos = new ArrayList<>(3);

        relatos.add(new Relato("a", valores));
        relatos.add(new Relato("b", valores));
        relatos.add(new Relato("a", valores));
    }

    @Test
    public void situacoesExcepcionais() {

        assertThrows(CampoExigidoNaoFornecido.class, () -> new Relatorio(null, 0, new ArrayList<>()));
        assertThrows(CampoExigidoNaoFornecido.class, () -> new Relatorio("", 0, new ArrayList<>()));
        assertThrows(CampoExigidoNaoFornecido.class, () -> new Relatorio("id", 0, null));
    }

    @Test
    public void semRelatosBuscaPorTipoRetornaZero() {
        Relatorio r = new Relatorio("r", 0, new ArrayList<>());
        assertEquals(0, r.relatosPorTipo("qualquer coisa").size());
    }

    @Test
    public void relatosPorTipo() {
        Relatorio r = new Relatorio("id", 0, relatos);

        assertEquals(0, r.relatosPorTipo("x").size());
        assertEquals(0, r.relatosPorTipo(null).size());
        assertEquals(2, r.relatosPorTipo("a").size());
        assertEquals(1, r.relatosPorTipo("b").size());
    }

    @Test
    public void recuperaCorretamente() {
        Relatorio r = new Relatorio("x", 1234, new ArrayList<>(0));
        assertEquals("x", r.getId());
        assertEquals(1234, r.getAnoBase());
        assertEquals(0, r.getRelatos().size());
    }

    @Test
    public void igualdade() {
        Relatorio r1 = new Relatorio("x", 0, relatos);

        assertNotEquals(r1, null);
        assertNotEquals(r1, "não pode ser igual");
        assertNotEquals(r1, new Relatorio("y", 0, relatos));

        assertEquals(r1, r1);
        assertEquals(r1, new Relatorio("x", 1, relatos));
    }

    @Test
    public void hashCodeContrato() {
        Relatorio r1 = new Relatorio("x", 0, relatos);
        Relatorio r2 = new Relatorio("x", 0, relatos);

        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
    }
}

