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

    /**
     * Realiza avaliação dos itens, na ordem em que são fornecidos.
     *
     * @param itens Sequência de itens a serem avaliados.
     *
     * @return Resultados produzidos pela avaliação.
     */
    public Map<String, Valor> avalia(List<ItemAvaliado> itens, List<Relato> relatos) {
        Map<String, Valor> contexto = new HashMap<>();

        for (ItemAvaliado item : itens) {
            Regra regra = item.getRegra();

            // Identifica grupos de relato por tipo
            Map<String, List<Relato>> relatosPorTipo = new HashMap<>();
            for (Relato relato : relatos) {
                String tipo = relato.getTipo();

                List<Relato> lista = relatosPorTipo.get(tipo);
                if (lista == null) {
                    lista = new ArrayList<>();
                }

                lista.add(relato);
            }

            // A avaliação da regra de um item pode depender dos
            // relatos correspondentes. Nesse caso, recupere-os.
            String tipo = item.getTipo();
            List<Relato> considerados = relatosPorTipo.get(tipo);

            // Avalie a regra, para o contexto disponível.
            Valor valor = regraService.avaliaRegra(regra, contexto, considerados);

            String variavel = item.getResultado();
            contexto.put(variavel, valor);
        }

        return contexto;
    }

}
