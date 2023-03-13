package AST;

import java.util.ArrayList;

public class StmtNode extends Node {
    private final ArrayList<Node> children;

    public StmtNode(Node id, Node assign, Node val, Node expr) {
        this.children = new ArrayList<>();

        if (id != null) {
            this.addChild(id);
        }
        if (assign != null) {
            this.addChild(assign);
        }
        if (val != null) {
            this.addChild(val);
        }
        if (expr != null) {
            this.addChild(expr);
        }
    }



    public StmtNode(Node ifStmt) {
        this.children = new ArrayList<>();

        if (ifStmt != null) {
            this.addChild(ifStmt);
        }
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public void addChild(Node child) {
        this.children.add(child);
    }
}
