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

    public Expr parse() throws SyntaxError {
        Expr result = parseE();
        if (tkz.hasNext()) {
            throw new SyntaxError("Unexpected token: " + tkz.peek());
        }
        return result;
    }

    private Expr parseE() throws SyntaxError {
        try {
            Expr result = parseT();
            while (tkz.hasNext() && (tkz.peek().equals("+") || tkz.peek().equals("-"))) {
                if (tkz.peek("+")) {
                    tkz.consume("+");
                    result = new BinaryArithExpr(result, "+", parseT());
                } else if (tkz.peek("-")) {
                    tkz.consume("-");
                    result = new BinaryArithExpr(result, "-", parseT());
                }
            }
            return result;
        } catch (NoSuchElementException e) {
            throw new SyntaxError("Unexpected end of expression");
        }
    }

    private Expr parseT() throws SyntaxError {
        try {
            Expr result = parseF();
            while (tkz.hasNext() && (tkz.peek().equals("*") || tkz.peek().equals("/") || tkz.peek().equals("%"))) {
                if (tkz.peek("*")) {
                    tkz.consume("*");
                    result = new BinaryArithExpr(result, "*", parseF());
                } else if (tkz.peek("/")) {
                    tkz.consume("/");
                    result = new BinaryArithExpr(result, "/", parseF());
                } else if (tkz.peek("%")) {
                    tkz.consume("%");
                    result = new BinaryArithExpr(result, "%", parseF());
                }
            }
            return result;
        } catch (ArithmeticException e) {
            throw new SyntaxError("Division by zero");
        }
    }

    private Expr parseF() throws SyntaxError {
        try {
            if (tkz.peek("(")) {
                tkz.consume("(");
                Expr result = parseE();
                tkz.consume(")");
                return result;
            } else {
                if(tkz.peek().matches("[0-9]+")) {
                    return new IntLit(Integer.parseInt(tkz.consume()));
                }else{
                    String s = tkz.consume();
                    return new Variable(s);
                }
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
