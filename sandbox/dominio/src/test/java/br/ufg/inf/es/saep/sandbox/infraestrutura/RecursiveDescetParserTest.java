package br.ufg.inf.es.saep.sandbox.infraestrutura;

import br.ufg.inf.es.saep.sandbox.dominio.regra.Expressao;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Analisador Sintático Descendente Recursivo.
 * <p>
 * Numero ::= dígito (um ou mais)
 * Constante ::= '-' + Numero | Numero
 * Variavel ::= Letra (uma ou mais)
 * Expr ::= Constante | Variavel | ( Expr Operador Expr )
 */
public class RecursiveDescetParserTest {

    @Test(expected = IllegalArgumentException.class)
    public void expressaoNullInvalida() {
        new Parser(null).expressao();
    }

    @Test(expected = IllegalArgumentException.class)
    public void expressaoVaziaInvalida() {
        new Parser("").expressao();
    }

    @Test(expected = IllegalArgumentException.class)
    public void expressaoSemParentesesInvalida() {
        new Parser("x + y").expressao();
    }

    @Test(expected = IllegalArgumentException.class)
    public void expressaoComElementoInvalido() {
        new Parser("@").expressao();
    }

    @Test
    public void expressoesConstantes() {
        assertEquals(2.34, new Parser(" 2.34    ").expressao().valor(), 0.00001d);
        assertEquals(-4, new Parser("   -4").expressao().valor(), 0.00001d);
        assertEquals(-4, new Parser("   -4  ").expressao().valor(), 0.00001d);
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
        assertEquals(9.876d, new Parser("   x ").expressao().valor(ctx), 0.00001d);
        assertEquals(8.765d, new Parser("   CaSa").expressao().valor(ctx), 0.00001d);
        assertEquals(8.765d, new Parser("   CaSa    ").expressao().valor(ctx), 0.00001d);
        assertEquals(18.641d, new Parser("(x + CaSa)").expressao().valor(ctx), 0.00001d);
        assertEquals(18.641d, new Parser(" (x + CaSa) ").expressao().valor(ctx), 0.00001d);
        assertEquals(18.641d, new Parser(" ( x+CaSa )").expressao().valor(ctx), 0.00001d);
        assertEquals(18.641d, new Parser(" ( x+   CaSa )").expressao().valor(ctx), 0.00001d);
        assertEquals(18.641d, new Parser(" ( x+CaSa )  ").expressao().valor(ctx), 0.00001d);
        assertEquals(18.641d, new Parser(" ( x    +CaSa )").expressao().valor(ctx), 0.00001d);
        assertEquals(86.56314d, new Parser("(x*CaSa)").expressao().valor(ctx), 0.00001d);
        assertEquals(1.12675d, new Parser("(x/CaSa)").expressao().valor(ctx), 0.00001d);
        assertEquals(1.11099d, new Parser("(x-CaSa)").expressao().valor(ctx), 0.00001d);
    }
}

class Parser {

    private int corrente = 0;
    private char caractere = ' ';
    private String expr;
    private int ultimaPosicao;

    public Parser(String expr) {
        if (expr == null || expr.isEmpty()) {
            throw new IllegalArgumentException("expressão nula ou vazia");
        }

        this.expr = expr;
        ultimaPosicao = expr.length() - 1;

        caractere = expr.charAt(corrente);
    }

    public Expressao expressao() {
        Expressao analisada = getExpressao();

        if (corrente < ultimaPosicao) {
            throw new IllegalArgumentException("fim inesperado: " + caractere);
        }

        return analisada;
    }

    private Expressao getExpressao() {
        eliminaBrancos();

        if (isConstante()) {
            return new Constante(getConstante());
        }

        if (isLetra()) {
            return new Variavel(getVariavel());
        }

        if (isExpressaoEntreParenteses()) {
            return getExpressaoEntreParenteses();
        }

        throw new IllegalArgumentException("Nao esperado: " + caractere);
    }

    private void fechaParenteses() {
        eliminaBrancos();

        if (caractere != ')') {
            throw new IllegalArgumentException("Esperado fecha parenteses: " +caractere);
        }
    }

    private char getOperador() {
        eliminaBrancos();
        if (caractere == '+' || caractere == '-' || caractere == '*' || caractere == '/') {
            char operador = caractere;

            // consome operador
            caractere = expr.charAt(++corrente);

            return operador;
        }

        throw new IllegalArgumentException(" Operador esperado: " + caractere);
    }

    private boolean isExpressaoEntreParenteses() {
        return caractere == '(';
    }

    /**
     * Pelo menos um dígito, possivelmente precedido
     * pelo sinal de menos.
     *
     * @return {@code true} se na posição corrente da
     * expressão encontra-se uma constante.
     */
    private boolean isConstante() {
        if (Character.isDigit(caractere)) {
            return true;
        }

        if (caractere != '-') {
            return false;
        }

        // TODO Apenas '-' gera excecao
        return Character.isDigit(expr.charAt(corrente + 1));
    }

    private boolean isLetra() {
        return Character.isLetter(caractere);
    }

    private Expressao getExpressaoEntreParenteses() {
        // consome '('
        caractere = expr.charAt(++corrente);

        Expressao exp1 = expressao();

        char operador = getOperador();

        Expressao exp2 = expressao();

        fechaParenteses();

        switch (operador) {
            case '+':
                return new Soma(exp1, exp2);
            case '-':
                return new Subtracao(exp1, exp2);
            case '*':
                return new Multiplicacao(exp1, exp2);
            case '/':
                return new Divisao(exp1, exp2);
            default:
                throw new IllegalArgumentException("Operador invalido:" + caractere);
        }
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

        int fim = isLetra() ? corrente + 1 : corrente;
        return expr.substring(inicio, fim);
    }

    private void eliminaBrancos() {
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
        return parcelaUm.valor(contexto) + parcelaDois.valor(contexto);
    }
}

class Subtracao implements Expressao {

    private Expressao parcelaUm;
    private Expressao parcelaDois;

    public Subtracao(Expressao p1, Expressao p2) {
        parcelaUm = p1;
        parcelaDois = p2;
    }

    @Override
    public double valor() {
        return parcelaUm.valor() - parcelaDois.valor();
    }

    @Override
    public double valor(Map<String, Double> contexto) {
        return parcelaUm.valor(contexto) - parcelaDois.valor(contexto);
    }
}

class Multiplicacao implements Expressao {

    private Expressao parcelaUm;
    private Expressao parcelaDois;

    public Multiplicacao(Expressao p1, Expressao p2) {
        parcelaUm = p1;
        parcelaDois = p2;
    }

    @Override
    public double valor() {
        return parcelaUm.valor() * parcelaDois.valor();
    }

    @Override
    public double valor(Map<String, Double> contexto) {
        return parcelaUm.valor(contexto) * parcelaDois.valor(contexto);
    }
}

class Divisao implements Expressao {

    private Expressao numerador;
    private Expressao denominador;

    public Divisao(Expressao p1, Expressao p2) {
        numerador = p1;
        denominador = p2;
    }

    @Override
    public double valor() {
        return numerador.valor() / denominador.valor();
    }

    @Override
    public double valor(Map<String, Double> contexto) {
        return numerador.valor(contexto) / denominador.valor(contexto);
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