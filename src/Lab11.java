public class Lab11 {
    public static void main(String[] args) {
        String[] test = {"1+1", "1/0", "1/1"};
        for (String s : test) {
            try {
                Parser parser = new ExprParser(new ExprTokenizer(s));
                System.out.println(parser.parse());
            } catch (SyntaxError e) {
                e.printStackTrace();
            }
        }
    }
}
