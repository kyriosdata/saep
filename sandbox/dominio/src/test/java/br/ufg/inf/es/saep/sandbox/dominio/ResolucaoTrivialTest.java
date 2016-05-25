package br.ufg.inf.es.saep.sandbox.dominio;

import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertNotNull;

public class ResolucaoTrivialTest {

    @Test
    public void resolucaoVazia() {
        Set<ItemAvaliado> itens = new HashSet<ItemAvaliado>();
        Resolucao r = new Resolucao(new Date(), "vazia", itens);
        assertNotNull(r);
    }
}
