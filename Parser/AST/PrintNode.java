package AST;

public class PrintNode extends Node {
    private String token;

    public PrintNode(String token) {
        this.token = token;
    }

    @Override
    public void accept(Visitor v) {

    }
    @Override
    public void addChild(Node child) {

    }
}
