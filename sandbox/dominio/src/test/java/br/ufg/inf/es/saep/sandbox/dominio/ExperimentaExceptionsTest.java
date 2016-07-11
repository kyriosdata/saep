package br.ufg.inf.es.saep.sandbox.dominio;

import org.junit.Test;

public class ExperimentaExceptionsTest {

    @Test(expected = IdentificadorExistente.class)
    public void justificativaNullGeraExcecao() {
        throw new IdentificadorExistente("x");
    }

    @Test(expected = TipoDeRegraInvalido.class)
    public void tipoDeRegraInvalidoGeraExcecao() {
        throw new TipoDeRegraInvalido("x");
    }

    @Test(expected = TipoDeAtributoInvalido.class)
    public void tipoDeAtributoInvalidoGeraExcecao() {
        throw new TipoDeAtributoInvalido("x");
    }

    @Test(expected = IdentificadorDesconhecido.class)
    public void identificadorDesconhecidoGeraExcecao() {
        throw new IdentificadorDesconhecido("x");
    }

    @Test(expected = ResolucaoUsaTipoException.class)
    public void removerTipoUsadoGeraExcecao() {
        throw new ResolucaoUsaTipoException("x");
    }

    @Test(expected = ExisteParecerReferenciandoRadoc.class)
    public void naoPodeRemoverRadocComParecerQueReferenciaEle() {
        throw new ExisteParecerReferenciandoRadoc("x");
    }
}

