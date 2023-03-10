package AST;

public class GENode extends Node {
    private String token;

    public GENode(String token) {
        this.token = token;
    }

    @Override
    public void addChild(Node child) {

    }
    @Override
    public void accept(Visitor v) {

    }
}
