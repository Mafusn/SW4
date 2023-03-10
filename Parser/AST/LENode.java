package AST;

public class LENode extends Node {
    private String token;

    public LENode(String token) {
        this.token = token;
    }

    @Override
    public void addChild(Node child) {

    }
    @Override
    public void accept(Visitor v) {

    }
}
