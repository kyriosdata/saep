package br.ufg.inf.es.saep.sandbox.infraestrutura;

import br.ufg.inf.es.saep.sandbox.dominio.*;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Testes do avaliador de regras
 */
public class AvaliadorRegrasTest {
    private Avaliador avaliador;

    @Before
    public void setUp() {
        avaliador = new Avaliador();
    }

    @Test(expected = AvaliacaoRegraException.class)
    public void avaliaExpressaoSemVariavelDefinidaGeraExcecao() {
        int tipo = Regra.EXPRESSAO;
        List<String> deps = new ArrayList<>(1);
        deps.add("a");
        Regra r = new Regra(tipo, "1 + a", null, null, 0,deps, 100, 0);

        avaliador.avaliaRegra(r, new HashMap<>(0), null);
    }

    @Test(expected = AvaliacaoRegraException.class)
    public void somatorioSemVariavelGeraExcecao() {
        int tipo = Regra.SOMATORIO;
        Regra r = new Regra(tipo, "a", null, null, 0, Arrays.asList("a"), 100, 0);

        List<Avaliavel> relatos = new ArrayList<>(1);
        relatos.add(new Relato("livro", new HashMap<>(0)));

        Map<String, Valor> ctx = new HashMap<>(0);
        Valor resultado = avaliador.avaliaRegra(r, ctx, relatos);
        assertEquals(16f, resultado.getFloat(), 0.0001f);
    }

    @Test
    public void somatorio() {
        int tipo = Regra.SOMATORIO;
        List<String> deps = new ArrayList<>(2);
        deps.add("a");
        deps.add("b");
        Regra r = new Regra(tipo, "a * b", null, null, 0, deps, 100, 0);

        Map<String, Valor> dados1 = new HashMap<>(2);
        dados1.put("a", new Valor(2));
        dados1.put("b", new Valor(2));
        Relato r1 = new Relato("livro", dados1);

        Map<String, Valor> dados2 = new HashMap<>(2);
        dados2.put("a", new Valor(3));
        dados2.put("b", new Valor(4));
        Relato r2 = new Relato("artigo", dados2);

        List<Avaliavel> relatos = new ArrayList<>(2);
        relatos.add(r1);
        relatos.add(r2);

        Map<String, Valor> ctx = new HashMap<>(0);
        Valor resultado = avaliador.avaliaRegra(r, ctx, relatos);
        assertEquals(16f, resultado.getFloat(), 0.0001f);
    }

    @Test
    public void media() {
        int tipo = Regra.MEDIA;
        List<String> deps = new ArrayList<>(1);
        deps.add("a");

        Regra r = new Regra(tipo, "a", null, null, 0, deps, 100, 0);

        Map<String, Valor> dados1 = new HashMap<>(2);
        dados1.put("a", new Valor(2));
        Relato r1 = new Relato("livro", dados1);

        Map<String, Valor> dados2 = new HashMap<>(2);
        dados2.put("a", new Valor(3));
        Relato r2 = new Relato("artigo", dados2);

        List<Avaliavel> relatos = new ArrayList<>(2);
        relatos.add(r1);
        relatos.add(r2);

        Map<String, Valor> ctx = new HashMap<>(0);
        Valor resultado = avaliador.avaliaRegra(r, ctx, relatos);
        assertEquals(2.5f, resultado.getFloat(), 0.0001f);
    }

    @Test
    public void semRegistroZeroPontos() {
        int tipo = Regra.PONTOS_POR_RELATO;
        Regra regra = new Regra(tipo, null, null, null, 13, null, 100, 0);

        List<Avaliavel> relatos = new ArrayList<>(0);

        Valor resultado = avaliador.avaliaRegra(regra, null, relatos);
        assertEquals(0f, resultado.getFloat(), 0.0001);
    }

    @Test
    public void umRegistroPontuacaoCorrespondente() {
        int tipo = Regra.PONTOS_POR_RELATO;
        int ppr = 13;
        Regra regra = new Regra(tipo, null, null, null, ppr, null, 100, 0);

        List<Avaliavel> relatos = new ArrayList<>(1);
        relatos.add(null);

        Valor resultado = avaliador.avaliaRegra(regra, null, relatos);
        assertEquals(ppr, resultado.getFloat(), 0.0001);
    }

    @Test
    public void expressaoConstante() {
        int tipo = Regra.EXPRESSAO;
        Regra regra = new Regra(tipo, "97", null, null, 0, null, 100, 0);

        Valor resultado = avaliador.avaliaRegra(regra, null, null);
        assertEquals(97f, resultado.getFloat(), 0.0001);
    }

    @Test
    public void expressaoQueDependeDeOutra() {
        int tipo = Regra.EXPRESSAO;
        List<String> dependeDe = new ArrayList<>(1);
        dependeDe.add("quatro");

        Regra regra = new Regra(tipo, "25 * quatro", null, null, 0, dependeDe, 100, 0);

        // Apenas a variável "quatro" está definida
        Map<String, Valor> contexto = new HashMap<>(1);
        contexto.put("quatro", new Valor(4));

        Valor resultado = avaliador.avaliaRegra(regra, contexto, null);
        assertEquals(100f, resultado.getFloat(), 0.0001);
    }

    @Test
    public void cadeiaDeTresDependencias() {
        Relato relato = new Relato("EG", new HashMap<>(0));
        List<Avaliavel> listaDeRelatos = new ArrayList<>(1);
        listaDeRelatos.add(relato);

        // Um relato de dado tipo, 11 pontos.
        // Máximo corrige para 10.
        int tipo = Regra.PONTOS_POR_RELATO;
        Regra r = new Regra(tipo, null, null, null,11, null, 10, 0);

        Valor parcial = avaliador.avaliaRegra(r, null, listaDeRelatos);
        assertEquals(10f, parcial.getFloat(), 0.0001f);

        Map<String, Valor> ctx = new HashMap<>(2);
        ctx.put("dez", parcial);

        tipo = Regra.EXPRESSAO;
        List<String> dependeDe = new ArrayList<>(1);
        dependeDe.add("dez");
        r = new Regra(tipo, "23.1 * dez", null, null, 0, dependeDe, 250, 0);
        parcial = avaliador.avaliaRegra(r, ctx, null);
        assertEquals(231f, parcial.getFloat(), 0.0001f);

        ctx.put("v231", parcial);

        tipo = Regra.EXPRESSAO;
        dependeDe.add("v231");
        r = new Regra(tipo, "v231 - 31 + dez", null, null, 0, dependeDe, 250, 0);
        parcial = avaliador.avaliaRegra(r, ctx, null);
        assertEquals(210f, parcial.getFloat(), 0.0001f);
    }
}
