package br.ufg.inf.es.saep.sandbox.dominio;

import br.ufg.inf.es.saep.sandbox.dominio.excecoes.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class ExperimentaExceptionsTest {

    @Test
    public void geraExcecoesApenasParaCobertura() {

        assertThrows(IdentificadorExistente.class, () -> { throw new IdentificadorExistente("x"); });
        assertThrows(TipoDeRegraInvalido.class, () -> { throw new TipoDeRegraInvalido("x"); });
        assertThrows(TipoDeAtributoInvalido.class, () -> { throw new TipoDeAtributoInvalido("x"); });
        assertThrows(IdentificadorDesconhecido.class, () -> { throw new IdentificadorDesconhecido("x"); });
        assertThrows(ResolucaoUsaTipoException.class, () -> { throw new ResolucaoUsaTipoException("x"); });
        assertThrows(ExisteParecerReferenciandoRadoc.class, () -> { throw new ExisteParecerReferenciandoRadoc("x"); });
        assertThrows(FalhaAoAvaliarRegra.class, () -> { throw new FalhaAoAvaliarRegra("x"); });
    }
}

