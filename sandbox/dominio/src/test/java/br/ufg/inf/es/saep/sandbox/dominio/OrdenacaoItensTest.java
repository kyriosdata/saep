package br.ufg.inf.es.saep.sandbox.dominio;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrdenacaoItensTest {

    @Test
    public void semItensOrdenacaoTrivial() {
        OrdenacaoService or = new OrdenacaoService();
        List<Regra> itens = new ArrayList<>();
        assertEquals(itens.size(), or.ordena(itens).size());
    }

    @Test
    public void umItemOrdenacaoTrivial() {
        Regra regra = new Regra("um", 1, "d", 0, 0, "10", null, null, null, 0, new ArrayList<>(0));

        // Itens cuja execução deve ser ordenada
        List<Regra> itens = new ArrayList<>(1);
        itens.add(regra);

        List<Regra> ordenados = new OrdenacaoService().ordena(itens);
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
        Regra regraX = new Regra("rx", 1, "x", 0, 0, "x", null, null, null, 0, atributosX);

        // Regra: "y" (simples propriedade)
        List<String> atributosY = new ArrayList<>(1);
        atributosY.add("y");
        Regra regraY = new Regra("ry", 1, "y", 0, 0, "y", null, null, null, 0, atributosY);

        // Regra: "rx + ry" (depende de itens avaliados)
        // (não consome simples propriedades, mas resultados
        // da avaliação de itens)
        List<String> atributosZ = new ArrayList<>();
        atributosZ.add("rx");
        atributosZ.add("ry");
        Regra regraZ = new Regra("rz", 1, "z", 0, 0, "rx + ry", null, null, null, 0, atributosZ);

        List<Regra> itens = new ArrayList<>(1);
        itens.add(regraZ);
        itens.add(regraX);
        itens.add(regraY);

        OrdenacaoService or = new OrdenacaoService();
        List<Regra> ordenados = or.ordena(itens);

        assertEquals(3, ordenados.size());
        assertEquals("rz", ordenados.get(2).getVariavel());
    }

    @Test
    public void duasRegrasOrdenacaoUnica() {
        OrdenacaoService or = new OrdenacaoService();

        // a = 10
        Regra ra = new Regra("a", 1, "a = 10", 10, 0, "10", null, null, null, 0, new ArrayList<String>(0));

        // b = a + 1
        ArrayList<String> atributos = new ArrayList<>(0);
        atributos.add("a");
        Regra rb = new Regra("b", 1, "b = a + 1", 11, 0, "a + 1", null, null, null, 0, atributos);

        // Itens que devem ser ordenados (itemA e itemB)
        List<Regra> itens = new ArrayList<>(2);
        itens.add(ra);
        itens.add(rb);

        // Executa operação de ordenação
        List<Regra> ordenados = or.ordena(itens);

        // Verifica que itemA precede itemB
        assertEquals(2, ordenados.size());
        assertEquals("a", ordenados.get(0).getVariavel());
        assertEquals("b", ordenados.get(1).getVariavel());
    }

    @Test
    public void cadItemInseridoUmaUnicaVez() {
        // Regras: "a=1", "b=a+1" e "c=a+2"
        List<String> dd = new ArrayList<>();
        Regra ra = new Regra("a", 1, "a=1", 0, 0, "1", null, null, null, 0, dd);

        List<String> dependentesB = new ArrayList<>();
        dependentesB.add("a");
        Regra rb = new Regra("b", 1, "b=a+1", 0, 0, "a+1", null, null, null, 0, dependentesB);

        List<String> dependentesC = new ArrayList<>();
        dependentesC.add("a");
        Regra rc = new Regra("c", 1, "c=a+2", 0, 0, "a+2", null, null, null, 0, dependentesC);

        OrdenacaoService oi = new OrdenacaoService();
        ArrayList<Regra> itens = new ArrayList<>();
        itens.add(ra);
        itens.add(rb);
        itens.add(rc);

        List<Regra> ordenados = oi.ordena(itens);
        assertEquals(3, ordenados.size());
    }
}
