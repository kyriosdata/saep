package br.ufg.inf.es.saep.sandbox.dominio;

import org.junit.Test;

public class IdentificadorExistenteTest {

    @Test(expected = IdentificadorExistente.class)
    public void justificativaNullGeraExcecao() {
        throw new IdentificadorExistente("x");
    }
}

