import java.util.Map;

public class BinaryArithExpr implements Expr{
    private Expr left, right;
    private String op;
    public BinaryArithExpr(Expr left, String op, Expr right) {
        this.left = left;
        this.op = op;
        this.right = right;
    }

    public int eval(Map<String,Integer> bindings) throws EvalError {
        int lv = left.eval(bindings);
        int rv = right.eval(bindings);
        if(op.equals("+")) return lv + rv;
        if(op.equals("-")) return lv - rv;
        if(op.equals("*")) return lv * rv;
        if(op.equals("/")) return lv / rv; // division by zero? not check because parser has already checked
        if(op.equals("%")) return lv % rv; // division by zero? not check because parser has already checked
        throw new EvalError("Unknown operator: " + op);
    }
    public void prettyPrint(StringBuilder s) {
        s.append("(");
        left.prettyPrint(s);
        s.append(op);
        right.prettyPrint(s);
        s.append(")");
    }
}
