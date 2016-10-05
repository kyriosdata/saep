package br.ufg.inf.es.saep.sandbox.dominio;

import br.ufg.inf.es.saep.sandbox.dominio.excecoes.CampoExigidoNaoFornecido;
import br.ufg.inf.es.saep.sandbox.dominio.excecoes.TipoDeAtributoInvalido;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AtributoTest {

    @Test
    public void situacoesExcepcionais() {

        assertThrows(CampoExigidoNaoFornecido.class, () -> new Atributo(null, "descricao", Atributo.STRING));
        assertThrows(TipoDeAtributoInvalido.class, () -> new Atributo("d", "d", -2));
        assertThrows(CampoExigidoNaoFornecido.class, () -> new Atributo("", "descricao", Atributo.LOGICO));
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

