package br.ufg.inf.es.saep.sandbox.dominio;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class TipoTest {

    @Test(expected = CampoObrigatorioNaoFornecidoException.class)
    public void tipoDevePossuirIdentificadorNaoNull() {
        new Tipo(null, "n", "d", null);
    }

    @Test(expected = CampoObrigatorioNaoFornecidoException.class)
    public void tipoDevePossuirIdentificadorNaoVazio() {
        new Tipo("", "n", "d", null);
    }

    @Test(expected = CampoObrigatorioNaoFornecidoException.class)
    public void tipoDevePossuirAtributos() {
        new Tipo("c", "n", "d", null);
    }

    @Test(expected = CampoObrigatorioNaoFornecidoException.class)
    public void tipoDevePossuirPeloMenosUmAtributo() {
        Set<Atributo> atrs = new HashSet<>(0);
        new Tipo("c", "n", "d", atrs);
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
}

