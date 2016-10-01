package br.ufg.inf.es.saep.sandbox.infraestrutura;

import br.ufg.inf.es.saep.sandbox.dominio.regra.Expressao;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Analisador Sintático Descendente Recursivo.
 * <p>
 * Expr ::= Constante | Variavel | ( Expr Operador Expr )
 */
public class RecursiveDescetParserTest {

    @Test
    public void expressoesConstantes() {
        assertEquals(2.34, new Parser(" 2.34    ").expressao().valor(), 0.00001d);
        assertEquals(-4, new Parser("   -4").expressao().valor(), 0.00001d);
        assertEquals(1, new Parser("1").expressao().valor(), 0.00001d);
        assertEquals(0, new Parser("0").expressao().valor(), 0.00001d);
    }

    @Test
    public void expressoesVariaveis() {
        Map<String,Double> ctx = new HashMap<>(2);
        ctx.put("x", 9.876d);
        ctx.put("CaSa", 8.765d);

        assertEquals(0, new Parser("x").expressao().valor(), 0.00001d);
        assertEquals(0, new Parser("CaSa").expressao().valor(), 0.00001d);

        assertEquals(9.876d, new Parser("   x").expressao().valor(ctx), 0.00001d);
        assertEquals(8.765d, new Parser("   CaSa").expressao().valor(ctx), 0.00001d);
    }
}

class Parser {

    private int corrente = 0;
    private char caractere = ' ';
    private String expr;
    private int ultimaPosicao;

    public Parser(String expr) {
        this.expr = expr;
        ultimaPosicao = expr.length() - 1;
    }

    public Expressao expressao() {
        eliminaBrancos();

        if (isConstante()) {
            return new Constante(getConstante());
        }

        if (isLetra()) {
            return new Variavel(getVariavel());
        }

        throw new IllegalArgumentException("Nao esperado: " + caractere);
    }

    private boolean isConstante() {
        return caractere == '-' || Character.isDigit(caractere);
    }

    private boolean isLetra() {
        return Character.isLetter(caractere);
    }

    private double getConstante() {
        int sinal = 1;
        if (caractere == '-') {
            caractere = expr.charAt(++corrente);
            sinal = -1;
        }

        int inicio = corrente;
        if ((Character.isDigit(caractere)) || caractere == '.') {
            while (Character.isDigit(caractere) || caractere == '.') {
                if (corrente == ultimaPosicao) {
                    break;
                }

                caractere = expr.charAt(++corrente);
            }

            String doubleStr = expr.substring(inicio, corrente + 1);
            return sinal * Double.parseDouble(doubleStr);
        }

        throw new IllegalArgumentException("constante nao obtida");
    }

    private String getVariavel() {
        int inicio = corrente;

        while (isLetra()) {
            if (corrente == ultimaPosicao) {
                break;
            }

            caractere = expr.charAt(++corrente);
        }

        return expr.substring(inicio, corrente + 1);
    }

    private void eliminaBrancos() {
        caractere = expr.charAt(corrente);
        while (caractere == ' ' || caractere == '\t') {
            caractere = expr.charAt(++corrente);
        }
    }

}

class Sentenca implements Expressao {

    @Override
    public double valor() {
        return 0;
    }

    @Override
    public double valor(Map<String, Double> contexto) {
        return 0;
    }
}

class Constante implements Expressao {
    private double constante;

    public Constante(double valor) {
        constante = valor;
    }

    @Override
    public double valor(Map<String, Double> contexto) {
        return 0;
    }

    @Override
    public double valor() {
        return constante;
    }
}

class Soma implements Expressao {

    private Expressao parcelaUm;
    private Expressao parcelaDois;

    public Soma(Expressao p1, Expressao p2) {
        parcelaUm = p1;
        parcelaDois = p2;
    }

    @Override
    public double valor() {
        return parcelaUm.valor() + parcelaDois.valor();
    }

    @Override
    public double valor(Map<String, Double> contexto) {
        return 0;
    }
}

class Variavel implements Expressao {
    private String variavel;

    public Variavel(String variavel) {
        this.variavel = variavel;
    }

    @Override
    public double valor() {
        return 0;
    }

    @Override
    public double valor(Map<String, Double> contexto) {
        if (contexto.containsKey(variavel)) {
            return contexto.get(variavel);
        }

        return 0;
    }
}