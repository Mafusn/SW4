package AST;

public class EOFNode extends Node {
    public EOFNode() {
    }@Override
    public void addChild(Node child) {

    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
