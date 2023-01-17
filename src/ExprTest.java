import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ExprTest {
    @Test
    public void IntLitPrintTest() throws SyntaxError, EvalError {
        StringBuilder s = new StringBuilder();
        String[] test = {"1", "100", "1000", "24", "0",};
        int[] expected = {1, 100, 1000, 24, 0};
        int i = 0;
        for (String str : test) {
            Parser p = new ExprParser(new ExprTokenizer(str));
            Expr e = p.parse();
            //Test for pretty print
            e.prettyPrint(s);
            assertEquals(test[i], s.toString());
            s.setLength(0);
            i++;
        }
    }

    @Test
    public void IntLitEvalTest() throws SyntaxError, EvalError {
        Map<String, Integer> env = Map.of();
        String[] test = {"1", "100", "1000", "24", "0",};
        int[] expected = {1, 100, 1000, 24, 0};
        int i = 0;
        for (String str : test) {
            Parser p = new ExprParser(new ExprTokenizer(str));
            Expr e = p.parse();
            assertEquals(expected[i], e.eval(env));
            i++;
        }
    }

    @Test
    public void VarPrintTest() throws SyntaxError, EvalError {
        StringBuilder s = new StringBuilder();
        String[] test = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        int i = 0;
        for (String str : test) {
            Parser p = new ExprParser(new ExprTokenizer(str));
            Expr e = p.parse();
            e.prettyPrint(s);
            assertEquals(test[i], s.toString());
            s.setLength(0);
            i++;
        }
    }

    @Test
    public void VarEvalTest() throws SyntaxError, EvalError {
        Map<String,Integer> map = new HashMap<>();
        map.put("A",1); map.put("B",2); map.put("C",3); map.put("D",4); map.put("E",5); map.put("F",6); map.put("G",7); map.put("H",8); map.put("I",9); map.put("J",10);
        map.put("K",11); map.put("L",12); map.put("M",13); map.put("N",14); map.put("O",15); map.put("P",16); map.put("Q",17); map.put("R",18); map.put("S",19); map.put("T",20);
        map.put("U",21); map.put("V",22); map.put("W",23); map.put("X",24); map.put("Y",25); map.put("Z",26);
        map.put("a",27); map.put("b",28); map.put("c",29); map.put("d",30); map.put("e",31); map.put("f",32); map.put("g",33); map.put("h",34); map.put("i",35); map.put("j",36);
        map.put("k",37); map.put("l",38); map.put("m",39); map.put("n",40); map.put("o",41); map.put("p",42); map.put("q",43); map.put("r",44); map.put("s",45); map.put("t",46);
        map.put("u",47); map.put("v",48); map.put("w",49); map.put("x",50); map.put("y",51); map.put("z",52);
        String[] test = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26,
                27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52};
        int i = 0;
        for(String str : test){
            Parser p = new ExprParser(new ExprTokenizer(str));
            Expr e = p.parse();
            assertEquals(expected[i],e.eval(map));
            i++;
        }
    }
//
    @Test
    public void BiTestNoVar() throws SyntaxError, EvalError {
        Map<String,Integer> map = new HashMap<>();
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
            assertEquals(evalAns[i], e.eval(map));
            i++;
        }
    }

    @Test
    public void BiTestVar() throws EvalError, SyntaxError {
        Map<String,Integer> map = new HashMap<>();
        map.put("A",1); map.put("B",2); map.put("C",3); map.put("D",4); map.put("E",5); map.put("F",6); map.put("G",7); map.put("H",8); map.put("I",9); map.put("J",10);
        String[] test = {"A+B", "A+B*C", "B/C", "(A+B)*C", "A+B*C+D", "A+B*C+D*E", "A+B*C+D*E*F"
                , "A+B/C", "A+B/C*C", "A+B/C*C*D", "A+B/C*C*D*E", "A+B/C*C*D*E*F"};
        String[] ans = {"(A+B)", "(A+(B*C))", "(B/C)", "((A+B)*C)", "((A+(B*C))+D)", "((A+(B*C))+(D*E))", "((A+(B*C))+((D*E)*F))"
                , "(A+(B/C))", "(A+((B/C)*C))", "(A+(((B/C)*C)*D))", "(A+((((B/C)*C)*D)*E))", "(A+(((((B/C)*C)*D)*E)*F))"};
        int[] evalAns = {3, 7, 0, 9, 11, 27, 127, 1, 1, 1, 1, 1};
        int i = 0;
        for(String str : test){
            Parser p = new ExprParser(new ExprTokenizer(str));
            StringBuilder s = new StringBuilder();
            Expr e = p.parse();
            e.prettyPrint(s);
            //Test for pretty print
            assertEquals(ans[i], s.toString());
            //Test for eval
            assertEquals(evalAns[i],e.eval(map));
            i++;
        }
    }

    @Test
    public void BiTestMixed() throws SyntaxError, EvalError {
        Map<String,Integer> map = new HashMap<>();
        map.put("A",1); map.put("B",2); map.put("C",3); map.put("D",4); map.put("E",5); map.put("F",6); map.put("G",7); map.put("H",8); map.put("I",9); map.put("J",10);
        String[] test = {"1/A+(1-1)", "1-A+B", "(A*D)/3", "2*5-8/(F-2)"};
        String[] ans = {"((1/A)+(1-1))", "((1-A)+B)", "((A*D)/3)", "((2*5)-(8/(F-2)))"};
        int[] evalAns = {1, 2, 1, 8};
        int i = 0;
        for(String str : test){
            Parser p = new ExprParser(new ExprTokenizer(str));
            StringBuilder s = new StringBuilder();
            Expr e = p.parse();
            e.prettyPrint(s);
            //Test for pretty print
            assertEquals(ans[i], s.toString());
            //Test for eval
            assertEquals(evalAns[i],e.eval(map));
            i++;
        }
    }

    @Test
    public void notFoundVar() throws SyntaxError {
        Map<String,Integer> map = new HashMap<>();
        String[] test = {"A","A+B","A+B*C","B/C","(A+B)*C","A+B*C+D","A+B*C+D*E","A+B*C+D*E*F"
                ,"A+B/C","A+B/C*C","A+B/C*C*D","A+B/C*C*D*E","A+B/C*C*D*E*F"};
        int i = 0;
        for(String str : test){
            Parser p = new ExprParser(new ExprTokenizer(str));
            Expr e = p.parse();
            assertThrows(EvalError.class, () -> e.eval(map));
        }
    }

//brief for this test
//Read this carefully : this test case we sure that expr is satisfied the grammar so we don't need to check the syntax error
    //First testing for each expr class such as NumExpr, VarExpr, BiExpr (name of class might not match for real). Testing for eval and pretty print
    //then test expr class with real arithmetic expression
        //expression with no variable (normal expression)
        //expression with only variable
        //expression with both variable and number
    //then test for not found variable in map
}