package br.ufg.inf.es.saep.sandbox.infraestrutura;

import br.ufg.inf.es.saep.sandbox.ExpressaoTeste;
import br.ufg.inf.es.saep.sandbox.ParserCondicaoTeste;
import br.ufg.inf.es.saep.sandbox.ParserTeste;
import br.ufg.inf.es.saep.sandbox.dominio.Avaliavel;
import br.ufg.inf.es.saep.sandbox.dominio.Relato;
import br.ufg.inf.es.saep.sandbox.dominio.Valor;
import br.ufg.inf.es.saep.sandbox.dominio.excecoes.CampoExigidoNaoFornecido;
import br.ufg.inf.es.saep.sandbox.dominio.regra.Regra;
import br.ufg.inf.es.saep.sandbox.dominio.regra.RegraCondicional;
import br.ufg.inf.es.saep.sandbox.dominio.regra.RegraExpressao;
import br.ufg.inf.es.saep.sandbox.dominio.regra.RegraSomatorio;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Testes do avaliador de regras
 */
public class AvaliaRegraServiceEvalExTest {
    private AvaliaRegraServiceEvalEx avaliador;

    @Before
    public void setUp() {
        avaliador = new AvaliaRegraServiceEvalEx();
    }

    @Test(expected = NullPointerException.class)
    public void naoAdmiteRegraNull() {
        avaliador.avalia(null, new HashMap<>(0), null);
    }

    @Test
    public void regraDefinidaPorConstante() {
        ArrayList<String> dd = new ArrayList<>(0);

        // Define valor a ser retornado pela avaliação da expressão
        ExpressaoTeste et = new ExpressaoTeste();
        et.setValorRetorno(2f);

        // Parser empregado para dependencias e produção de expressão avaliável
        ParserTeste p = new ParserTeste();
        p.setDependencias(dd);
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

        assertEquals(1f, r.avalie(null, new HashMap<>()).getReal(), 0.0001d);
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

        List<Avaliavel> relatos = new ArrayList<>(1);
        Map<String, Valor> relato = new HashMap<>(1);
        relato.put("atributo", new Valor("ok"));
        relatos.add(new Relato("livro", relato));

        Map<String, Valor> ctx = new HashMap<>(0);
        assertEquals(6f, r.avalie(null, ctx).getReal(), 0.0001f);
    }

    @Test
    public void somatorio() {
        Regra r = new RegraSomatorio("v", "d", 100, 0, "a + b", "classe");

        // Parser
        ExpressaoTeste et = new ExpressaoTeste();
        et.setValorRetorno(1f);

        ParserTeste pt = new ParserTeste();
        pt.setDependencias(new ArrayList<>(0));
        pt.setExpressao(et);

        // Preparação antes de qualquer uso da regra
        r.preparacao(pt);

        Avaliavel avaliavel = new Avaliavel() {
            @Override
            public Valor get(String atributo) {
                return null;
            }
        };

        List<Avaliavel> avaliavels = new ArrayList<>(3);
        avaliavels.add(avaliavel);
        avaliavels.add(avaliavel);

        assertEquals(2f, r.avalie(avaliavels, new HashMap<>(0)).getReal(), 0.0001f);
    }

    @Test
    public void media() {
        int tipo = Regra.MEDIA;
        List<String> deps = new ArrayList<>(1);
        deps.add("dependeDeRegra1_1");

        Regra r = new RegraExpressao("v", "d", 100, 0, "dependeDeRegra1_1");

        Map<String, Valor> dados1 = new HashMap<>(2);
        dados1.put("dependeDeRegra1_1", new Valor(2));
        Relato r1 = new Relato("livro", dados1);

        Map<String, Valor> dados2 = new HashMap<>(2);
        dados2.put("dependeDeRegra1_1", new Valor(3));
        Relato r2 = new Relato("artigo", dados2);

        List<Avaliavel> relatos = new ArrayList<>(2);
        relatos.add(r1);
        relatos.add(r2);

        Map<String, Valor> ctx = new HashMap<>(0);
        Valor resultado = avaliador.avalia(r, ctx, relatos);
        assertEquals(2.5f, resultado.getReal(), 0.0001f);
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void semRegistroZeroPontos() {
        new RegraExpressao("v", "d", 100, 0, null);
    }

    @Test
    public void umRegistroPontuacaoCorrespondente() {
        int tipo = Regra.PONTOS;
        int ppr = 13;
        Regra regra = new RegraExpressao("v", "d", 100, 0, null);

        List<Avaliavel> relatos = new ArrayList<>(1);
        relatos.add(null);

        Valor resultado = avaliador.avalia(regra, null, relatos);
        assertEquals(ppr, resultado.getReal(), 0.0001);
    }

    @Test
    public void expressaoConstante() {
        int tipo = Regra.EXPRESSAO;
        Regra regra = new RegraExpressao("v", "d", 100, 0, "97");

        Valor resultado = avaliador.avalia(regra, null, null);
        assertEquals(97f, resultado.getReal(), 0.0001);
    }

    @Test
    public void expressaoCondicional() {
        Regra regra = new RegraCondicional("c", "d", 10, 0, "condicao", "entao", "senao");

        // Parser
        ParserCondicaoTeste pct = new ParserCondicaoTeste();

        ArrayList<String> depsC = new ArrayList<>();
        depsC.add("condicao");

        ArrayList<String> depsE = new ArrayList<>();
        depsE.add("entao");

        ArrayList<String> depsS = new ArrayList<>();
        depsS.add("senao");

        pct.setDependencias("condicao", depsC);
        pct.setDependencias("entao", depsE);
        pct.setDependencias("senao", depsS);

        ExpressaoTeste etC = new ExpressaoTeste();
        etC.setValorRetorno(1f);

        ExpressaoTeste etE = new ExpressaoTeste();
        etE.setValorRetorno(1f);

        ExpressaoTeste etS = new ExpressaoTeste();
        etS.setValorRetorno(0f);

        pct.setCondicao(etC);
        pct.setEntao(etE);
        pct.setSenao(etS);

        // Preparação da regra

        regra.preparacao(pct);

        Map<String, Valor> contexto = new HashMap<>(1);
        contexto.put("condicao", new Valor(0));
        contexto.put("oito", new Valor(8));
        contexto.put("nove", new Valor(9));

        // Condição verdadeira (definido acima)
        assertEquals(1f, regra.avalie(null, contexto).getReal(), 0.0001);

        // Define condição com valor false
        etC.setValorRetorno(0f);
        assertEquals(0f, regra.avalie(null, contexto).getReal(), 0.0001);
    }
}