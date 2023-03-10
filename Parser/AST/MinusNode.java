package AST;

public class MinusNode extends Node {
    private String token;

    public MinusNode(String token) {
        this.token = token;
    }

    @Override
    public void accept(Visitor v) {

    }
    @Override
    public void addChild(Node child) {

    }
}
