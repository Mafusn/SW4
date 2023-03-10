package AST;

public class TrueNode extends Node {
    private String token;

    public TrueNode(String token) {
        this.token = token;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public void addChild(Node child) {

    }
}
