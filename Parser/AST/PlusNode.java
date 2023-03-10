package AST;

public class PlusNode extends Node {
    private String token;

    public PlusNode(String token) {
        this.token = token;
    }

    @Override
    public void accept(Visitor v) {

    }
    @Override
    public void addChild(Node child) {

    }
}
