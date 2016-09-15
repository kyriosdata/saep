package br.ufg.inf.es.saep.sandbox.infraestrutura;

import br.ufg.inf.es.saep.sandbox.dominio.*;
import br.ufg.inf.es.saep.sandbox.dominio.excecoes.FalhaAoAvaliarRegra;
import br.ufg.inf.es.saep.sandbox.dominio.regra.Regra;
import br.ufg.inf.es.saep.sandbox.dominio.regra.RegraExpressao;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

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

    @Test(expected = FalhaAoAvaliarRegra.class)
    public void naoAdmiteContextoNullQuandoDependeDeVariavel() {
        // Depende de variável cujo contexto fornecido é null!
        ArrayList<String> dd = new ArrayList<>(0);
        dd.add("dependeDeRegra1_1");

        Regra regra = new RegraExpressao("v", 1, "d", 100, 0, "1", null, null, null, 0, dd);
        avaliador.avalia(regra, null, null);
    }

    @Test(expected = FalhaAoAvaliarRegra.class)
    public void avaliaExpressaoSemVariavelDefinidaGeraExcecao() {
        int tipo = Regra.EXPRESSAO;
        List<String> deps = new ArrayList<>(1);
        deps.add("dependeDeRegra1_1");
        Regra r = new RegraExpressao("v", tipo, "d", 100, 0, "1 + dependeDeRegra1_1", null, null, null, 0,deps);

        avaliador.avalia(r, new HashMap<>(0), null);
    }

    @Test(expected = FalhaAoAvaliarRegra.class)
    public void somatorioSemVariavelGeraExcecao() {
        int tipo = Regra.SOMATORIO;

        List<String> dd = new ArrayList<>(1);
        dd.add("dependeDeRegra1_1");
        Regra r = new RegraExpressao("v", tipo, "d", 100, 0, "dependeDeRegra1_1", null, null, null, 0, dd);

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
        Regra r = new RegraExpressao("v", tipo, "d", 100, 0, "dependeDeRegra1_1 * dependeDeRegra1_2", null, null, null, 0, deps);

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

        Regra r = new RegraExpressao("v", tipo, "d", 100, 0, "dependeDeRegra1_1", null, null, null, 0, deps);

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
        Regra regra = new RegraExpressao("v", tipo, "d", 100, 0, null, null, null, "r", 13, null);

        List<Avaliavel> relatos = new ArrayList<>(0);

        Valor resultado = avaliador.avalia(regra, null, relatos);
        assertEquals(0f, resultado.getReal(), 0.0001);
    }

    @Test
    public void umRegistroPontuacaoCorrespondente() {
        int tipo = Regra.PONTOS;
        int ppr = 13;
        Regra regra = new RegraExpressao("v", tipo, "d", 100, 0, null, null, null, "r", ppr, null);

        List<Avaliavel> relatos = new ArrayList<>(1);
        relatos.add(null);

        Valor resultado = avaliador.avalia(regra, null, relatos);
        assertEquals(ppr, resultado.getReal(), 0.0001);
    }

    @Test
    public void expressaoConstante() {
        int tipo = Regra.EXPRESSAO;
        Regra regra = new RegraExpressao("v", tipo, "d", 100, 0, "97", null, null, "r", 0, new ArrayList<>());

        Valor resultado = avaliador.avalia(regra, null, null);
        assertEquals(97f, resultado.getReal(), 0.0001);
    }

    @Test
    public void expressaoQueDependeDeOutra() {
        int tipo = Regra.EXPRESSAO;
        List<String> dependeDe = new ArrayList<>(1);
        dependeDe.add("quatro");

        Regra regra = new RegraExpressao("v", tipo, "d", 100, 0, "25 * quatro", null, null, null, 0, dependeDe);

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
        // Máximo corrige para 10.
        int tipo = Regra.PONTOS;
        Regra r = new RegraExpressao("v", tipo, "d", 10, 0, null, null, null, "r", 11, null);

        Valor parcial = avaliador.avalia(r, null, listaDeRelatos);
        assertEquals(10f, parcial.getReal(), 0.0001f);

        Map<String, Valor> ctx = new HashMap<>(2);
        ctx.put("dez", parcial);

        tipo = Regra.EXPRESSAO;
        List<String> dependeDe = new ArrayList<>(1);
        dependeDe.add("dez");
        r = new RegraExpressao("v", tipo, "d", 250, 0, "23.1 * dez", null, null, null, 0, dependeDe);
        parcial = avaliador.avalia(r, ctx, null);
        assertEquals(231f, parcial.getReal(), 0.0001f);

        ctx.put("v231", parcial);

        tipo = Regra.EXPRESSAO;
        dependeDe.add("v231");
        r = new RegraExpressao("v", tipo, "d", 250, 0, "v231 - 31 + dez", null, null, null, 0, dependeDe);
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

        Regra regra = new RegraExpressao("c", tipo, "d", 10, 0, "condicao", "oito", "nove", null, 0, dd);

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
