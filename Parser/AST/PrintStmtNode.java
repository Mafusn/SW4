package AST;

import java.util.ArrayList;

public class PrintStmtNode extends Node {
    private final ArrayList<Node> children;

    @Override
    public void addChild(Node child) {
        this.children.add(child);
    }

    public PrintStmtNode(PrintNode print, LParenNode lParen, IdNode id, RParenNode rParen) {
        this.children = new ArrayList<>();

        if (print != null) {
            this.addChild(print);
        }
        if (lParen != null) {
            this.addChild(lParen);
        }
        if (id != null) {
            this.addChild(id);
        }
        if (rParen != null) {
            this.addChild(rParen);
        }
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
        for (Node child : children) {
            child.accept(v);
        }
    }
}
