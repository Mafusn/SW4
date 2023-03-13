package AST;

import java.util.ArrayList;

public class ValNode extends Node {
    private ArrayList<Node> children;
    public ValNode(Node minusNode, Node intNode, Node floatNode) {
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

    public ValNode(Node trueNode) {
        this.children = new ArrayList<>();

        if (trueNode != null) {
            this.addChild(trueNode);
        }
    }



    public ValNode(Node lParenNode, Node minusNode, Node intNode, Node floatNode, Node rParenNode) {
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
    

    public void accept(Visitor v) {
        v.visit(this);
    }
    @Override
    public void addChild(Node child) {
        this.children.add(child);
    }
}
