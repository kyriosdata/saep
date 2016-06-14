package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.Map;

/**
 * Interface a ser implementada por qualquer classe cujas
 * instâncias serão empregadas para a avaliação de regras
 * de progressão, promoção ou estágio probatório na UFG.
 * <p>
 * O objeto a ser empregada para avaliação deve possuir
 * construtor padrão (sem argumento) e, na sequência,
 * deverá receber a mensagem {@link #defineRegras(Regras)}
 * por meio do qual as regras a serem empregadas são
 * fornecidas. Isso cria uma oportunidade de "aquecimento"
 * ou preparação antes da execução de avaliações,
 * ocorrida por meio de mensagens enviadas pelo método
 * {@link #avalia(Relatos)}.
 * <p>
 * O resultado é uma coleção de "pontuações", valores
 * associados a sequências de caracteres, onde cada uma
 * delas identifica um resultado relevante a ser
 * considerado em uma avaliação.
 * <p>
 * Observe que a implementação dessa interface não produz um
 * "relatório", mas os valores que serão empregados na
 * produção de um relatório para uma avaliação.
 *
 * @see Relatos
 * @see Regras
 */
public interface AvaliaRegraService {

    /**
     * Avalia o conjunto de relatos fornecido.
     * <p>
     * A avaliação dos relatos produz valores, um para cada
     * um dos identificadores de regras, ou variáveis nas
     * quais os resultados são depositados.
     * <p>
     * Isso significa que os identificadores de resultados
     * devem ser únicos.
     *
     * @param relatos O conjunto de relatos a ser avaliado.
     * @return Resultados (pontuações) obtidas pela avaliação
     * dos relatos.
     * @see #defineRegras(Regras)
     */
    Map<String, Valor> avalia(Relatos relatos);

    /**
     * Define o conjunto de regras sobre as quais uma avaliação
     * é realizada.
     *
     * @param regras O conjunto de regras.
     * @see #avalia(Relatos)
     */
    void defineRegras(Regras regras);
}
