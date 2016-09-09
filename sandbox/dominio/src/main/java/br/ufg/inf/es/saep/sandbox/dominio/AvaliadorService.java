/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Serviço de avaliação de RADOCs.
 */
public class AvaliadorService {

    private List<Nota> notas;

    /**
     * Serviço de avaliação de regra a ser utilizado peloa Avaliador.
     */
    private final AvaliaRegraService regraService;

    public AvaliadorService(AvaliaRegraService regraService) {
        this.regraService = regraService;
    }

    /**
     * Realiza avaliação dos itens fornecidos.
     *
     * @param regras Sequência de regras a serem avaliadas. Possivelmente
     *               a ordem em que são fornecidas não é a ordem esperada
     *               ou correta de execução. Ou seja, dependências entre
     *               as regras não necessariamente são contempladas nesse
     *               parâmetro.
     *
     * @param relatos Conjunto de relatos sobre os quais a avaliação
     *                das regras será executada.
     *
     * @param substitutos Conjunto de pontuações que fornecem valores
     *                   "substitutos".
     *
     * @param parametros Conjunto de valores inicialis, possivelmente
     *                 empregados para definição de constantes.
     *
     * @return Resultados produzidos pela avaliação. Cada regra dá origem
     *      a um valor quando avaliada, o valor é associado ao nome da
     *      variável da regra e retornado.
     */
    public Map<String, Valor> avalia(
            List<Regra> regras,
            List<Relato> relatos,
            Map<String, Valor> substitutos,
            Map<String, Valor> parametros) {

        // Acumula valores produzidos pela avaliação.
        Map<String, Valor> resultados = new HashMap<>();

        // Parâmetros fornecidos devem estar disponíveis na avaliação
        if (parametros != null) {
            // Valores iniciais devem estar disponíveis
            for (String key : parametros.keySet()) {
                resultados.put(key, parametros.get(key));
            }
        }

        // Obtém itens na ordem em que devem ser avaliados.
        List<Regra> ordenadas = OrdenacaoService.ordena(regras);

        // Agrupa relatos por tipo.
        Map<String, List<Avaliavel>> relatosPorTipo = montaRelatosPorTipo(relatos);

        for (Regra regra : ordenadas) {

            // A avaliação da regra de um item pode depender dos
            // relatos correspondentes. Nesse caso, recupere-os.
            String tipo = "tipo"; // era ... regra.getTipoRelato();
            List<Avaliavel> relatosRelevantes = relatosPorTipo.get(tipo);

            // Avalie a regra, para o contexto disponível.
            Valor valor = regraService.avalia(regra, resultados, relatosRelevantes);

            // Valor produzido deve ser adicionado ao contexto.
            String variavel = regra.getVariavel();

            // Contudo, pode ser que uma "substituição" deva prevalecer
            // Ou seja, o valor produzido deve ceder para o fornecido.
            if (substitutos.containsKey(variavel)) {
                valor = substitutos.get(variavel);
            }

            // Acrescenta o valor calculado (ou o que deve prevalecer)
            // ao conjunto de resultados (contexto a ser utilizado).
            resultados.put(variavel, valor);
        }

        return resultados;
    }

    /**
     * Dado um conjunto de relatos, agrupa-os por tipo.
     *
     * @param relatos Conjunto de relatos.
     *
     * @return Dicionário que reúne os relatos fornecidos pelos tipos
     *      correspondentes.
     */
    private Map<String, List<Avaliavel>> montaRelatosPorTipo(List<Relato> relatos) {
        Map<String, List<Avaliavel>> relatosPorTipo = new HashMap<>();
        for (Relato relato : relatos) {
            String tipo = relato.getClasse();

            List<Avaliavel> lista = relatosPorTipo.get(tipo);
            if (lista == null) {
                lista = new ArrayList<>();
                relatosPorTipo.put(tipo, lista);
            }

            lista.add(relato);
        }

        return relatosPorTipo;
    }

    /**
     * Adiciona nota ao parecer.
     *
     * <p>Caso a nota a ser acrescentada
     * se refira a um item {@link Avaliavel} para o qual já
     * exista uma nota, então esse nota existente é
     * substituída por aquela fornecida. Caso contrário, a
     * nota é simplesmente acrescentada.
     *
     * <p>A adição de uma nota possivelmente altera o
     * conjunto de pontuações do parecer, dado que o valor de
     * um relato é substituído por outro, ou até mesmo o valor
     * de uma pontuação. Esse método não atualiza a pontuação
     * de um parecer.
     *
     * @param nota A nota a ser acrescentada ao parecer.
     *
     * @throws CampoExigidoNaoFornecido Caso a nota
     *      seja {@code null}.
     */
    public void adicionaNota(Nota nota) {
        if (nota == null) {
            throw new CampoExigidoNaoFornecido("nota");
        }

        if (notas == null) {
            notas = new ArrayList<>(1);
            notas.add(nota);
            return;
        }

        for(Nota n : notas) {
            Avaliavel original = n.getItemOriginal();
            Avaliavel novo = nota.getItemOriginal();
            if (original.equals(novo)) {
                notas.remove(n);
                notas.add(nota);
                return;
            }
        }

        notas.add(nota);
    }
}
