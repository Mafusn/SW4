package AST;

public class FloatNode extends Node {
    private String token;

    public FloatNode(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public void addChild(Node child) {

    }
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
