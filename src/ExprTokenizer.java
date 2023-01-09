import java.util.NoSuchElementException;

public class ExprTokenizer implements Tokenizer {
    private String src,next;
    private int pos;

    public ExprTokenizer(String src) {
        this.src = src;
        this.pos = 0;
        computeNext();
    }

    public boolean hasNext(){
        return next != null;
    }

    public String peek(){
        if(!hasNext()){
            throw new NoSuchElementException("No more tokens");
        }else{
            return next;
        }
    }

    public boolean peek(String s){
        return peek().equals(s);
    }

    public String consume(){
        if(!hasNext()) {
            throw new NoSuchElementException("No more tokens");
        }else{
            String result = next;
            computeNext();
            return result;
        }
    }

    public boolean consume(String s) throws SyntaxError {
        if(peek().equals(s)){
            consume();
            return true;
        }else{
            throw new SyntaxError("Expected: " + s + ", but got: " + peek());
        }
    }

    private void computeNext(){
        StringBuilder s = new StringBuilder();
        // skip spaces
        while(pos < src.length() && Character.isWhitespace(src.charAt(pos))){
            pos++;
        }
        // check if we are at the end of the string
        if(pos == src.length()){
            next = null;
            return;
        }
        char c = src.charAt(pos);
        if(Character.isDigit(c)) {
            //loop for all digits
            while (pos < src.length() && Character.isDigit(src.charAt(pos))) {
                s.append(src.charAt(pos));
                pos++;
            }
        }else if(c == '(' || c == ')' || c == '+' || c == '-' || c == '*' || c == '/' || c == '%'){
            s.append(c);
            pos++;
        }else{
            throw new IllegalArgumentException("Illegal character: " + c);
        }
        next = s.toString();
    }
}
