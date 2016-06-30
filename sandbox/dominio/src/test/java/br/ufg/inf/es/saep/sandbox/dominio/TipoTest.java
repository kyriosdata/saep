package br.ufg.inf.es.saep.sandbox.dominio;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TipoTest {

    @Test(expected = CampoExigidoNaoFornecidoException.class)
    public void tipoDevePossuirIdentificadorNaoNull() {
        new Tipo(null, "n", "d", null);
    }

    @Test(expected = CampoExigidoNaoFornecidoException.class)
    public void tipoDevePossuirIdentificadorNaoVazio() {
        new Tipo("", "n", "d", null);
    }

    @Test(expected = CampoExigidoNaoFornecidoException.class)
    public void tipoDevePossuirAtributos() {
        new Tipo("c", "n", "d", null);
    }

    @Test(expected = CampoExigidoNaoFornecidoException.class)
    public void tipoDevePossuirPeloMenosUmAtributo() {
        Set<Atributo> atrs = new HashSet<>(0);
        new Tipo("c", "n", "d", atrs);
    }

    @Test
    public void testesParaCasosDeIgualdade() {
        Set<Atributo> atrs = new HashSet<>(1);
        atrs.add(new Atributo("a", "d", Atributo.REAL));

        Tipo t1 = new Tipo("c", "n", "d", atrs);

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

        Tipo t1 = new Tipo("c", "n", "d", atrs);
        Tipo t2 = new Tipo("c", "N", "D", atrs);

        Set<Tipo> tipos = new HashSet<>(2);
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

        Set<Atributo> atributos = new HashSet<Atributo>(2);
        atributos.add(nome);
        atributos.add(cha);

        final String NOME = "Aulas presenciais na graduação";
        final String DESCRICAO = "Aulas presenciais ministradas na graduação";

        Tipo t = new Tipo("APG", NOME, DESCRICAO, atributos);

        assertEquals("APG", t.getCodigo());
        assertEquals(NOME, t.getNome());
        assertEquals(DESCRICAO, t.getDescricao());
        assertEquals(2, t.getAtributos().size());
    }

    @Test
    public void umGrupo() {
        Set<Atributo> atribs = new HashSet<>(1);
        atribs.add(new Atributo("c", "n", Atributo.REAL));

        Tipo presencial = new Tipo("presencial", "presencial", "presencial", atribs);
        Tipo ead = new Tipo("ead", "ead", "ead", atribs);

        Set<Tipo> tipos = new HashSet<Tipo>();
        tipos.add(presencial);
        tipos.add(ead);

        Grupo ensino = new Grupo("ensino", "ensino", "ensino graduação", atribs, tipos);

        assertEquals(2, ensino.getTipos().size());
    }

    @Test(expected = CampoExigidoNaoFornecidoException.class)
    public void grupoDevePossuirCodigo() {
        new Grupo(null, "a", "d", null, null);
    }

    @Test(expected = CampoExigidoNaoFornecidoException.class)
    public void grupoDevePossuirPeloMenosUmAtributo() {
        Set<Atributo> atribs = new HashSet<>(1);

        new Grupo("c", "a", "d", atribs, null);
    }

    @Test(expected = CampoExigidoNaoFornecidoException.class)
    public void grupoDevePossuirPeloMenosUmTipo() {
        Set<Atributo> atribs = new HashSet<>(1);
        atribs.add(new Atributo("n", "d", Atributo.REAL));

        Set<Tipo> tipos = new HashSet<>(0);
        new Grupo("c", "a", "d", atribs, tipos);
    }
}

