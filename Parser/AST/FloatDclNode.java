package AST;

public class FloatDclNode extends Node {
    private String token;
    public FloatDclNode(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public void addChild(Node child) {

    }
}
