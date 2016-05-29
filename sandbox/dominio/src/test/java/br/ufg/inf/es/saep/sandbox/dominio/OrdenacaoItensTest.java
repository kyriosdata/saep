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
        Atributo resultado = new Atributo("um", "descrica", TipoPrimitivo.REAL);

        Regra regra = new Regra(new Expressao("10"), 0, 0, new ArrayList<Atributo>(0));

        ItemAvaliado ia = new ItemAvaliado(null, regra, "d", resultado);

        // Itens cuja execução deve ser ordenada
        List<ItemAvaliado> itens = new ArrayList<ItemAvaliado>(1);
        itens.add(ia);

        List<ItemAvaliado> ordenados = new Ordenacao().ordena(itens);
        assertEquals(1, ordenados.size());
        assertEquals("um", ordenados.get(0).getResultado().getNome());
    }

    @Test
    public void umItemDependenteDeAtributoOrdenacaoTrivial() {

        // Ensino de graduação: x => rx (usa "x" resultado em "rx")
        // Ensino a distância: y => ry (usa "y" resultado em "ry")
        // Ensino: rz = rx + ry (usa "rx" e "ry" resultado em "rz")

        Atributo x = new Atributo("x", "x", TipoPrimitivo.REAL);
        Atributo rx = new Atributo("rx", "rx", TipoPrimitivo.REAL);
        Atributo y = new Atributo("y", "y", TipoPrimitivo.REAL);
        Atributo ry = new Atributo("ry", "ry", TipoPrimitivo.REAL);
        Atributo rz = new Atributo("rz", "rz", TipoPrimitivo.REAL);

        // Regra: "x" (simples propriedade)
        List<Atributo> atributosX = new ArrayList<Atributo>(1);
        atributosX.add(x);
        Regra regraX = new Regra(new Expressao("x"), 0, 0, atributosX);

        // Regra: "y" (simples propriedade)
        List<Atributo> atributosY = new ArrayList<Atributo>(1);
        atributosY.add(y);
        Regra regraY = new Regra(new Expressao("y"), 0, 0, atributosY);

        // Regra: "rx + ry" (depende de itens avaliados)
        // (não consome simples propriedades, mas resultados
        // da avaliação de itens)
        List<Atributo> atributosZ = new ArrayList<Atributo>();
        atributosZ.add(rx);
        atributosZ.add(ry);
        Regra regraZ = new Regra(new Expressao("rx + ry"), 0, 0, atributosZ);

        ItemAvaliado ix = new ItemAvaliado(null, regraX, "x", rx);
        ItemAvaliado iy = new ItemAvaliado(null, regraY, "y", ry);
        ItemAvaliado iz = new ItemAvaliado(null, regraZ, "z", rz);

        List<ItemAvaliado> itens = new ArrayList<ItemAvaliado>(1);
        itens.add(ix);
        itens.add(iy);
        itens.add(iz);

        Ordenacao or = new Ordenacao();
        List<ItemAvaliado> ordenados = or.ordena(itens);

        assertEquals(3, ordenados.size());
        assertEquals("rz", ordenados.get(2).getResultado().getNome());
    }

    @Test
    public void duasRegrasOrdenacaoUnica() {
        Ordenacao or = new Ordenacao();

        // a = 10
        Atributo a = new Atributo("a", "descricao", TipoPrimitivo.REAL);
        Regra ra = new Regra(new Expressao("10"), 10, 0, new ArrayList<Atributo>(0));
        Atributo r = new Atributo("a", "a", TipoPrimitivo.REAL);
        ItemAvaliado itemA = new ItemAvaliado(null, ra, "a = 10", r);

        // b = a + 1
        Atributo b = new Atributo("b", "descricao", TipoPrimitivo.REAL);
        ArrayList<Atributo> atributos = new ArrayList<Atributo>(0);
        atributos.add(a);
        Regra rb = new Regra(new Expressao("a + 1"), 11, 0, atributos);
        ItemAvaliado itemB = new ItemAvaliado(null, rb, "b = a + 1", b);

        // Itens que devem ser ordenados (itemA e itemB)
        List<ItemAvaliado> itens = new ArrayList<ItemAvaliado>(2);
        itens.add(itemA);
        itens.add(itemB);

        // Executa operação de ordenação
        List<ItemAvaliado> ordenados = or.ordena(itens);

        // Verifica que itemA precede itemB
        assertEquals(2, ordenados.size());
        assertEquals("a", ordenados.get(0).getResultado().getNome());
        assertEquals("b", ordenados.get(1).getResultado().getNome());
    }

    @Test
    public void cadItemInseridoUmaUnicaVez() {
        // Atributos: a, b e c
        Atributo a = new Atributo("a", "descricao", TipoPrimitivo.REAL);
        Atributo b = new Atributo("b", "descricao", TipoPrimitivo.REAL);
        Atributo c = new Atributo("c", "descricao", TipoPrimitivo.REAL);

        // Regras: "a=1", "b=a+1" e "c=a+2"
        Regra ra = new Regra(new Expressao("1"), 0, 0, new ArrayList<Atributo>());
        ItemAvaliado ia = new ItemAvaliado(null, ra, "a=1", a);

        List<Atributo> dependentesB = new ArrayList<Atributo>();
        dependentesB.add(a);
        Regra rb = new Regra(new Expressao("a+1"), 0, 0, dependentesB);
        ItemAvaliado ib = new ItemAvaliado(null, rb, "b=a+1", b);

        List<Atributo> dependentesC = new ArrayList<Atributo>();
        dependentesC.add(a);
        Regra rc = new Regra(new Expressao("a+2"), 0, 0, dependentesC);
        ItemAvaliado ic = new ItemAvaliado(null, rc, "c=a+2", c);

        Ordenacao oi = new Ordenacao();
        ArrayList<ItemAvaliado> itens = new ArrayList<ItemAvaliado>();
        itens.add(ia);
        itens.add(ib);
        itens.add(ic);

        List<ItemAvaliado> ordenados = oi.ordena(itens);
        assertEquals(3, ordenados.size());
    }
}
