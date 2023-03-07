package AST;

public class ValNode extends Node {
    public void accept(Visitor v) {
        v.visit(this);
    }

    public ValNode() {
    }
}
