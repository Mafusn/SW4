package AST;

import java.util.ArrayList;

public class FactorNode extends Node {
    private ArrayList<Node> children;

    public FactorNode(IntNode intNode) {
        this.children = new ArrayList<>();

        if (intNode != null) {
            this.addChild(intNode);
        }
    }

    public FactorNode(FloatNode floatNode) {
        this.children = new ArrayList<>();

        if (floatNode != null) {
            this.addChild(floatNode);
        }
    }

    public FactorNode(LParenNode lParen, TermNode term, RParenNode rParen) {
        this.children = new ArrayList<>();

        if (lParen != null) {
            this.addChild(lParen);
        }
        if (term != null) {
            this.addChild(term);
        }
        if (rParen != null) {
            this.addChild(rParen);
        }
    }

    public FactorNode(IdNode id) {
        this.children = new ArrayList<>();

        if (id != null) {
            this.addChild(id);
        }
    }

    public FactorNode(TrueNode trueNode) {
        this.children = new ArrayList<>();

        if (trueNode != null) {
            this.addChild(trueNode);
        }
    }

    public FactorNode(FalseNode falseNode) {
        this.children = new ArrayList<>();

        if (falseNode != null) {
            this.addChild(falseNode);
        }
    }

    @Override
    public void addChild(Node child) {
        this.children.add(child);
    }

    @Override
    public void accept(Visitor v) {

    }
}
