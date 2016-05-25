package br.ufg.inf.es.saep.sandbox.dominio;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrdenacaoItensTest {

    @Test
    public void semItensOrdenacaoTrivial() {
        OrdenacaoItemAvaliado or = new OrdenacaoItemAvaliado();
        List<ItemAvaliado> itens = new ArrayList<ItemAvaliado>();
        assertEquals(itens.size(), or.ordena(itens).size());
    }

    @Test
    public void umItemOrdenacaoTrivial() {
        OrdenacaoItemAvaliado or = new OrdenacaoItemAvaliado();
        Atributo resultado = new Atributo("um", TipoPrimitivo.REAL);
        Regra regra = new Regra(new Expressao("10"), 0, 0, new ArrayList<Atributo>(0));
        ItemAvaliado ia = new Tipo(null, regra, "1", resultado);

        List<ItemAvaliado> itens = new ArrayList<ItemAvaliado>(1);
        itens.add(ia);

        List<ItemAvaliado> ordenados = or.ordena(itens);
        assertEquals(1, ordenados.size());
        assertEquals("um", ordenados.get(0).getAtributo().getNome());
    }
}
