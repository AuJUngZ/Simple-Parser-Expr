import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest{
    @Test
    public void testParse() throws SyntaxError {
        //test for simple expression plus
        Parser parser = new ExprParser(new ExprTokenizer("1+1"));
        assertEquals(2,parser.parse());
        //test for simple expression minus
        parser = new ExprParser(new ExprTokenizer("1-1"));
        assertEquals(0,parser.parse());
        //test for simple expression multiply
        parser = new ExprParser(new ExprTokenizer("1*1"));
        assertEquals(1,parser.parse());
        //test for simple expression divide
        parser = new ExprParser(new ExprTokenizer("1/1"));
        //test for simple expression mod
        parser = new ExprParser(new ExprTokenizer("1%1"));
        assertEquals(0,parser.parse());

        //test for complex expression
        Parser parser1 = new ExprParser(new ExprTokenizer("1+2*3"));
        assertEquals(7,parser1.parse());
        Parser parser2 = new ExprParser(new ExprTokenizer(" 1+2*3/4"));
        assertEquals(2,parser2.parse());
        Parser parser3 = new ExprParser(new ExprTokenizer("1+2*3/4-5"));
        assertEquals(-3,parser3.parse());
//
//
        //test for brackets expression
        Parser parser7 = new ExprParser(new ExprTokenizer("(1+1)*0"));
        assertEquals(0,parser7.parse());
        Parser parser8 = new ExprParser(new ExprTokenizer("1+(1*0)"));
        assertEquals(1,parser8.parse());
        Parser parser10 = new ExprParser(new ExprTokenizer("(1+2)*3+(4*5)"));
        assertEquals(29,parser10.parse());
    }

    @Test
    public void testException() throws SyntaxError {
        //test for unexpected expression
        Parser parser = new ExprParser(new ExprTokenizer("1+"));
        assertThrows(SyntaxError.class, () -> parser.parse());
        //test for noting in the brackets
        Parser parser1 = new ExprParser(new ExprTokenizer("()"));
        assertThrows(SyntaxError.class, () -> parser1.parse());
        //test for wrong position of brackets
        Parser parser2 = new ExprParser(new ExprTokenizer(")+1"));
        assertThrows(SyntaxError.class, () -> parser2.parse());
        //test for unbalanced brackets
        Parser parser3 = new ExprParser(new ExprTokenizer("(1+(1*0)"));
        assertThrows(SyntaxError.class, () -> parser3.parse());
        //test for unexpected token
        Parser parser4 = new ExprParser(new ExprTokenizer("1+1)"));
        assertThrows(SyntaxError.class, () -> parser4.parse());
        //test divide by zero
        Parser parser5 = new ExprParser(new ExprTokenizer("1/0"));
        assertThrows(SyntaxError.class, () -> parser5.parse());
        //test divide by zero
        Parser parser6 = new ExprParser(new ExprTokenizer("1%0"));
        assertThrows(SyntaxError.class, () -> parser6.parse());
    }
}

//brief analysis on the coverage of my test cases
//I have tested all the possible cases of the expression, including the simple expression, complex expression and brackets expression.
//I have also tested the exception cases, including unexpected expression, noting in the brackets, wrong position of brackets and unbalanced brackets.
//I have also tested the divide by zero exception.
//I wish my test cases can cover all the possible cases of the expression and exception cases.

//NOTE: I this version ") , (" at the end ex. "1+2*3/4-5)" will be accepted as a valid expression. I think grammar is not correct in this case.