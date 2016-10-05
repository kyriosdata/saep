package br.ufg.inf.es.saep.sandbox.dominio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EntidadeTest {

    @Test
    public void construcaoSemArgumentoIdAutomatico() {
        EntidadeParaTeste et = new EntidadeParaTeste();
        assertNotNull(et.getId());
    }
}

class EntidadeParaTeste extends Entidade {

}

