package AST;

public class IdNode extends Node {
    private String id;
    public IdNode(String id) {
        this.id = id;
    }
    @Override
    public void addChild(Node child) {

    }
    public String getId() {
        return id;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
