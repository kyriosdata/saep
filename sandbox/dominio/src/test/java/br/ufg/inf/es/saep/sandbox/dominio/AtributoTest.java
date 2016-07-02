package br.ufg.inf.es.saep.sandbox.dominio;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AtributoTest {

    @Test(expected = CampoExigidoNaoFornecido.class)
    public void nomeNullGeraExcecao() {
        new Atributo(null, "descricao", Atributo.STRING);
    }

    @Test(expected = TipoDeAtributoInvalido.class)
    public void tipoInvalidoGeraExcecao() {
        new Atributo("d", "d", -2);
    }

    @Test(expected = CampoExigidoNaoFornecido.class)
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

        assertEquals(a, a);
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    public void atributosDistintos() {
        Atributo a = new Atributo("a", "d", Atributo.REAL);

        assertNotEquals(a, null);
        assertNotEquals(a, "String e diferente de Atributo");
    }

}

