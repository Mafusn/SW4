package AST;

public class IfNode extends Node {
    private String token;

    public IfNode(String token) {
        this.token = token;
    }
    @Override
    public void addChild(Node child) {

    }
    @Override
    public void accept(Visitor v) {

    }
}
