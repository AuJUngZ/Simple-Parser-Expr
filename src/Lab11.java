import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Lab11 {
    public static void main(String[] args) throws IOException, SyntaxError, EvalError {
        Map<String,Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        String[] test = {"1-A+B"};
        for(String s : test){
            Parser p = new ExprParser(new ExprTokenizer(s));
            Expr e = p.parse();
            e.prettyPrint(sb);
            System.out.println(sb.toString());
            System.out.println(e.eval(map));
            sb.setLength(0);
        }
    }
}
