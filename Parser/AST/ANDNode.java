package AST;

public class ANDNode extends Node {
    private String token;

    public ANDNode(String token) {
        this.token = token;
    }

    @Override
    public void addChild(Node child) {

    }

    @Override
    public void accept(Visitor v) {

    }
}
