package br.ufg.inf.es.saep.sandbox.dominio;

import java.util.List;

/**
 * Regra que representa valor condicional.
 */
public class CondicionalRegra extends Regra {
    private Expressao entao;
    private Expressao senao;

    public CondicionalRegra(Expressao expressao, double valorMaximo, double valorMinimo, List<Atributo> atributos) {
        super(expressao, valorMaximo, valorMinimo, atributos);
    }
}
