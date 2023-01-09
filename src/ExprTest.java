import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExprTest {
    @Test
    public void IntLitTes() throws SyntaxError, EvalError {
        StringBuilder s = new StringBuilder();
        String[] test = {"1","100","1000","24","0",};
        int[] expected = {1,100,1000,24,0};
        int i = 0;
        for(String str : test){
            Parser p = new ExprParser(new ExprTokenizer(str));
            Expr e = p.parse();
            //Test for pretty print
            assertEquals(expected[i],e.eval());
            //Test for eval
            e.prettyPrint(s);
            assertEquals(test[i],s.toString());
            s.setLength(0);
            i++;
        }
    }
    @Test
    public void BinaryArithExprTest() throws SyntaxError, EvalError {
        //Test BinaryArithExpr that can pretty print
        String[] test = {"1+2", "1+5*3", "2/2", "(1+2)*3", "1+2*3+4", "1+2*3+4*5", "1+2*3+4*5*6"
                , "1+2/2", "1+2/2*3", "1+2/2*3*4", "1+2/2*3*4*5", "1+2/2*3*4*5*6"};
        String[] ans = {"(1+2)", "(1+(5*3))", "(2/2)", "((1+2)*3)", "((1+(2*3))+4)", "((1+(2*3))+(4*5))", "((1+(2*3))+((4*5)*6))"
                , "(1+(2/2))", "(1+((2/2)*3))", "(1+(((2/2)*3)*4))", "(1+((((2/2)*3)*4)*5))", "(1+(((((2/2)*3)*4)*5)*6))"};
        int[] evalAns = {3, 16, 1, 9, 11, 27, 127, 2, 4, 13, 61, 361};
        int i = 0;
        for (String str : test) {
            Parser p = new ExprParser(new ExprTokenizer(str));
            StringBuilder s = new StringBuilder();
            Expr e = p.parse();
            e.prettyPrint(s);
            //Test for pretty print
            assertEquals(ans[i], s.toString());
            //Test for eval
            assertEquals(evalAns[i], e.eval());
            i++;
        }
    }

    //brief for this test
    //Read this carefully : this test case we sure that expr is satisfied the grammar so we don't need to check the syntax error
    //1.this will set only create a AST trees. It will not test for any exception.
    //2.First it will test for the IntLit class that give a correct ans. such as 5 will be 5.
    //  -It not necessary to test too many cases because it is a simple class.
    //  -It eval and pretty print only one Integer.
    //3.Second it will test for the BinaryArithExpr class that give a correct ans.
    // - give a parenthesis balance or not.
    // - then passed above test, it will test for the eval() method.
    // - then passed above test, it will test for the prettyPrint() method.
    // Note: Do not test the exception because it was tested in the ExprParserTest.java (Lab11)
    //       in addition every exception will not appear in this class if pass the ExprParserTest.java
    //       (if Parser is correct AST tree will be created correctly)
}