package br.ufg.inf.es.saep.sandbox.dominio;

import br.ufg.inf.es.saep.sandbox.dominio.excecoes.CampoExigidoNaoFornecido;
import br.ufg.inf.es.saep.sandbox.dominio.regra.Configuracao;
import br.ufg.inf.es.saep.sandbox.dominio.regra.Regra;
import br.ufg.inf.es.saep.sandbox.dominio.regra.RegraExpressao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConfiguracaoTest {

    private static List<Regra> regras;

    @BeforeAll
    public static void setUp() {
        Regra r = new RegraExpressao("v", "d", 1, 0, "a");
        regras = new ArrayList<>();
        regras.add(r);
    }

    @Test
    public void situacoesExcepcionais() {

        assertThrows(CampoExigidoNaoFornecido.class,
                () -> new Configuracao(null, "r", "d", new Date(), regras));
        assertThrows(CampoExigidoNaoFornecido.class,
                () -> new Configuracao("", "r", "d", new Date(), regras));
        assertThrows(CampoExigidoNaoFornecido.class,
                () -> new Configuracao("id", "d", null, new Date(), regras));
        assertThrows(CampoExigidoNaoFornecido.class,
                () -> new Configuracao("id", "d", "", new Date(), regras));
        assertThrows(CampoExigidoNaoFornecido.class,
                () -> new Configuracao("id", "d", "d", null, regras));
        assertThrows(CampoExigidoNaoFornecido.class,
                () -> new Configuracao("id", "d", "d", new Date(), null));
    }

    @Test
    public void regrasVaziaGeraExcecao() {

        List<Regra> vazias = new ArrayList<>(0);
        assertThrows(CampoExigidoNaoFornecido.class,
                () -> new Configuracao("id", "d", "d", new Date(), vazias));
    }

    @Test
    public void regraValidaNaoGeraExcecao() {

        Date dataAprovacao = new Date();
        Configuracao r = new Configuracao("id", "n", "d", dataAprovacao, regras);

        assertEquals("id", r.getId());
        assertEquals("n", r.getNome());
        assertEquals("d", r.getDescricao());
        assertEquals(dataAprovacao, r.getData());
        assertEquals(regras, r.getRegras());
    }

    @Test
    public void desigualdade() {
        Configuracao r = new Configuracao("i", "n", "d", new Date(), regras);

        assertNotEquals(r, null);
        assertNotEquals(r, "casa");
    }

    @Test
    public void resolucoesIguaisSeIdsIguais() {
        Configuracao r1 = new Configuracao("i", "x", "d", new Date(), regras);
        Configuracao r2 = new Configuracao("i", "y", "d", new Date(), regras);
        Configuracao r3 = new Configuracao("a", "y", "d", new Date(), regras);

        assertEquals(r1, r1);
        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());

        assertNotEquals(r1, r3);
    }
}
