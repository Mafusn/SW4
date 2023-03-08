package AST;
public class ProgNode extends Node {
    public void accept(Visitor v) {
        v.visit(this);
    }

    public ProgNode() {
    }
}
