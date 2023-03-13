package AST;

import java.util.ArrayList;

public class ANDNode extends Node {
    private ArrayList<Node> children;

    public ANDNode(Node a, Node b) {
        this.children = new ArrayList<>();

        if (a != null) {
            this.addChild(a);
        }
        if (b != null) {
            this.addChild(b);
        }
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    @Override
    public void accept(Visitor v) {

    }
    @Override
    public void addChild(Node child) {
        this.children.add(child);
    }
}
