package br.ufg.inf.es.saep.sandbox.dominio;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class ResolucaoTrivialTest {

    @Test
    public void resolucaoVazia() {
        List<Regra> itens = new ArrayList<>();
        Resolucao r = new Resolucao("vazia", null, new Date(), itens);
        assertNotNull(r);
    }
}
