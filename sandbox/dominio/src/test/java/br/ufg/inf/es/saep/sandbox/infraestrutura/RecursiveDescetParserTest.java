package br.ufg.inf.es.saep.sandbox.infraestrutura;

import br.ufg.inf.es.saep.sandbox.dominio.regra.Expressao;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Analisador Sintático Descendente Recursivo.
 * <p>
 * Constante ::= '-' + Numero | Numero
 * Variavel ::= Letra (uma ou mais)
 * Expr ::= Constante | Variavel | ( Expr Operador Expr )
 * Main ::= Expr [Operador Expr]
 *
 * <p>Observe que não contempla "Expr op Expr".
 */
public class RecursiveDescetParserTest {

    @Test
    public void expressaoNullInvalida() {
        assertThrows(IllegalArgumentException.class, () -> new Parser(null).expressao());
    }

    @Test
    public void expressaoVaziaInvalida() {

        assertThrows(IllegalArgumentException.class, () -> new Parser("").expressao());
    }

    @Test
    public void expressaoComElementoInvalido() {

        assertThrows(IllegalArgumentException.class, () -> new Parser("@").expressao());
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
        Map<String,Float> ctx = new HashMap<>(2);
        ctx.put("x", 9.876f);
        ctx.put("CaSa", 8.765f);

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
        assertEquals(1.12675f, new Parser("(x/CaSa)").expressao().valor(ctx), 0.00001d);
        assertEquals(1.111f, new Parser("(x-CaSa)").expressao().valor(ctx), 0.00001d);
    }

    @Test
    public void expressoesLogicas() {
        assertEquals(0d, new Parser("(0&1)").expressao().valor(), 0.00001d);
        assertEquals(0d, new Parser("(0&0)").expressao().valor(), 0.00001d);
        assertEquals(0d, new Parser("( 0 & 1 )").expressao().valor(), 0.00001d);
        assertEquals(0d, new Parser("( 0 & 1 )").expressao().valor(), 0.00001d);
        assertEquals(1d, new Parser("( 1 & 1 )").expressao().valor(), 0.00001d);
        assertEquals(1d, new Parser("( 0 | 1 )").expressao().valor(), 0.00001d);
        assertEquals(2d, new Parser("( 1 | 1 )").expressao().valor(), 0.00001d);
    }

    @Test
    public void expressoesLogicasComplexas() {
        assertEquals(0d, new Parser("( 0 | (1 & 0) )").expressao().valor(), 0.00001d);
        assertEquals(2d, new Parser("(1|(1 & 1))").expressao().valor(), 0.00001d);
    }

    @Test
    public void sentencasInvalidas() {
        assertThrows(IllegalArgumentException.class,
                () -> new Parser("(").expressao());
        assertThrows(IllegalArgumentException.class,
                () -> new Parser("-").expressao());
    }

    @Test
    public void expressaoTodaComBrancos() {
        assertThrows(IllegalArgumentException.class,
                () -> new Parser("   ").expressao());
    }

    @Test
    public void expressaoSemParenteses() {
        assertEquals(2d, new Parser("1 + 1").expressao().valor(), 0.0001d);
        assertEquals(2d, new Parser("1 + 1 + 4").expressao().valor(), 0.0001d);
    }
}

class Parser {

    private int corrente = 0;
    private char caractere = ' ';
    private String expr;
    private int ultimaPosicao;

    public Parser(String expressao) {
        if (expressao == null) {
            throw new IllegalArgumentException("expressão null");
        }

        expr = expressao.trim();
        ultimaPosicao = expr.length() - 1;

        if (ultimaPosicao < 0) {
            throw new IllegalArgumentException("apenas espaço(s)");
        }

        caractere = expr.charAt(corrente);
    }

    /**
     * expr [operador expr]
     *
     * @return A expressão correspondente à sentença a ser
     * analisada.
     */
    public Expressao expressao() {
        Expressao analisada = expr();

        if (corrente < ultimaPosicao) {
            return complementoExpr(analisada);
        }

        return analisada;
    }

    /**
     * Expr ::= Constante | Variavel | (Expr Op Expr)
     * @return
     */
    private Expressao expr() {
        proximo();

        if (isConstante()) {
            return new Constante(constante());
        }

        if (isLetra()) {
            return new Variavel(variavel());
        }

        if (isExprEntreParenteses()) {
            return exprEntreParenteses();
        }

        throw new IllegalArgumentException("Nao esperado: " + caractere);
    }

    private void fechaParenteses() {
        eliminaBrancos();

        if (caractere != ')') {
            throw new IllegalArgumentException("Esperado fecha parenteses: " + caractere);
        }

        if (corrente < ultimaPosicao) {
            caractere = expr.charAt(++corrente);
        }
    }

    private void consome(char esperado) {
        if (caractere != esperado) {
            throw new IllegalArgumentException("Esperado " + caractere);
        }
    }

    private char getOperador() {
        eliminaBrancos();
        if (isOperador()) {
            char operador = caractere;

            // consome operador
            caractere = expr.charAt(++corrente);

            return operador;
        }

        throw new IllegalArgumentException(" Operador esperado: " + caractere);
    }

    /**
     * Verifica se o token corrente é um operador.
     *
     * @return {@code true} se o token corrente é um
     * operador e {@code false}, caso contrário.
     */
    private boolean isOperador() {
        return caractere == '+' || caractere == '-' ||
                caractere == '*' || caractere == '/' ||
                caractere == '&' || caractere == '|';
    }

