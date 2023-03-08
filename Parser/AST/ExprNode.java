package AST;

public class ExprNode extends Node {
    public void accept(Visitor v) {
        v.visit(this);
    }

    public ExprNode() {
    }
}
