package AST;

import java.util.ArrayList;

public class ExprNode extends Node {
    private ArrayList<Node> children;

    public ExprNode(Node plus, Node minus, Node ltNode, Node gtNode, Node geNode, Node leNode, Node val, Node expr) {
        this.children = new ArrayList<>();

        if (plus != null) {
            this.addChild(plus);
        }
        if (minus != null) {
            this.addChild(minus);
        }
        if (ltNode != null) {
            this.addChild(ltNode);
        }
        if (gtNode != null) {
            this.addChild(gtNode);
        }
        if (geNode != null) {
            this.addChild(geNode);
        }
        if (leNode != null) {
            this.addChild(leNode);
        }
        if (val != null) {
            this.addChild(val);
        }
        if (expr != null) {
            this.addChild(expr);
        }
    }

    @Override
    public void addChild(Node child) {
        this.children.add(child);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
