package br.ufg.inf.es.saep.sandbox.dominio;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrdenacaoItensTest {

    @Test
    public void semItensOrdenacaoTrivial() {
        OrdenacaoService or = new OrdenacaoService();
        List<ItemAvaliado> itens = new ArrayList<ItemAvaliado>();
        assertEquals(itens.size(), or.ordena(itens).size());
    }

    @Test
    public void umItemOrdenacaoTrivial() {
        Regra regra = new Regra("10", 0, 0, new ArrayList<String>(0));

        ItemAvaliado ia = new ItemAvaliado(regra, "d", "um");

        // Itens cuja execução deve ser ordenada
        List<ItemAvaliado> itens = new ArrayList<ItemAvaliado>(1);
        itens.add(ia);

        List<ItemAvaliado> ordenados = new OrdenacaoService().ordena(itens);
        assertEquals(1, ordenados.size());
        assertEquals("um", ordenados.get(0).getVariavel());
    }

    @Test
    public void umItemDependenteDeAtributoOrdenacaoTrivial() {

        // Ensino de graduação: x => rx (usa "x" resultado em "rx")
        // Ensino a distância: y => ry (usa "y" resultado em "ry")
        // Ensino: rz = rx + ry (usa "rx" e "ry" resultado em "rz")

        // Regra: "x" (simples propriedade)
        List<String> atributosX = new ArrayList<>(1);
        atributosX.add("x");
        Regra regraX = new Regra("x", 0, 0, atributosX);

        // Regra: "y" (simples propriedade)
        List<String> atributosY = new ArrayList<>(1);
        atributosY.add("y");
        Regra regraY = new Regra("y", 0, 0, atributosY);

        // Regra: "rx + ry" (depende de itens avaliados)
        // (não consome simples propriedades, mas resultados
        // da avaliação de itens)
        List<String> atributosZ = new ArrayList<>();
        atributosZ.add("rx");
        atributosZ.add("ry");
        Regra regraZ = new Regra("rx + ry", 0, 0, atributosZ);

        ItemAvaliado ix = new ItemAvaliado(regraX, "x", "rx");
        ItemAvaliado iy = new ItemAvaliado(regraY, "y", "ry");
        ItemAvaliado iz = new ItemAvaliado(regraZ, "z", "rz");

        List<ItemAvaliado> itens = new ArrayList<ItemAvaliado>(1);
        itens.add(ix);
        itens.add(iy);
        itens.add(iz);

        OrdenacaoService or = new OrdenacaoService();
        List<ItemAvaliado> ordenados = or.ordena(itens);

        assertEquals(3, ordenados.size());
        assertEquals("rz", ordenados.get(2).getVariavel());
    }

    @Test
    public void duasRegrasOrdenacaoUnica() {
        OrdenacaoService or = new OrdenacaoService();

        // a = 10
        Regra ra = new Regra("10", 10, 0, new ArrayList<>(0));
        ItemAvaliado itemA = new ItemAvaliado(ra, "a = 10", "a");

        // b = a + 1
        Atributo b = new Atributo("b", "descricao", Atributo.REAL);
        ArrayList<String> atributos = new ArrayList<>(0);
        atributos.add("a");
        Regra rb = new Regra("a + 1", 11, 0, atributos);
        ItemAvaliado itemB = new ItemAvaliado(rb, "b = a + 1", "b");

        // Itens que devem ser ordenados (itemA e itemB)
        List<ItemAvaliado> itens = new ArrayList<ItemAvaliado>(2);
        itens.add(itemA);
        itens.add(itemB);

        // Executa operação de ordenação
        List<ItemAvaliado> ordenados = or.ordena(itens);

        // Verifica que itemA precede itemB
        assertEquals(2, ordenados.size());
        assertEquals("a", ordenados.get(0).getVariavel());
        assertEquals("b", ordenados.get(1).getVariavel());
    }

    @Test
    public void cadItemInseridoUmaUnicaVez() {
        // Regras: "a=1", "b=a+1" e "c=a+2"
        Regra ra = new Regra("1", 0, 0, new ArrayList<>());
        ItemAvaliado ia = new ItemAvaliado(ra, "a=1", "a");

        List<String> dependentesB = new ArrayList<>();
        dependentesB.add("a");
        Regra rb = new Regra("a+1", 0, 0, dependentesB);
        ItemAvaliado ib = new ItemAvaliado(rb, "b=a+1", "b");

        List<String> dependentesC = new ArrayList<>();
        dependentesC.add("a");
        Regra rc = new Regra("a+2", 0, 0, dependentesC);
        ItemAvaliado ic = new ItemAvaliado(rc, "c=a+2", "c");

        OrdenacaoService oi = new OrdenacaoService();
        ArrayList<ItemAvaliado> itens = new ArrayList<ItemAvaliado>();
        itens.add(ia);
        itens.add(ib);
        itens.add(ic);

        List<ItemAvaliado> ordenados = oi.ordena(itens);
        assertEquals(3, ordenados.size());
    }
}
