package AST;

public class DclNode extends Node {
    public void accept(Visitor v) {
        v.visit(this);
    }

    public DclNode() {
    }
}
