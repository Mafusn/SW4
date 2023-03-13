package AST;

import java.util.ArrayList;

public class ConditionTailNode extends Node {
    private ArrayList<Node> children;

    public ConditionTailNode(Node orNode, Node comparison, Node conditionTail) {
        this.children = new ArrayList<>();

        if (orNode != null) {
            this.addChild(orNode);
        }
        if (comparison != null) {
            this.addChild(comparison);
        }
        if (conditionTail != null) {
            this.addChild(conditionTail);
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
