package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Serviço de avaliação automática de RADOC.
 */
public class AvaliadorService {

    private AvaliaRegraService regraService;

    public AvaliadorService(AvaliaRegraService regraService) {
        this.regraService = regraService;
    }

    /**
     * Realiza avaliação dos itens fornecidos.
     *
     * @param itens Sequência de itens a serem avaliados.
     *
     * @return Resultados produzidos pela avaliação.
     */
    public Map<String, Valor> avalia(List<ItemAvaliado> itens, List<Relato> relatos) {

        // Obtém itens na ordem em que devem ser avaliados.
        List<ItemAvaliado> ordenados = OrdenacaoService.ordena(itens);

        // Retém valores produzidos pela avaliação.
        Map<String, Valor> contexto = new HashMap<>();

        // Identifica grupos de relatos pelo tipo, ou seja,
        // agrupa todos os "libros sem corpo editorial", "ensino graduação",
        // e assim sucessivamente.
        Map<String, List<Relato>> relatosPorTipo = montaRelatosPorTipo(relatos);

        for (ItemAvaliado item : ordenados) {
            Regra regra = item.getRegra();

            // A avaliação da regra de um item pode depender dos
            // relatos correspondentes. Nesse caso, recupere-os.
            String tipo = item.getTipo();
            List<Relato> considerados = relatosPorTipo.get(tipo);

            // Avalie a regra, para o contexto disponível.
            Valor valor = regraService.avaliaRegra(regra, contexto, considerados);

            String variavel = item.getVariavel();
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
    private Map<String, List<Relato>> montaRelatosPorTipo(List<Relato> relatos) {
        Map<String, List<Relato>> relatosPorTipo = new HashMap<>();
        for (Relato relato : relatos) {
            String tipo = relato.getTipo();

            List<Relato> lista = relatosPorTipo.get(tipo);
            if (lista == null) {
                lista = new ArrayList<>();
                relatosPorTipo.put(tipo, lista);
            }

            lista.add(relato);
        }

        return relatosPorTipo;
    }

}
