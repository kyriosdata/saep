package br.ufg.inf.es.saep.sandbox.dominio;

/**
 * Abstração dos serviços de persistência de RADOC.
 *
 */
public interface RadocRepository {

    /**
     * Recupera o RADOC identificado pelo argumento.
     *
     * @param identificador O identificador único do
     *                      RADOC.
     *
     * @return O {@code Radoc} correspondente ao
     * identificador fornecido.
     */
    Radoc identificadoPor(String identificador);
}
