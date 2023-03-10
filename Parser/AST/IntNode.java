package AST;

public class IntNode extends Node {
    private String token;

    public IntNode(String token) {
        this.token = token;
    }

    @Override
    public void addChild(Node child) {

    }
    @Override
    public void accept(Visitor v) {

    }
}
