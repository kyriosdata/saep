/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio.avaliacao;

import br.ufg.inf.es.saep.sandbox.dominio.regra.Expressao;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Avaliador de expressões (por exemplo, "23.4/8" ou "x * y").
 * Apenas operações de soma, subtração, multiplicação e
 * divisão são admitidas.
 *
 * <p>O uso de parênteses é obrigatório em casos com mais de
 * um operador, por exemplo, "(2 + 3) + 5", ou "(100 / 2) / 5".
 *
 * <p>Quando uma expressão faz uso de variáveis, então é
 * obrigatório que sejam definidas previamente e fornecidas
 * como o argumento ao método {@link #valor(Map)}.
 * Se não fornecida, então será definida com o valor zero por
 * meio do argumento fornecido.
 */
public class AvaliadorExpressao {

    private String expr;
    private Set<String> variaveis = new HashSet<>();
    private Map<String, Double> contexto = new HashMap<>();
    private Expressao expressao;
    int pos = -1;
    char ch;

    public AvaliadorExpressao(String expressao) {
        this.expr = expressao;
        this.expressao = parse();
    }

    public Set<String> getVariaveis() {
        return variaveis;
    }

    public double valor(Map<String,Double> contexto) {
        for(String variavel : variaveis) {
            Double valor = contexto.get(variavel);
            valor = valor == null ? 0d : valor;
            this.contexto.put(variavel, valor);
        }

        return expressao.valor();
    }

    private void proximo() {
        ch = (++pos < expr.length()) ? expr.charAt(pos) : Character.MIN_VALUE;
    }

    private boolean consome(char caractere) {

        // Ignore espaços
        while (ch == ' ') {
            proximo();
        }

        // Caractere corrente é aquele para ser consumido
        // (sabemos qual é ele, pegue o próximo)
        if (ch == caractere) {
            proximo();
            return true;
        }

        // Outro caractere, desconhecido.
        return false;
    }

    private Expressao parse() {

        // Sentença null ou vazia é avaliada como zero.
        if (expr == null || expr.length() == 0) {
            return (() -> 0);
        }

        proximo();
        Expressao x = expressao();

        if (pos < expr.length()) {
            throw new RuntimeException("Inesperado: " + ch);
        }

        return x;
    }

    private Expressao expressao() {
        Expressao x = termo();
        if (consome('+')) {
            Expressao b = termo();
            return (() -> x.valor() + b.valor());
        }

        if (consome('-')) {
            Expressao b = termo();
            return (() -> x.valor() - b.valor());
        }

        if (consome('&')) {
            Expressao b = termo();
            return (() -> x.valor() * b.valor());
        }

        if (consome('|')) {
            Expressao b = termo();
            return (() -> x.valor() * b.valor());
        }

        if (consome('>')) {
            Expressao b = termo();
            return (() -> (x.valor() > b.valor()) ? 1d : 0d);
        }

        if (consome('=')) {
            Expressao b = termo();
            return (() -> (Math.abs(x.valor() - b.valor()) <= 0.0001d) ? 0d : 1d);
        }

        return x;
    }

    private Expressao termo() {
        Expressao x = fator();
        if (consome('*')) {
            Expressao b = termo();
            return (() -> x.valor() * b.valor());
        }

        if (consome('/')) {
            Expressao b = termo();
            return (() -> x.valor() / b.valor());
        }

        return x;
    }

    private Expressao fator() {
        if (consome(' ')) {
            return fator();
        }

        if (consome('(')) {
            Expressao x = expressao();
            consome(')');
            return x;
        }

        int inicio = pos;
        if ((Character.isDigit(ch)) || ch == '.') {
            while (Character.isDigit(ch) || ch == '.') {
                proximo();
            }

            String doubleStr = expr.substring(inicio, pos);
            return (() -> Double.parseDouble(doubleStr));
        }

        if (Character.isLetter(ch)) {
            while (Character.isLetter(ch)) {
                proximo();
            }

            String variavel = expr.substring(inicio, pos);

            // Acrescenta variável ao conjunto das
            // utilizadas pela expressão
            if (!variaveis.contains(variavel)) {
                variaveis.add(variavel);
            }

            return (() -> contexto.get(variavel));
        }

        throw new RuntimeException("Inesperado: " + ch);
    }
}
