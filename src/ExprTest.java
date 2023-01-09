import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExprTest {
    @Test
    public void IntLitTes() {
        //Test IntLit that can be evaluated
        IntLit intLit = new IntLit(5);
        assertEquals(5, intLit.eval());
        //Test IntLit that can be pretty printed
        StringBuilder s = new StringBuilder();
        intLit.prettyPrint(s);
        assertEquals("5", s.toString());
    }
    @Test
    public void BinaryArithExprTest() throws SyntaxError {
        //Test BinaryArithExpr that can pretty print
        String[] test = {"1+2", "1+5*3", "2/2", "(1+2)*3", "1+2*3+4", "1+2*3+4*5", "1+2*3+4*5*6"
                , "1+2/2", "1+2/2*3", "1+2/2*3*4", "1+2/2*3*4*5", "1+2/2*3*4*5*6"};
        String[] ans = {"(1+2)", "(1+(5*3))", "(2/2)", "((1+2)*3)", "((1+(2*3))+4)", "((1+(2*3))+(4*5))", "((1+(2*3))+((4*5)*6))"
                , "(1+(2/2))", "(1+((2/2)*3))", "(1+(((2/2)*3)*4))", "(1+((((2/2)*3)*4)*5))", "(1+(((((2/2)*3)*4)*5)*6))"};
        int i = 0;
        for (String str : test) {
            Parser p = new ExprParser(new ExprTokenizer(str));
            StringBuilder s = new StringBuilder();
            Expr e = p.parse();
            e.prettyPrint(s);
            assertEquals(ans[i], s.toString());
            i++;
        }
    }
}