import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

public class Lab11 {
    public static void main(String[] args) {
        StringBuilder s = new StringBuilder();
        String[] test = {"1+2", "1+5*3", "l/2", "(1+2)*3", "1/0","8*9/7","1+1"};
        for (String str : test) {
            try {
                s.setLength(0);
                Parser p = new ExprParser(new ExprTokenizer(str));
                Expr e = p.parse();
                e.prettyPrint(s);
                System.out.print(s + " = " + e.eval());
                System.out.println();
            }catch (SyntaxError e){
                System.out.println(e.getMessage());
            }catch (EvalError e){
                System.out.println(e.getMessage());
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
