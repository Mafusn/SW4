package AST;

import java.util.ArrayList;

public class ProgNode extends Node {
    private ArrayList<Node> children;

    public ProgNode() {
        this.children = new ArrayList<>();
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public void addChild(Node child) {
        this.children.add(child);
    }
}
