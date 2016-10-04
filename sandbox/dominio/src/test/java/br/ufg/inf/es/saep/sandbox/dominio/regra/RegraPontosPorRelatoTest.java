package br.ufg.inf.es.saep.sandbox.dominio.regra;

import br.ufg.inf.es.saep.sandbox.ExpressaoTeste;
import br.ufg.inf.es.saep.sandbox.ParserTeste;
import br.ufg.inf.es.saep.sandbox.dominio.excecoes.CampoExigidoNaoFornecido;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * Testes do avaliador de regras
 */
public class RegraPontosPorRelatoTest {

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void tipoRelatoNaoPodeSerNull() {
        new RegraPontosPorRelato("v", "d", 1, 0, null, 1);
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void tipoRelatoNaoPodeSerVazio() {
        new RegraPontosPorRelato("v", "d", 1, 0, "", 1);
    }

    @Test
    public void agradaCobertura() {
        RegraPontosPorRelato rp = new RegraPontosPorRelato("v", "d", 1, 0, "a", 1);
        assertEquals("a", rp.getTipoRelato());
        assertEquals(1f, rp.getPontosPorItem(), 0.0001f);
    }

    @Test
    public void nenhumRelatoTotalDePontosZeroOuMinimo() {
        RegraPontosPorRelato rp = new RegraPontosPorRelato("v", "d", 7, 6, "a", 8);

        ParserTeste parser = new ParserTeste();
        parser.setDependencias(new ArrayList<>(0));
        rp.preparacao(parser);

        assertEquals(6, rp.avalie(new ArrayList<>(0), new HashMap<>(0)).getReal(), 0.0001f);
    }

}
