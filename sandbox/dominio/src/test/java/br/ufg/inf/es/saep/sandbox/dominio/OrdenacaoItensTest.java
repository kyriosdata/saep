package br.ufg.inf.es.saep.sandbox.dominio;

import br.ufg.inf.es.saep.sandbox.ParserTeste;
import br.ufg.inf.es.saep.sandbox.dominio.regra.OrdenacaoService;
import br.ufg.inf.es.saep.sandbox.dominio.regra.Regra;
import br.ufg.inf.es.saep.sandbox.dominio.regra.RegraExpressao;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static br.ufg.inf.es.saep.sandbox.dominio.regra.OrdenacaoService.ordena;
import static org.junit.Assert.assertEquals;

public class OrdenacaoItensTest {

    @Test
    public void semItensOrdenacaoTrivial() {
        OrdenacaoService or = new OrdenacaoService();
        List<Regra> itens = new ArrayList<>();
        assertEquals(itens.size(), ordena(itens).size());
    }

    @Test
    public void umItemOrdenacaoTrivial() {
        Regra regra = new RegraExpressao("um", "d", 0, 0, "10");

        // Itens cuja execução deve ser ordenada
        List<Regra> itens = new ArrayList<>(1);
        itens.add(regra);

        ParserTeste pt = new ParserTeste();
        pt.setDependencias(new ArrayList<>(0));

        regra.preparacao(pt);

        List<Regra> ordenados = ordena(itens);
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
        Regra regraX = new RegraExpressao("rx", "x", 0, 0, "x");

        // Regra: "y" (simples propriedade)
        List<String> atributosY = new ArrayList<>(1);
        atributosY.add("y");
        Regra regraY = new RegraExpressao("ry", "y", 0, 0, "y");

        // Regra: "rx + ry" (depende de itens avaliados)
        // (não consome simples propriedades, mas resultados
        // da avaliação de itens)
        List<String> atributosZ = new ArrayList<>();
        atributosZ.add("rx");
        atributosZ.add("ry");
        Regra regraZ = new RegraExpressao("rz", "z", 0, 0, "rx + ry");

        List<Regra> itens = new ArrayList<>(1);
        itens.add(regraZ);
        itens.add(regraX);
        itens.add(regraY);

        // Preparação das regras
        ParserTeste ptX = new ParserTeste();
        ptX.setDependencias(atributosX);

        ParserTeste ptY = new ParserTeste();
        ptY.setDependencias(atributosY);

        ParserTeste ptZ = new ParserTeste();
        ptZ.setDependencias(atributosZ);

        regraX.preparacao(ptX);
        regraY.preparacao(ptY);
        regraZ.preparacao(ptZ);

        List<Regra> ordenados = ordena(itens);

        assertEquals(3, ordenados.size());
        assertEquals("rz", ordenados.get(2).getVariavel());
    }

    @Test
    public void duasRegrasOrdenacaoUnica() {
        OrdenacaoService or = new OrdenacaoService();

        // a = 10
        Regra ra = new RegraExpressao("a", "a = 10", 10, 0, "10");

        // b = a + 1
        Regra rb = new RegraExpressao("b", "b = a + 1", 11, 0, "a + 1");

        // Dependências de "b", apenas "a"
        List<String> depsB = new ArrayList<>(0);
        depsB.add("a");

        // Itens que devem ser ordenados (itemA e itemB)
        List<Regra> regras = new ArrayList<>(2);
        regras.add(ra);
        regras.add(rb);

        // Preparação das regras (antes de qualquer uso)
        ParserTeste ptB = new ParserTeste();
        ptB.setDependencias(depsB);

        ParserTeste ptA = new ParserTeste();
        ptA.setDependencias(new ArrayList<>(0));

        ra.preparacao(ptA);
        rb.preparacao(ptB);

        // Executa operação de ordenação
        List<Regra> ordenados = ordena(regras);

        // Verifica que itemA precede itemB
        assertEquals(2, ordenados.size());
        assertEquals("a", ordenados.get(0).getVariavel());
        assertEquals("b", ordenados.get(1).getVariavel());
    }

    @Test
    public void cadItemInseridoUmaUnicaVez() {
        // Regras: "a=1", "b=a+1" e "c=a+2"
        Regra ra = new RegraExpressao("a", "a=1", 0, 0, "1");
        Regra rb = new RegraExpressao("b", "b=a+1", 0, 0, "a+1");
        Regra rc = new RegraExpressao("c", "c=a+2", 0, 0, "a+2");

        // Parser para as regras
        ParserTeste ptA = new ParserTeste();
        List<String> da = new ArrayList<>();
        ptA.setDependencias(da);

        ParserTeste ptB = new ParserTeste();
        List<String> db = new ArrayList<>();
        db.add("a");
        ptB.setDependencias(db);

        ParserTeste ptC = new ParserTeste();
        List<String> dc = new ArrayList<>();
        dc.add("a");
        ptC.setDependencias(dc);

        // Preparação das regras antes de qualquer uso
        ra.preparacao(ptA);
        rb.preparacao(ptB);
        rc.preparacao(ptC);

        OrdenacaoService oi = new OrdenacaoService();
        ArrayList<Regra> itens = new ArrayList<>();
        itens.add(ra);
        itens.add(rb);
        itens.add(rc);

        List<Regra> ordenados = ordena(itens);
        assertEquals(3, ordenados.size());
    }
}
