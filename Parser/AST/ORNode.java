package AST;

public class ORNode extends Node {
    private String token;

    public ORNode(String token) {
        this.token = token;
    }

    @Override
    public void accept(Visitor v) {

    }
    @Override
    public void addChild(Node child) {

    }
}
