package br.ufg.inf.es.saep.sandbox.dominio.regra;

import br.ufg.inf.es.saep.sandbox.ExpressaoTeste;
import br.ufg.inf.es.saep.sandbox.ParserCondicaoTeste;
import br.ufg.inf.es.saep.sandbox.ParserTeste;
import br.ufg.inf.es.saep.sandbox.dominio.Avaliavel;
import br.ufg.inf.es.saep.sandbox.dominio.Pontuacao;
import br.ufg.inf.es.saep.sandbox.dominio.Relato;
import br.ufg.inf.es.saep.sandbox.dominio.Valor;
import br.ufg.inf.es.saep.sandbox.dominio.excecoes.CampoExigidoNaoFornecido;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Testes do avaliador de regras
 */
public class RegraSomatorioTest {

    @Test
    public void situacoesExcepcionaisDeConstrucao() {
        assertThrows(CampoExigidoNaoFornecido.class, () -> new RegraSomatorio("v", "d", 1, 0, null, null));
    }

    @Test
    public void nenhumAvaliavelSomatorioZero() {
        Regra r = new RegraSomatorio("v", "d", 1, 0, "1", null);

        List<Avaliavel> avaliaveis = new ArrayList<>(0);
        assertEquals(0, r.avalie(avaliaveis, null).getReal(), 0.0001f);
    }

    @Test
    public void nenhumAvaliavelDaClasseSomatorioZero() {
        Regra r = new RegraSomatorio("v", "d", 1, 0, "1", "x");

        List<Avaliavel> avaliaveis = new ArrayList<>(0);
        avaliaveis.add(new Pontuacao("p", new Valor(1f)));
        assertEquals(0, r.avalie(avaliaveis, null).getReal(), 0.0001f);
    }

    @Test
    public void umAvaliavelComExpressaoConstante() {
        // Parser que adequadamente identifica dependência "x" na expressão "x"
        ParserTeste parser = new ParserTeste();
        ArrayList<String> deps = new ArrayList<>();
        deps.add("x");
        parser.setDependencias(deps);

        Regra r = new RegraSomatorio("v", "d", 1, 0, "1", "x");
        r.preparacao(parser);

        List<Avaliavel> avaliaveis = new ArrayList<>(0);
        HashMap<String, Valor> atributos = new HashMap<>(0);
        atributos.put("a", new Valor(true));
        avaliaveis.add(new Relato("x", atributos));

        assertEquals(0, r.avalie(avaliaveis, null).getReal(), 0.0001f);
    }
}
