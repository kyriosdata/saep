package br.ufg.inf.es.saep.sandbox.dominio;

import br.ufg.inf.es.saep.sandbox.infraestrutura.AvaliaRegraServiceEvalEx;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AvaliadorServiceTest {

    private static AvaliadorService as;

    @BeforeClass
    public static void setUpClass() {
        as = new AvaliadorService(new AvaliaRegraServiceEvalEx());
    }

    @Test
    public void semRegraParaAvaliarNenhumResultadoProduzido() {
        List<Regra> regras = new ArrayList<>();
        List<Relato> relatos = new ArrayList<>();
        Map<String, Valor> resultado = as.avalia(regras, relatos, null, null);

        assertEquals(0, resultado.size());
    }
}

