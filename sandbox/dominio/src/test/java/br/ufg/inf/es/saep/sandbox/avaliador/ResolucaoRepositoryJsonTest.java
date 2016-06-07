package br.ufg.inf.es.saep.sandbox.avaliador;

import br.ufg.inf.es.saep.sandbox.dominio.Resolucao;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ResolucaoRepositoryJsonTest {

    private String getResourcesDir() {
        return "src/test/resources/";
    }

    @Test
    public void recuperacaoArquivoTrivial() {
        ResolucaoRepositoryJson repo = new ResolucaoRepositoryJson();
        String identificador = getResourcesDir() + "resolucao.json";
        Resolucao res = repo.identificadaPor(identificador);
        assertNotNull(res);

        System.out.println(res.getDataAprovacao());
        System.out.println(res.getIdentificador());
    }
}
