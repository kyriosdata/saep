package br.ufg.inf.es.saep.sandbox.infraestrutura;

import br.ufg.inf.es.saep.sandbox.dominio.Avaliavel;
import br.ufg.inf.es.saep.sandbox.dominio.Relato;
import br.ufg.inf.es.saep.sandbox.dominio.Valor;
import br.ufg.inf.es.saep.sandbox.dominio.excecoes.FalhaAoAvaliarRegra;
import br.ufg.inf.es.saep.sandbox.dominio.regra.Expressao;
import br.ufg.inf.es.saep.sandbox.dominio.regra.Regra;
import br.ufg.inf.es.saep.sandbox.dominio.regra.RegraExpressao;
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

    @Test(expected = FalhaAoAvaliarRegra.class)
    public void somatorioSemVariavelGeraExcecao() {

        List<String> dd = new ArrayList<>(1);
        dd.add("dependeDeRegra1_1");
        Regra r = new RegraExpressao("v", "d", 100, 0, "dependeDeRegra1_1");

        List<Avaliavel> relatos = new ArrayList<>(1);
        Map<String, Valor> relato = new HashMap<>(1);
        relato.put("atributo", new Valor("ok"));
        relatos.add(new Relato("livro", relato));

        Map<String, Valor> ctx = new HashMap<>(0);
        Valor resultado = avaliador.avalia(r, ctx, relatos);
        assertEquals(16f, resultado.getReal(), 0.0001f);
    }

    @Test
    public void somatorio() {
        int tipo = Regra.SOMATORIO;
        List<String> deps = new ArrayList<>(2);
        deps.add("dependeDeRegra1_1");
        deps.add("dependeDeRegra1_2");
        Regra r = new RegraExpressao("v", "d", 100, 0, "dependeDeRegra1_1 * dependeDeRegra1_2");

        Map<String, Valor> dados1 = new HashMap<>(2);
        dados1.put("dependeDeRegra1_1", new Valor(2));
        dados1.put("dependeDeRegra1_2", new Valor(2));
        Relato r1 = new Relato("livro", dados1);

        Map<String, Valor> dados2 = new HashMap<>(2);
        dados2.put("dependeDeRegra1_1", new Valor(3));
        dados2.put("dependeDeRegra1_2", new Valor(4));
        Relato r2 = new Relato("artigo", dados2);

        List<Avaliavel> relatos = new ArrayList<>(2);
        relatos.add(r1);
        relatos.add(r2);

        Map<String, Valor> ctx = new HashMap<>(0);
        Valor resultado = avaliador.avalia(r, ctx, relatos);
        assertEquals(16f, resultado.getReal(), 0.0001f);
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

    @Test
    public void semRegistroZeroPontos() {
        int tipo = Regra.PONTOS;
        Regra regra = new RegraExpressao("v", "d", 100, 0, null);

        List<Avaliavel> relatos = new ArrayList<>(0);

        Valor resultado = avaliador.avalia(regra, null, relatos);
        assertEquals(0f, resultado.getReal(), 0.0001);
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
    public void expressaoQueDependeDeOutra() {
        int tipo = Regra.EXPRESSAO;
        List<String> dependeDe = new ArrayList<>(1);
        dependeDe.add("quatro");

        Regra regra = new RegraExpressao("v", "d", 100, 0, "25 * quatro");

        // Apenas dependeDeRegra1_1 variável "quatro" está definida
        Map<String, Valor> contexto = new HashMap<>(1);
        contexto.put("quatro", new Valor(4));

        Valor resultado = avaliador.avalia(regra, contexto, null);
        assertEquals(100f, resultado.getReal(), 0.0001);
    }

    @Test
    public void cadeiaDeTresDependencias() {
        Map<String, Valor> valor = new HashMap<>(1);
        valor.put("atributo", new Valor("valor"));
        Relato relato = new Relato("EG", valor);
        List<Avaliavel> listaDeRelatos = new ArrayList<>(1);
        listaDeRelatos.add(relato);

        // Um relato de dado tipo, 11 pontos.
        // Máximo corrige expressao 10.
        int tipo = Regra.PONTOS;
        Regra r = new RegraExpressao("v", "d", 10, 0, null);

        Valor parcial = avaliador.avalia(r, null, listaDeRelatos);
        assertEquals(10f, parcial.getReal(), 0.0001f);

        Map<String, Valor> ctx = new HashMap<>(2);
        ctx.put("dez", parcial);

        tipo = Regra.EXPRESSAO;
        List<String> dependeDe = new ArrayList<>(1);
        dependeDe.add("dez");
        r = new RegraExpressao("v", "d", 250, 0, "23.1 * dez");
        parcial = avaliador.avalia(r, ctx, null);
        assertEquals(231f, parcial.getReal(), 0.0001f);

        ctx.put("v231", parcial);

        tipo = Regra.EXPRESSAO;
        dependeDe.add("v231");
        r = new RegraExpressao("v", "d", 250, 0, "v231 - 31 + dez");
        parcial = avaliador.avalia(r, ctx, null);
        assertEquals(210f, parcial.getReal(), 0.0001f);
    }

    @Test
    public void expressaoCondicional() {
        int tipo = Regra.CONDICIONAL;

        List<String> dd = new ArrayList<>(1);
        dd.add("condicao");
        dd.add("oito");
        dd.add("nove");

        Regra regra = new RegraExpressao("c", "d", 10, 0, "condicao");

        // Primeiro: false (0)
        Map<String, Valor> contexto = new HashMap<>(1);
        contexto.put("condicao", new Valor(0));
        contexto.put("oito", new Valor(8));
        contexto.put("nove", new Valor(9));

        Valor resultado = avaliador.avalia(regra, contexto, null);
        assertEquals(9f, resultado.getReal(), 0.0001);

        // Segunda: true (1)
        contexto.put("condicao", new Valor(1));
        resultado = avaliador.avalia(regra, contexto, null);
        assertEquals(8f, resultado.getReal(), 0.0001);
    }
}

class ParserTeste implements br.ufg.inf.es.saep.sandbox.dominio.regra.Parser {

    private Expressao expressaoRetorno;
    private List<String> dependencias;

    public void setExpressao(Expressao exprRetorno) {
        expressaoRetorno = exprRetorno;
    }

    public void setDependencias(List<String> deps) {
        dependencias = deps;
    }

    @Override
    public Expressao ast(String sentenca) {
        return expressaoRetorno;
    }

    @Override
    public List<String> dependencias(String sentenca) {
        return dependencias;
    }
}

class ExpressaoTeste implements Expressao {

    private float valorRetorno;

    public void setValorRetorno(float v) {
        valorRetorno = v;
    }

    @Override
    public float valor() {
        return valorRetorno;
    }

    @Override
    public float valor(Map<String, Float> contexto) {
        return valorRetorno;
    }
}
