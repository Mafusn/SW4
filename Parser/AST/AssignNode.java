package AST;

public class AssignNode extends Node {
    private String token;
    public AssignNode(String token) {
        this.token = token;
    }

    @Override
    public void addChild(Node child) {

    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
