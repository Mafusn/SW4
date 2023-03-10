package AST;

public class IntDclNode extends Node {
    private String token;
    public IntDclNode(String token) {
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
