package AST;

import java.util.ArrayList;

public class TypeNode extends Node {
    private Node child;
    public TypeNode(IntDclNode intDclNode) {
        this.child = null;

        if (intDclNode != null) {
            this.addChild(intDclNode);
        }
    }

    public TypeNode(FloatDclNode floatDclNode) {
        this.child = null;

        if (floatDclNode != null) {
            this.addChild(floatDclNode);
        }
    }

    public Node getChild() {
        return child;
    }

    public TypeNode(BoolDclNode boolDclNode) {
        this.child = null;

        if (boolDclNode != null) {
            this.addChild(boolDclNode);
        }
    }

    @Override
    public void addChild(Node child) {
        this.child = child;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
