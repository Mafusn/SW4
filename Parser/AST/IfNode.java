package AST;

public class IfNode extends Node {
    public void accept(Visitor v) {
        v.visit(this);
    }

    public IfNode() {
    }
}
