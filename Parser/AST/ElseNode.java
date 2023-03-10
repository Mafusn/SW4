package AST;

public class ElseNode extends Node {
    private String token;

    public ElseNode(String token) {
        this.token = token;
    }

    @Override
    public void addChild(Node child) {

    }

    @Override
    public void accept(Visitor v) {

    }
}
