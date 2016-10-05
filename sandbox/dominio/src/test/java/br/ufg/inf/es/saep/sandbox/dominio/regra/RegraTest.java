package br.ufg.inf.es.saep.sandbox.dominio.regra;

import br.ufg.inf.es.saep.sandbox.dominio.Avaliavel;
import br.ufg.inf.es.saep.sandbox.dominio.Valor;
import br.ufg.inf.es.saep.sandbox.dominio.excecoes.CampoExigidoNaoFornecido;
import org.junit.jupiter.api.Test;


import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RegraTest {

    @Test
    public void situacoesExcepcionaisDeConstrucao() {

        assertThrows(IllegalArgumentException.class, () -> new RegraParaTeste("r", "d", 1f, 2f));
        assertThrows(CampoExigidoNaoFornecido.class, () -> new RegraParaTeste(null, "d", 10f, 2f));
        assertThrows(CampoExigidoNaoFornecido.class, () -> new RegraParaTeste("", "d", 10f, 2f));
        assertThrows(CampoExigidoNaoFornecido.class, () -> new RegraParaTeste("v", null, 10f, 2f));
        assertThrows(CampoExigidoNaoFornecido.class, () -> new RegraParaTeste("v", "", 10f, 2f));
    }

    @Test
    public void preparacaoApenasParaCobertura() {
        RegraParaTeste r = new RegraParaTeste("v", "d", 1, 0);
        r.preparacao(null);
    }

    @Test
    public void ajustaLimitesCorretamente() {
        RegraParaTeste r = new RegraParaTeste("v", "d", 1, 0);
        assertEquals(1f, r.ajustaLimites(1.001f), 0.0001f);
        assertEquals(0f, r.ajustaLimites(-0.0001f), 0.0001f);
        assertEquals(0.5f, r.ajustaLimites(0.5f), 0.0001f);
    }

    @Test
    public void apenasParaAgradarCobertura() {
        RegraParaTeste r = new RegraParaTeste("v", "d", 2.3f, 1f);
        assertEquals("v", r.getVariavel());
        assertEquals("d", r.getDescricao());
        assertEquals(2.3f, r.getValorMaximo(), 0.0001f);
        assertEquals(1f, r.getValorMinimo(), 0.0001f);
    }

    @Test
    public void igualdadeEntreRegras() {
        RegraParaTeste r1 = new RegraParaTeste("v", "d", 1, 0);
        RegraParaTeste r2 = new RegraParaTeste("v", "x", 6, 5);
        RegraParaTeste r3 = new RegraParaTeste("u", "d", 1, 0);

        // Casos clássicos
        assertEquals(r1, r1);
        assertNotEquals(r1, "impossivel igualdade");

        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());

        assertNotEquals(r1, r3);
        assertNotEquals(r2, r3);
    }
}

class RegraParaTeste extends Regra {

    public RegraParaTeste(String variavel, String descricao, float valorMaximo, float valorMinimo) {
        super(variavel, descricao, valorMaximo, valorMinimo);
    }

    @Override
    public Valor avalie(List<Avaliavel> avaliaveis, Map<String, Valor> contexto) {
        return null;
    }
}

