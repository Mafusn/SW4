package AST;

import java.util.ArrayList;

public class ValNode extends Node {
    private ArrayList<Node> children;
    public ValNode(MinusNode minusNode, IntNode intNode, FloatNode floatNode) {
        this.children = new ArrayList<>();

        if (minusNode != null) {
            this.addChild(minusNode);
        }
        if (intNode != null) {
            this.addChild(intNode);
        }
        if (floatNode != null) {
            this.addChild(floatNode);
        }
    }

    public ValNode(TrueNode trueNode) {
        this.children = new ArrayList<>();

        if (trueNode != null) {
            this.addChild(trueNode);
        }
    }

    public ValNode(FalseNode falseNode) {
        this.children = new ArrayList<>();

        if (falseNode != null) {
            this.addChild(falseNode);
        }
    }

    public ValNode(LParenNode lParenNode, MinusNode minusNode, IntNode intNode, FloatNode floatNode, RParenNode rParenNode) {
        this.children = new ArrayList<>();

        if (lParenNode != null) {
            this.addChild(lParenNode);
        }
        if (minusNode != null) {
            this.addChild(minusNode);
        }
        if (intNode != null) {
            this.addChild(intNode);
        }
        if (floatNode != null) {
            this.addChild(floatNode);
        }
        if (rParenNode != null) {
            this.addChild(rParenNode);
        }
    }

    public ValNode(IdNode idNode) {
        this.children = new ArrayList<>();

        if (idNode != null) {
            this.addChild(idNode);
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
