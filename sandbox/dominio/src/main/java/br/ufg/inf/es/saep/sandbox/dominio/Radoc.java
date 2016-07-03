/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.ArrayList;
import java.util.List;

/**
 * Conjunto de relatos associados a um docente em um dado ano base.
 *
 * <p>Eventualmente um mesmo docente, em um dado ano, pode possuir
 * mais de um RADOC, decorrente, por exemplo, da correção de alguma
 * informação.
 *
 * <p>Convém destacar que um RADOC pode ser referenciado por mais
 * de um parecer.
 *
 * @see Relato
 * @see Parecer
 *
 */
public class Radoc {

    /**
     * Identificador único do relatório.
     */
    private String id;

    /**
     * Ano base do relatório.
     */
    private int anoBase;

    /**
     * Relatos associados ao RADOC.
     * Um RADOC pode possuir zero ou mais
     * relatos.
     */
    private List<Relato> relatos;

    /**
     * Cria um relatório byId relatos.
     *
     * @param id O identificador único do RADOC.
     * @param anoBase O ano base do RADOC.
     * @param relatos Conjunto de relatos que fazem parte do RADOC.
     */
    public Radoc(String id, int anoBase, List<Relato> relatos) {
        if (id == null || id.isEmpty()) {
            throw new CampoExigidoNaoFornecido("id");
        }

        if (relatos == null) {
            throw new CampoExigidoNaoFornecido("relatos");
        }

        this.id = id;
        this.anoBase = anoBase;
        this.relatos = relatos;
    }

    /**
     * Identifica, dentre os relatos do RADOC, aqueles
     * de um dado tipo.
     *
     * @param tipo O tipo de relato.
     * @return Conjunto de relatos do tipo indicado.
     */
    public List<Relato> relatosPorTipo(String tipo) {

        List<Relato> procurados = new ArrayList<>();

        for(Relato relato : relatos) {
            if (relato.getTipo().equals(tipo)) {
                procurados.add(relato);
            }
        }

        return procurados;
    }

    /**
     * Recupera o identificador único do RADOC.
     *
     * @return O identificador único do RADOC.
     */
    public String getId() {
        return id;
    }

    /**
     * Recupera o ano base do RADOC.
     *
     * @return O ano base do RADOC.
     */
    public int getAnoBase() {
        return anoBase;
    }

    /**
     * Recupera os relatos que fazem parte do RADOC.
     *
     * @return O conjunto de relatos do RADOC.
     */
    public List<Relato> getRelatos() {
        return relatos;
    }
}
