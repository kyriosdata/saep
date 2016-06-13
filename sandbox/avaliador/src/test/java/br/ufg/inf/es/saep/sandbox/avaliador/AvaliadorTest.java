package br.ufg.inf.es.saep.sandbox.avaliador;

import br.ufg.inf.es.saep.sandbox.dominio.*;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Testes do avaliador de regras
 */
public class AvaliadorTest {
    private Avaliador avaliador;

    @Before
    public void setUp() {
        Regras regras = new Regras();
        avaliador = new Avaliador(regras);
    }

    @Test
    public void semRegistroZeroPontos() {
        List<Map<String,BigDecimal>> valores = new ArrayList<Map<String, BigDecimal>>(0);
        Registros registros = new Registros(valores);
        float resultado = avaliador.avalia(registros, 0);
        assertEquals(0f, resultado, 0.0001);
    }

    @Test
    public void umRegistroPontuacaoCorrespondente() {
        List<Map<String,BigDecimal>> valores = new ArrayList<Map<String, BigDecimal>>(0);
        Registros registros = new Registros(valores);
        float resultado = avaliador.avalia(registros, 0);
        assertEquals(1.1f, resultado, 0.0001);
    }

    @Test
    public void expressaoConstante() {
        float resultado = avaliador.avalia(null, 1);
        assertEquals(8.97f, resultado, 0.0001);
    }

    @Test
    public void expressaoQueDependeDeOutras() {
        List<Map<String,BigDecimal>> valores = new ArrayList<Map<String, BigDecimal>>(0);
        Registros registros = new Registros(valores);

        float resultado = avaliador.avalia(registros, 0);
        assertEquals(1.1f, resultado, 0.0001);

        resultado = avaliador.avalia(null, 1);
        assertEquals(8.97f, resultado, 0.0001);

        resultado = avaliador.avalia(null, 2);
        assertEquals(10.07f, resultado, 0.0001);

        resultado = avaliador.avalia(null, 3);
        assertEquals(1f, resultado, 0.0001);

        resultado = avaliador.avalia(registros, 6);
        assertEquals(4f, resultado, 0.0001);
    }
}
