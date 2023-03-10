package AST;

public class LBraceNode extends Node {
    private String token;

    public LBraceNode(String token) {
        this.token = token;
    }

    @Override
    public void addChild(Node child) {

    }
    @Override
    public void accept(Visitor v) {

    }
}
