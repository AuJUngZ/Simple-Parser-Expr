import java.util.Map;

public class IntLit implements Expr{
    private int value;
    public IntLit(int value) {
        this.value = value;
    }
    public int eval(){
        return value;
    }
    @Override
    public void prettyPrint(StringBuilder s) {
        s.append(value);
    }
}
