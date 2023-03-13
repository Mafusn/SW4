package AST;


import java.util.ArrayList;

public class IfElseStmtNode extends Node {
    private ArrayList<Node> children;

    public IfElseStmtNode(Node a, Node b, Node c) {
        this.children = new ArrayList<>();

        if (a != null) {
            this.addChild(a);
        }
        if (b != null) {
            this.addChild(b);
        }
        if (c != null) {
            this.addChild(c);
        }
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    @Override
    public void addChild(Node child) {
        this.children.add(child);
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}


