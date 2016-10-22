package br.ufg.inf.es.saep.sandbox.infraestrutura;

import br.ufg.inf.es.saep.sandbox.dominio.Pontuacao;
import br.ufg.inf.es.saep.sandbox.dominio.Valor;
import br.ufg.inf.es.saep.sandbox.dominio.infraestrutura.Serializador;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SerializacaoTest {

    private static Serializador sz;

    @BeforeAll
    public static void setUpClass() {
        sz = new Serializador();
    }

    @Test
    public void serializarValor() {
        verificaValor(new Valor(LocalDate.now()));
        verificaValor(new Valor(3.14f));
        verificaValor(new Valor(true));
        verificaValor(new Valor(false));
        verificaValor(new Valor("casa"));
    }

    private void verificaValor(Valor valor) {
        Valor recuperado = sz.valor(sz.toJson(valor));
        assertEquals(valor, recuperado);
    }

    @Test
    public void serializarPontuacao() {
        verificaPontuacao(new Pontuacao("a", new Valor(false)));
    }

    public void verificaPontuacao(Pontuacao pontuacao) {
        Pontuacao recuperado = sz.pontuacao(sz.toJson(pontuacao));
        assertEquals(pontuacao, recuperado);
    }
}
