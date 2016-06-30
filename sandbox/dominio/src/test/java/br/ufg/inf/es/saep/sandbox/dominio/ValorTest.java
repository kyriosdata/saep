package br.ufg.inf.es.saep.sandbox.dominio;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValorTest {

    @Test
    public void umValorLogicoFalso() {
        Valor verdadeiro = new Valor(false);
        assertFalse(verdadeiro.getBoolean());
    }

    @Test
    public void umValorLogicoVerdadeiro() {
        Valor verdadeiro = new Valor(true);
        assertTrue(verdadeiro.getBoolean());
    }

    @Test
    public void umValorLogicoObtidoDeTipoIncompativelResultaFalse() {
        Valor verdadeiro = new Valor("teste");
        assertFalse(verdadeiro.getBoolean());
    }

    @Test
    public void umValorLogicoObtidoDeTipoIncompativel2ResultaFalse() {
        Valor verdadeiro = new Valor(23);
        assertFalse(verdadeiro.getBoolean());
    }
}

