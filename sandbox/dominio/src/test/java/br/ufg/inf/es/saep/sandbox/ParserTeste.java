package br.ufg.inf.es.saep.sandbox;

import br.ufg.inf.es.saep.sandbox.dominio.regra.Expressao;

import java.util.List;

public class ParserTeste implements br.ufg.inf.es.saep.sandbox.dominio.regra.Parser {

    private Expressao expressaoRetorno;
    private List<String> dependencias;

    public void setExpressao(Expressao exprRetorno) {
        expressaoRetorno = exprRetorno;
    }

    public void setDependencias(List<String> deps) {
        dependencias = deps;
    }

    @Override
    public Expressao ast(String sentenca) {
        return expressaoRetorno;
    }

    @Override
    public List<String> dependencias(String sentenca) {
        return dependencias;
    }
}
