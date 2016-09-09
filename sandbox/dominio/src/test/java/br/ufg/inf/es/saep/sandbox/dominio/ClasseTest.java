package br.ufg.inf.es.saep.sandbox.dominio;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ClasseTest {

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void tipoDevePossuirIdentificadorNaoNull() {
        new Classe(null, "n", "d", null);
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void tipoDevePossuirIdentificadorNaoVazio() {
        new Classe("", "n", "d", null);
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void tipoDevePossuirAtributos() {
        new Classe("c", "n", "d", null);
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void tipoDevePossuirPeloMenosUmAtributo() {
        Set<Atributo> atrs = new HashSet<>(0);
        new Classe("c", "n", "d", atrs);
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void nomeDoTipoNaoPodeSerNull() {
        Atributo atributo = new Atributo("a", "d", 1);
        Set<Atributo> atrs = new HashSet<>(0);
        atrs.add(atributo);
        new Classe("c", null, "d", atrs);
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

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void grupoDevePossuirCodigo() {
        new Grupo(null, "a", "d", null, null);
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void grupoDevePossuirPeloMenosUmAtributo() {
        Set<Atributo> atribs = new HashSet<>(1);

        new Grupo("c", "a", "d", atribs, null);
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void grupoDevePossuirPeloMenosUmTipo() {
        Set<Atributo> atribs = new HashSet<>(1);
        atribs.add(new Atributo("n", "d", Atributo.REAL));

        Set<String> tipos = new HashSet<>(0);
        new Grupo("c", "a", "d", atribs, tipos);
    }
}

