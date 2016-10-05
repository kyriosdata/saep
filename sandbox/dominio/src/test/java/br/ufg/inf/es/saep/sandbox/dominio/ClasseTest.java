package br.ufg.inf.es.saep.sandbox.dominio;

import br.ufg.inf.es.saep.sandbox.dominio.excecoes.CampoExigidoNaoFornecido;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ClasseTest {

    @Test
    public void tipoDevePossuirIdentificadorNaoNull() {

        assertThrows(CampoExigidoNaoFornecido.class, () -> new Classe(null, "n", "d", null));
    }

    @Test
    public void tipoDevePossuirIdentificadorNaoVazio() {

        assertThrows(CampoExigidoNaoFornecido.class, () -> new Classe("", "n", "d", null));
    }

    @Test
    public void tipoDevePossuirAtributos() {

        assertThrows(CampoExigidoNaoFornecido.class, () -> new Classe("c", "n", "d", null));
    }

    @Test
    public void tipoDevePossuirPeloMenosUmAtributo() {
        Set<Atributo> atrs = new HashSet<>(0);
        assertThrows(CampoExigidoNaoFornecido.class, () -> new Classe("c", "n", "d", atrs));
    }

    @Test
    public void nomeDoTipoNaoPodeSerNull() {
        Atributo atributo = new Atributo("a", "d", 1);
        Set<Atributo> atrs = new HashSet<>(0);
        atrs.add(atributo);
        assertThrows(CampoExigidoNaoFornecido.class, () -> new Classe("c", null, "d", atrs));
    }

    @Test
    public void testesParaCasosDeIgualdade() {
        Set<Atributo> atrs = new HashSet<>(1);
        atrs.add(new Atributo("a", "d", Atributo.REAL));

        Classe t1 = new Classe("c", "n", "d", atrs);

        // igual a si proprio
        assertEquals(t1, t1);

        // diferente de null
        assertNotEquals(t1, null);

        // diferente de outro objeto
        assertNotEquals(t1, "sequencia");
    }

    @Test
    public void tipoIdentificadoPorCodigo() {
        Set<Atributo> atrs = new HashSet<>(1);
        atrs.add(new Atributo("a", "d", Atributo.REAL));

        Classe t1 = new Classe("c", "n", "d", atrs);
        Classe t2 = new Classe("c", "N", "D", atrs);

        Set<Classe> tipos = new HashSet<>(2);
        assertTrue(tipos.add(t1));
        assertFalse(tipos.add(t2));
    }

    @Test
    public void aulaPresencialNaGraduacao() {

        // O nome da disciplina
        Atributo nome = new Atributo("nome",
                "nome da disciplina",
                Atributo.STRING);

        // A carga horária da disciplina
        Atributo cha = new Atributo("cha",
                "carga horária da disciplina",
                Atributo.REAL);

        Set<Atributo> atributos = new HashSet<>(2);
        atributos.add(nome);
        atributos.add(cha);

        final String NOME = "Aulas presenciais na graduação";
        final String DESCRICAO = "Aulas presenciais ministradas na graduação";

        Classe t = new Classe("APG", NOME, DESCRICAO, atributos);

        assertEquals("APG", t.getId());
        assertEquals(NOME, t.getNome());
        assertEquals(DESCRICAO, t.getDescricao());
        assertEquals(2, t.getAtributos().size());
    }

    @Test
    public void umGrupo() {
        Set<Atributo> atribs = new HashSet<>(1);
        atribs.add(new Atributo("c", "n", Atributo.REAL));

        Classe presencial = new Classe("presencial", "presencial", "presencial", atribs);
        Classe ead = new Classe("ead", "ead", "ead", atribs);

        Set<String> tipos = new HashSet<>();
        tipos.add("presencial");
        tipos.add("ead");

        Grupo ensino = new Grupo("ensino", "ensino", "ensino graduação", atribs, tipos);

        assertEquals(2, ensino.getTipos().size());
    }

    @Test
    public void grupoDevePossuirCodigo() {

        assertThrows(CampoExigidoNaoFornecido.class, () -> new Grupo(null, "a", "d", null, null));
    }

    @Test
    public void grupoDevePossuirPeloMenosUmAtributo() {
        Set<Atributo> atribs = new HashSet<>(1);

        assertThrows(CampoExigidoNaoFornecido.class, () -> new Grupo("c", "a", "d", atribs, null));
    }

    @Test
    public void grupoDevePossuirPeloMenosUmTipo() {
        Set<Atributo> atribs = new HashSet<>(1);
        atribs.add(new Atributo("n", "d", Atributo.REAL));

        Set<String> tipos = new HashSet<>(0);
        assertThrows(CampoExigidoNaoFornecido.class, () -> new Grupo("c", "a", "d", atribs, tipos));
    }
}

