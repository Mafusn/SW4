package AST;

public class EOLNode extends Node {
    public EOLNode() {
    }@Override
    public void addChild(Node child) {

    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
