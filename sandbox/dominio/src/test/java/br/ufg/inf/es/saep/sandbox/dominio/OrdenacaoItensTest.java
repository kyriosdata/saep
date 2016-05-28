package br.ufg.inf.es.saep.sandbox.dominio;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrdenacaoItensTest {

    @Test
    public void semItensOrdenacaoTrivial() {
        Ordenacao or = new Ordenacao();
        List<ItemAvaliado> itens = new ArrayList<ItemAvaliado>();
        assertEquals(itens.size(), or.ordena(itens).size());
    }

    @Test
    public void umItemOrdenacaoTrivial() {
        Ordenacao or = new Ordenacao();
        Atributo resultado = new Atributo("um", "descrica", TipoPrimitivo.REAL);
        Regra regra = new Regra(new Expressao("10"), 0, 0, new ArrayList<Atributo>(0));
        Atributo depositoResultado = new Atributo("a", "d", TipoPrimitivo.REAL);
        ItemAvaliado ia = new ItemAvaliado(regra, "d", depositoResultado);

        List<ItemAvaliado> itens = new ArrayList<ItemAvaliado>(1);
        itens.add(ia);

        List<ItemAvaliado> ordenados = or.ordena(itens);
        assertEquals(1, ordenados.size());
        assertEquals("um", ordenados.get(0).getAtributo().getNome());
    }

    @Test
    public void umItemDependenteDeAtributoOrdenacaoTrivial() {
        Ordenacao or = new Ordenacao();

        // "dependente" depende de "x"
        Atributo resultado = new Atributo("dependente", "descricao", TipoPrimitivo.REAL);

        ArrayList<Atributo> atributos = new ArrayList<Atributo>(0);
        Atributo x = new Atributo("x", "um tipo para teste", TipoPrimitivo.REAL);
        atributos.add(x);

        Regra regra = new Regra(new Expressao("x"), 0, 0, atributos);
        Atributo r = new Atributo("x", "x", TipoPrimitivo.REAL);
        ItemAvaliado ia = new ItemAvaliado(regra, "x", r);

        List<ItemAvaliado> itens = new ArrayList<ItemAvaliado>(1);
        itens.add(ia);

        List<ItemAvaliado> ordenados = or.ordena(itens);
        assertEquals(1, ordenados.size());
        assertEquals("dependente", ordenados.get(0).getAtributo().getNome());
    }

    @Test
    public void duasRegrasOrdenacaoUnica() {
        Ordenacao or = new Ordenacao();

        // a = 10
        Atributo a = new Atributo("a", "descricao", TipoPrimitivo.REAL);
        Regra ra = new Regra(new Expressao("10"), 10, 0, new ArrayList<Atributo>(0));
        Atributo r = new Atributo("a", "a", TipoPrimitivo.REAL);
        ItemAvaliado itemA = new ItemAvaliado(ra, "a = 10", r);

        // b = a + 1
        Atributo b = new Atributo("b", "descricao", TipoPrimitivo.REAL);
        ArrayList<Atributo> atributos = new ArrayList<Atributo>(0);
        atributos.add(a);
        Regra rb = new Regra(new Expressao("a + 1"), 11, 0, atributos);
        ItemAvaliado itemB = new ItemAvaliado(rb, "b = a + 1", b);

        // Itens que devem ser ordenados (itemA e itemB)
        List<ItemAvaliado> itens = new ArrayList<ItemAvaliado>(2);
        itens.add(itemA);
        itens.add(itemB);

        // Executa operação de ordenação
        List<ItemAvaliado> ordenados = or.ordena(itens);

        // Verifica que itemA precede itemB
        assertEquals(2, ordenados.size());
        assertEquals("a", ordenados.get(0).getAtributo().getNome());
        assertEquals("b", ordenados.get(1).getAtributo().getNome());
    }

    @Test
    public void cadItemInseridoUmaUnicaVez() {
        // Atributos: a, b e c
        Atributo a = new Atributo("a", "descricao", TipoPrimitivo.REAL);
        Atributo b = new Atributo("b", "descricao", TipoPrimitivo.REAL);
        Atributo c = new Atributo("c", "descricao", TipoPrimitivo.REAL);

        // Regras: "a=1", "b=a+1" e "c=a+2"
        Regra ra = new Regra(new Expressao("1"), 0, 0, new ArrayList<Atributo>());
        ItemAvaliado ia = new ItemAvaliado(ra, "a=1", a);

        List<Atributo> dependentesB = new ArrayList<Atributo>();
        dependentesB.add(a);
        Regra rb = new Regra(new Expressao("a+1"), 0, 0, dependentesB);
        ItemAvaliado ib = new ItemAvaliado(rb, "b=a+1", b);

        List<Atributo> dependentesC = new ArrayList<Atributo>();
        dependentesC.add(a);
        Regra rc = new Regra(new Expressao("a+2"), 0, 0, dependentesC);
        ItemAvaliado ic = new ItemAvaliado(rc, "c=a+2", c);

        Ordenacao oi = new Ordenacao();
        ArrayList<ItemAvaliado> itens = new ArrayList<ItemAvaliado>();
        itens.add(ia);
        itens.add(ib);
        itens.add(ic);

        List<ItemAvaliado> ordenados = oi.ordena(itens);
        assertEquals(3, ordenados.size());
    }
}
