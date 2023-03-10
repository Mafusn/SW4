package AST;

public class BoolDclNode extends Node {
    private String token;
    public BoolDclNode(String token) {
        this.token = token;
    }

    @Override
    public void addChild(Node child) {

    }

    public String getToken() {
        return token;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
