package AST;

import java.util.ArrayList;

public class DclNode extends Node {
    private final ArrayList<Node> children;

    public DclNode(Node type, Node id, Node val, Node assign, Node expr) {
        this.children = new ArrayList<>();

        if (type != null) {
            this.addChild(type);
        }
        if (id != null) {
            this.addChild(id);
        }
        if (assign != null) {
            this.addChild(assign);
            if (val != null) {
                this.addChild(val);
            }
            if (expr != null) {
                this.addChild(expr);
            }
        }
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    @Override
    public void addChild(Node child) {
        this.children.add(child);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
