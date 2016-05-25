package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Fornece ordenação topológica a ser seguida
 * para execução das regras.
 *
 */
public class OrdenacaoItemAvaliado {

    /**
     * Ordena topologicamente os itens avaliados.
     *
     * @param itens Itens a serem ordenadas.
     *
     * @return Sequência de itens a serem executadas
     * nessa ordem.
     */
    public List<ItemAvaliado> ordena(List<ItemAvaliado> itens) {
        List<ItemAvaliado> ordenados = new ArrayList<ItemAvaliado>(itens.size());

        // Um item dá origem a um resultado identificado
        // pelo nome do atributo. Precisamos identificar,
        // no sentido inverso, o item cujo resultado é
        // identificado por um dado nome.
        int size = itens.size();
        Map<String, ItemAvaliado> itemPorNome = new HashMap<String, ItemAvaliado>(size);
        for(ItemAvaliado item : itens) {
            itemPorNome.put(item.getAtributo().getNome(), item);
        }

        // TODAS OS ITENS SERÃO INSERIDOS
        for (ItemAvaliado item : itens) {
            insereItem(item, itemPorNome, ordenados);
        }

        return itens;
    }

    private void insereItem(ItemAvaliado item,
                            Map<String, ItemAvaliado> itemPorNome,
                            List<ItemAvaliado> ordenados) {

        // Assegura ordenação correta, primeiro
        // componentes são inseridos
        for(Atributo atributo : item.getRegra().getAtributos()) {
            String nome = atributo.getNome();

            // Nome não necessariamente é "resultado" de um item.
            // Pode ser, por exemplo, propriedade de um relato.
            if (itemPorNome.containsKey(nome)) {
                insereItem(itemPorNome.get(nome), itemPorNome, ordenados);
            }
        }

        // Insere item após componentes
        ordenados.add(item);
    }
}
