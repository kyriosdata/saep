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
 * Serviço de avaliação automática de RADOC.
 */
public class AvaliadorService {

    private List<Nota> notas;

    private String fundamentacao;

    private AvaliaRegraService regraService;

    public AvaliadorService(AvaliaRegraService regraService) {
        this.regraService = regraService;
    }

    /**
     * Realiza avaliação dos itens fornecidos.
     *
     * @param regras Sequência de regras a serem avaliadas.
     *
     * @param relatos Conjunto de relatos sobre os quais a avaliação
     *                das regras será executada.
     *
     * @return Resultados produzidos pela avaliação.
     */
    public Map<String, Valor> avalia(List<Regra> regras, List<Relato> relatos) {

        // Obtém itens na ordem em que devem ser avaliados.
        List<Regra> ordenados = OrdenacaoService.ordena(regras);

        // Retém valores produzidos pela avaliação.
        Map<String, Valor> contexto = new HashMap<>();

        // Identifica grupos de relatos pelo tipo, ou seja,
        // agrupa todos os "libros sem corpo editorial", "ensino graduação",
        // e assim sucessivamente.
        Map<String, List<Avaliavel>> relatosPorTipo = montaRelatosPorTipo(relatos);

        for (Regra regra : ordenados) {
            //Regra regra = item.getRegra();

            // A avaliação da regra de um item pode depender dos
            // relatos correspondentes. Nesse caso, recupere-os.
            String tipo = regra.getTipoRelato();
            List<Avaliavel> considerados = relatosPorTipo.get(tipo);

            // Avalie a regra, para o contexto disponível.
            Valor valor = regraService.avaliaRegra(regra, contexto, considerados);

            String variavel = regra.getVariavel();
            contexto.put(variavel, valor);
        }

        return contexto;
    }

    /**
     * Dado um conjunto de relatos, agrupa-os por tipo.
     *
     * @param relatos Conjunto de relatos.
     *
     * @return Dicionário que reúne os relatos fornecidos pelos tipos
     * correspondentes.
     */
    private Map<String, List<Avaliavel>> montaRelatosPorTipo(List<Relato> relatos) {
        Map<String, List<Avaliavel>> relatosPorTipo = new HashMap<>();
        for (Relato relato : relatos) {
            String tipo = relato.getTipo();

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
     * @throws CampoExigidoNaoFornecido Caso a nota
     * seja {@code null}.
     *
     * @param nota A nota a ser acrescentada ao
     * pareder.
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

    /**
     * Altera a fundamentação do parecer.
     *
     * <p>Fundamentação é o texto propriamente dito do
     * parecer. Não confunda com as alterações de
     * valores (dados de relatos ou de pontuações).
     *
     * @throws IdentificadorDesconhecido Caso o identificador
     * fornecido não identifique um parecer.
     *
     * @param parecer O identificador único do parecer.
     * @param fundamentacao Novo texto da fundamentação do parecer.
     */
    void atualizaFundamentacao(String parecer, String fundamentacao){}

    /**
     * Remove a nota cujo item {@link Avaliavel} original é
     * fornedido.
     *
     * @param id O identificador único do parecer.
     * @param original Instância de {@link Avaliavel} que participa
     *                 da {@link Nota} a ser removida como origem.
     *
     */
    void removeNota(String id, Avaliavel original) {}
}
