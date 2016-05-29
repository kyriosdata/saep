package br.ufg.inf.es.saep.sandbox.dominio;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class TipoTest {

    @Test
    public void aulaPresencialNaGraduacao() {

        // O nome da disciplina
        Atributo nome = new Atributo("nome",
                "nome da disciplina",
                TipoPrimitivo.STRING);

        // A carga horária da disciplina
        Atributo cha = new Atributo("cha",
                "carga horária da disciplina",
                TipoPrimitivo.REAL);

        Set<Atributo> atributos = new HashSet<Atributo>(2);
        atributos.add(nome);
        atributos.add(cha);

        Tipo t = new Tipo("Aulas presenciais na graduação",
                "APG",
                "Aulas presenciais ministradas na graduação",
                atributos);

        assertEquals("Aulas presenciais na graduação", t.getNome());
        assertEquals("APG", t.getCodigo());
        assertEquals(2, t.getAtributos().size());
    }

    @Test
    public void umGrupo() {
        Tipo presencial = new Tipo("presencial", "presencial", "presencial", null);
        Tipo ead = new Tipo("ead", "ead", "ead", null);

        Set<Tipo> tipos = new HashSet<Tipo>();
        tipos.add(presencial);
        tipos.add(ead);

        Grupo ensino = new Grupo("ensino", "ensino", "ensino graduação", null, tipos);

        assertEquals(2, ensino.getTipos().size());
    }
}

