package br.ufg.inf.es.saep.sandbox.dominio;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Interface de operações oferecidas por um serviço
 * que avalia regras.
 */
public interface AvaliaRegraService {

    /**
     * Avalia o conjunto de relatos fornecido.
     *
     * @param registros O conjunto de relatos a ser avaliado.
     *
     * @return Resultados (pontuações) obtidas pela avaliação
     * dos relatos.
     */
    Map<String,BigDecimal> avalia(Registros registros);

    /**
     * Avalia o conjunto de registros conforme a sentença
     * cujo código é fornecido.
     *
     * @param registros Conjunto de registros a ser avaliado.
     *
     * @param codigo Código único da sentença a ser utilizada
     *               na avaliação dos registros.
     *
     * @return Pontuação obtida pela avaliação.
     */
    float avalia(Registros registros, int codigo);
}
