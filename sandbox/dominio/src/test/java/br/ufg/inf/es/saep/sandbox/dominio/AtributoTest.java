package br.ufg.inf.es.saep.sandbox.dominio;

import com.sun.javaws.exceptions.InvalidArgumentException;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class AtributoTest {

    @Test(expected = IllegalArgumentException.class)
    public void nomeNullGeraExcecao() {
        new Atributo(null, "descricao", Atributo.STRING);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nomeVazioGeraExcecao() {
        new Atributo("", "descricao", Atributo.LOGICO);
    }

    @Test
    public void atributoRecuperadoConformeDefinido() {
        Atributo a = new Atributo("nome", "descricao", Atributo.STRING);
        assertEquals("nome", a.getNome());
        assertEquals("descricao", a.getDescricao());
        assertEquals(Atributo.STRING, a.getTipo());
    }

    @Test
    public void atributosIguaisHashCodeIguais() {
        Atributo a = new Atributo("a", "d", Atributo.REAL);
        Atributo b = new Atributo("a", "d", Atributo.REAL);

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

}

