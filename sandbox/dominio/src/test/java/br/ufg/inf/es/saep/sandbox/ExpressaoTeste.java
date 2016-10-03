package br.ufg.inf.es.saep.sandbox;

import br.ufg.inf.es.saep.sandbox.dominio.regra.Expressao;

import java.util.Map;

public class ExpressaoTeste implements Expressao {

    private float valorRetorno;

    public void setValorRetorno(float v) {
        valorRetorno = v;
    }

    @Override
    public float valor() {
        return valorRetorno;
    }

    @Override
    public float valor(Map<String, Float> contexto) {
        return valorRetorno;
    }
}

