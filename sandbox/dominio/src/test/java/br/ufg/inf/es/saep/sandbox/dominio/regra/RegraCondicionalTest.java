package br.ufg.inf.es.saep.sandbox.dominio.regra;

import br.ufg.inf.es.saep.sandbox.ExpressaoTeste;
import br.ufg.inf.es.saep.sandbox.ParserCondicaoTeste;
import br.ufg.inf.es.saep.sandbox.ParserTeste;
import br.ufg.inf.es.saep.sandbox.dominio.Avaliavel;
import br.ufg.inf.es.saep.sandbox.dominio.Relato;
import br.ufg.inf.es.saep.sandbox.dominio.Valor;
import br.ufg.inf.es.saep.sandbox.dominio.excecoes.CampoExigidoNaoFornecido;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Testes do avaliador de regras
 */
public class RegraCondicionalTest {

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void entaoObrigatoriamenteDeveSerFornecido() {
        new RegraCondicional("v", "d", 1, 0, "c", null, "s");
    }

    @Test
    public void agradarCobertura() {
        RegraCondicional regra = new RegraCondicional("c", "d", 10, 0, "condicao", "entao", "senao");
        assertEquals("entao", regra.getEntao());
        assertEquals("senao", regra.getSenao());
    }

    @Test
    public void expressaoCondicional() {
        Regra regra = new RegraCondicional("c", "d", 10, 0, "condicao", "entao", "senao");

        // Parser
        ParserCondicaoTeste pct = new ParserCondicaoTeste();

        ArrayList<String> depsC = new ArrayList<>();
        depsC.add("condicao");

        ArrayList<String> depsE = new ArrayList<>();
        depsE.add("entao");

        ArrayList<String> depsS = new ArrayList<>();
        depsS.add("senao");

        pct.setDependencias("condicao", depsC);
        pct.setDependencias("entao", depsE);
        pct.setDependencias("senao", depsS);

        ExpressaoTeste etC = new ExpressaoTeste();
        etC.setValorRetorno(1f);

        ExpressaoTeste etE = new ExpressaoTeste();
        etE.setValorRetorno(1f);

        ExpressaoTeste etS = new ExpressaoTeste();
        etS.setValorRetorno(0f);

        pct.setCondicao(etC);
        pct.setEntao(etE);
        pct.setSenao(etS);

        // Preparação da regra

        regra.preparacao(pct);

        Map<String, Valor> contexto = new HashMap<>(1);
        contexto.put("condicao", new Valor(0));
        contexto.put("oito", new Valor(8));
        contexto.put("nove", new Valor(9));

        // Condição verdadeira (definido acima)
        assertEquals(1f, regra.avalie(null, contexto).getReal(), 0.0001);

        // Define condição com valor false
        etC.setValorRetorno(0f);
        assertEquals(0f, regra.avalie(null, contexto).getReal(), 0.0001);
    }
}
