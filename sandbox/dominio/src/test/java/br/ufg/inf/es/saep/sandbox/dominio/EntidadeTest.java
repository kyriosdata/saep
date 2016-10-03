package br.ufg.inf.es.saep.sandbox.dominio;

import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;

public class EntidadeTest {

    @Test
    public void construcaoSemArgumentoIdAutomatico() {
        EntidadeParaTeste et = new EntidadeParaTeste();
        assertNotNull(et.getId());
    }
}

class EntidadeParaTeste extends Entidade {

}

