package br.ufg.inf.es.saep.sandbox.avaliador;

import br.ufg.inf.es.saep.sandbox.dominio.*;
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
public class AvaliadorRegrasTest {
    private Avaliador avaliador;

    @Before
    public void setUp() {
        Regras regras = new Regras();
        avaliador = new Avaliador(regras);
    }

    @Test
    public void semRegistroZeroPontos() {
        int tipo = Regra.PONTOS_POR_RELATO;
        Regra regra = new Regra(tipo, null, null, null, 13, null, 100, 0);

        List<Relato> relatos = new ArrayList<>(0);
        Relatos registros = new Relatos(relatos);

        Valor resultado = avaliador.avaliaRegra(regra, null, relatos);
        assertEquals(0f, resultado.getFloat(), 0.0001);
    }

    @Test
    public void umRegistroPontuacaoCorrespondente() {
        int tipo = Regra.PONTOS_POR_RELATO;
        int ppr = 13;
        Regra regra = new Regra(tipo, null, null, null, ppr, null, 100, 0);

        List<Relato> relatos = new ArrayList<>(1);
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
        List<Relato> listaDeRelatos = new ArrayList<>(1);
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