    private boolean isExprEntreParenteses() {
        return caractere == '(';
    }

    /**
     * Pelo menos um dígito, possivelmente precedido
     * pelo sinal de menos identifica uma constante.
     *
     * @return {@code true} se na posição corrente da
     * expressão encontra-se uma constante.
     */
    private boolean isConstante() {
        if (Character.isDigit(caractere) || caractere == '-') {
            return true;
        }

        return false;
    }

    private boolean isLetra() {
        return Character.isLetter(caractere);
    }

    private Expressao exprEntreParenteses() {
        // consome '('
        consome('(');

        Expressao exp1 = expr();

        Expressao comComplemento = complementoExpr(exp1);

        fechaParenteses();

        return comComplemento;
    }

    /**
     * Operador Expr
     *
     * @param expr1 Primeiro operando.
     *
     * @return Expressão formada pelo primeiro operando
     * concatenada com o Operador Expr.
     */
    private Expressao complementoExpr(Expressao expr1) {
        char operador = getOperador();

        Expressao exp2 = expr();

        switch (operador) {
            case '+':
                return new Soma(expr1, exp2);
            case '-':
                return new Subtracao(expr1, exp2);
            case '*':
                return new Multiplicacao(expr1, exp2);
            case '/':
                return new Divisao(expr1, exp2);
            case '&':
                return new Multiplicacao(expr1, exp2);
            case '|':
                return new Soma(expr1, exp2);
            default:
                throw new IllegalArgumentException("Operador invalido:" + caractere);
        }
    }

    private float constante() {
        int sinal = 1;
        if (caractere == '-') {
            proximo();
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

            int fim = Character.isDigit(caractere) ? corrente + 1 : corrente;
            String doubleStr = expr.substring(inicio, fim);
            return sinal * (float)Double.parseDouble(doubleStr);
        }

        throw new IllegalArgumentException("constante esperada");
    }

    private String variavel() {
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

    /**
     * Elimina caracteres brancos (que não fazem parte
     * de token). Método seguinte deve recuperar próximo
     * token via método {@link #proximo()}.
     */
    private void eliminaBrancos() {
        while (isBranco(caractere)) {
            if (isProximoCaractereBranco()) {
                caractere = expr.charAt(++corrente);
            } else {
                return;
            }
        }
    }

    private boolean isProximoCaractereBranco() {
        if (corrente < ultimaPosicao) {
            return isBranco(expr.charAt(corrente + 1));
        }

        return false;
    }

    private boolean isBranco(char caractere) {
        return caractere == ' ' || caractere == '\t';
    }

    private void proximo() {
        if (isBranco(caractere)) {
            eliminaBrancos();
        } else {
            if (corrente < ultimaPosicao) {
                caractere = expr.charAt(++corrente);
            } else {
                throw new IllegalArgumentException("fim inesperado");
            }
        }
    }
}

class Constante implements Expressao {
    private final float constante;

    public Constante(float valor) {
        constante = valor;
    }

    @Override
    public float valor(Map<String, Float> contexto) {
        return 0;
    }

    @Override
    public float valor() {
        return constante;
    }
}

class Soma implements Expressao {

    private final Expressao parcelaUm;
    private final Expressao parcelaDois;

    public Soma(Expressao p1, Expressao p2) {
        parcelaUm = p1;
        parcelaDois = p2;
    }

    @Override
    public float valor() {
        return parcelaUm.valor() + parcelaDois.valor();
    }

    @Override
    public float valor(Map<String, Float> contexto) {
        return parcelaUm.valor(contexto) + parcelaDois.valor(contexto);
    }
}

class Subtracao implements Expressao {

    private final Expressao parcelaUm;
    private final Expressao parcelaDois;

    public Subtracao(Expressao p1, Expressao p2) {
        parcelaUm = p1;
        parcelaDois = p2;
    }

    @Override
    public float valor() {
        return parcelaUm.valor() - parcelaDois.valor();
    }

    @Override
    public float valor(Map<String, Float> contexto) {
        return parcelaUm.valor(contexto) - parcelaDois.valor(contexto);
    }
}

class Multiplicacao implements Expressao {

    private final Expressao parcelaUm;
    private final Expressao parcelaDois;

    public Multiplicacao(Expressao p1, Expressao p2) {
        parcelaUm = p1;
        parcelaDois = p2;
    }

    @Override
    public float valor() {
        return parcelaUm.valor() * parcelaDois.valor();
    }

    @Override
    public float valor(Map<String, Float> contexto) {
        return parcelaUm.valor(contexto) * parcelaDois.valor(contexto);
    }
}

class Divisao implements Expressao {

    private final Expressao numerador;
    private final Expressao denominador;

    public Divisao(Expressao p1, Expressao p2) {
        numerador = p1;
        denominador = p2;
    }

    @Override
    public float valor() {
        return numerador.valor() / denominador.valor();
    }

    @Override
    public float valor(Map<String, Float> contexto) {
        return numerador.valor(contexto) / denominador.valor(contexto);
    }
}

class Variavel implements Expressao {
    private final String variavel;

    public Variavel(String variavel) {
        this.variavel = variavel;
    }

    @Override
    public float valor() {
        return 0;
    }

    @Override
    public float valor(Map<String, Float> contexto) {
        if (contexto.containsKey(variavel)) {
            return contexto.get(variavel);
        }

        return 0;
    }
}