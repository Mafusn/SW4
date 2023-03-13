package AST;

import java.util.ArrayList;

public class BlockNode extends Node {
    private ArrayList<Node> children;

    public BlockNode() {
        this.children = new ArrayList<>();
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public void addChild(Node child) {
        this.children.add(child);
    }
}
