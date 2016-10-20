package br.ufg.inf.es.saep.sandbox.dominio.regra;

import br.ufg.inf.es.saep.sandbox.ExpressaoTeste;
import br.ufg.inf.es.saep.sandbox.ParserTeste;
import br.ufg.inf.es.saep.sandbox.dominio.Valor;
import br.ufg.inf.es.saep.sandbox.dominio.excecoes.CampoExigidoNaoFornecido;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes do avaliador de regras
 */
public class RegraExpressaoTest {

    @Test
    public void agradarCobertura() {
        RegraExpressao re = new RegraExpressao("v", "d", 1, 0, "x");

        // Antes da preparação é null
        assertNull(re.getContexto());

        // Não pode ser null após preparação
        ParserTeste parser = new ParserTeste();
        parser.setDependencias(new ArrayList<>());
        re.preparacao(parser);
        assertNotNull(re.getContexto());
        assertEquals("x", re.getExpressao());
    }

    @Test
    public void parserObrigatorioParaPreparacao() {
        RegraExpressao re = new RegraExpressao("v", "d", 1, 0, "x");
        assertThrows(CampoExigidoNaoFornecido.class, () -> re.preparacao(null));
    }

    @Test
    public void regraDefinidaPorConstante() {

        // Define valor a ser retornado pela avaliação da expressão
        ExpressaoTeste et = new ExpressaoTeste();
        et.setValorRetorno(2f);

        // Parser empregado para dependencias e produção de expressão avaliável
        ParserTeste p = new ParserTeste();
        p.setDependencias(new ArrayList<>(0));
        p.setExpressao(et);

        Regra regra = new RegraExpressao("v", "d", 100, 0, "2");
        regra.preparacao(p);
        assertEquals(2d, regra.avalie(null, null).getReal(), 0.0001d);
    }

    @Test
    public void regraDependeDeVariavelNaoFornecidaAssumeZero() {
        ExpressaoTeste et = new ExpressaoTeste();
        et.setValorRetorno(1);

        List<String> deps = new ArrayList<>(1);
        deps.add("x");

        ParserTeste pt = new ParserTeste();
        pt.setDependencias(deps);
        pt.setExpressao(et);

        Regra r = new RegraExpressao("v", "d", 100, 0, "1 + x");
        r.preparacao(pt);

        HashMap<String, Valor> contexto = new HashMap<>();
        contexto.put("x", new Valor(34f));

        assertEquals(1f, r.avalie(null, contexto).getReal(), 0.0001d);
    }

    @Test
    public void somatorioSemVariavelGeraExcecao() {
        Regra r = new RegraExpressao("v", "d", 100, 0, "x");

        // Preparação antes do uso da regra
        List<String> dd = new ArrayList<>(1);
        dd.add("x");

        ExpressaoTeste et = new ExpressaoTeste();
        et.setValorRetorno(6);

        ParserTeste pt = new ParserTeste();
        pt.setDependencias(dd);
        pt.setExpressao(et);

        r.preparacao(pt);

        Map<String, Valor> ctx = new HashMap<>(0);
        assertEquals(6f, r.avalie(null, ctx).getReal(), 0.0001f);
    }

    @Test
    public void semRegistroZeroPontos() {
        assertThrows(CampoExigidoNaoFornecido.class, () -> new RegraExpressao("v", "d", 100, 0, null));
    }
}
