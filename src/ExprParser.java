import java.util.NoSuchElementException;

public class ExprParser implements Parser {
    //grammar for arithmetic expressions:
    //E → E + T | E - T | T
    //T → T * F | T / F | T % F | F
    //F → n | ( E )
    //notice that E → E ... will be infinite recursion, so we need to use reassociation
    private Tokenizer tkz;

    public ExprParser(Tokenizer tokenizer) {
        this.tkz = tokenizer;
    }

    public int parse() throws SyntaxError {
        int result = parseE();
        if (tkz.hasNext()) {
            throw new SyntaxError("Unexpected token: " + tkz.peek());
        }
        return result;
    }

    private int parseE() throws SyntaxError {
        try {
            int result = parseT();
            while (tkz.hasNext() && (tkz.peek().equals("+") || tkz.peek().equals("-"))) {
                if (tkz.peek("+")) {
                    tkz.consume("+");
                    result += parseT();
                } else if (tkz.peek("-")) {
                    tkz.consume("-");
                    result -= parseT();
                }
            }
            return result;
        } catch (NoSuchElementException e) {
            throw new SyntaxError("Unexpected end of expression");
        }
    }

    private int parseT() throws SyntaxError {
        try {
            int result = parseF();
            while (tkz.hasNext() && (tkz.peek().equals("*") || tkz.peek().equals("/") || tkz.peek().equals("%"))) {
                if (tkz.peek("*")) {
                    tkz.consume("*");
                    result *= parseF();
                } else if (tkz.peek("/")) {
                    tkz.consume("/");
                    result /= parseF();
                } else if (tkz.peek("%")) {
                    tkz.consume("%");
                    result %= parseF();
                }
            }
            return result;
        } catch (ArithmeticException e) {
            throw new SyntaxError("Division by zero");
        }
    }

    private int parseF() throws SyntaxError {
        try {
            if (tkz.peek("(")) {
                tkz.consume("(");
                int result = parseE();
                tkz.consume(")");
                return result;
            } else {
                int result = Integer.parseInt(tkz.consume());
                return result;
            }
        } catch (NoSuchElementException e) {
            throw new SyntaxError("Unexpected end of input");
        } catch (SyntaxError e) {
            throw e;
        } catch (NumberFormatException e) {
            throw new SyntaxError("Expected number, got: " + tkz.peek());
        }
    }
}
