package AST;

public class RBraceNode extends Node {
    private String token;

    public RBraceNode(String token) {
        this.token = token;
    }

    @Override
    public void accept(Visitor v) {

    }
    @Override
    public void addChild(Node child) {

    }
}
