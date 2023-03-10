package AST;

public class LParenNode extends Node {
    private String token;

    public LParenNode(String token) {
        this.token = token;
    }
    @Override
    public void addChild(Node child) {

    }
    @Override
    public void accept(Visitor v) {

    }
}
