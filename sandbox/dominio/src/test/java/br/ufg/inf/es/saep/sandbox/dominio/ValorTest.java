package br.ufg.inf.es.saep.sandbox.dominio;

import org.junit.Test;

public class ValorTest {

    @Test
    public void umValorLogico() {
        Atributo atributo = new Atributo("exercito", "serviu?", TipoPrimitivo.LOGICO);
        Valor verdadeiro = new Valor(atributo, false);
    }
}

