package AST;

public class EQNode extends Node {
    private String token;

    public EQNode(String token) {
        this.token = token;
    }

    @Override
    public void addChild(Node child) {

    }

    @Override
    public void accept(Visitor v) {

    }
}
