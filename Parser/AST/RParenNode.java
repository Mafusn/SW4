package AST;

public class RParenNode extends Node {
    private String token;

    public RParenNode(String token) {
        this.token = token;
    }

    @Override
    public void accept(Visitor v) {

    }
    @Override
    public void addChild(Node child) {

    }
}
