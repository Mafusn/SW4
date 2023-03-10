package AST;

public class NENode extends Node {
    private String token;

    public NENode(String token) {
        this.token = token;
    }

    @Override
    public void accept(Visitor v) {

    }
    @Override
    public void addChild(Node child) {

    }
}
