package AST;

import java.util.ArrayList;

public class ConditionTailNode extends Node {
    private ArrayList<Node> children;

    public ConditionTailNode(ANDNode andNode, ComparisonNode comparison, ConditionTailNode conditionTail) {
        this.children = new ArrayList<>();

        if (andNode != null) {
            this.addChild(andNode);
        }
        if (comparison != null) {
            this.addChild(comparison);
        }
        if (conditionTail != null) {
            this.addChild(conditionTail);
        }
    }

    public ConditionTailNode(ORNode orNode, ComparisonNode comparison, ConditionTailNode conditionTail) {
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

    @Override
    public void accept(Visitor v) {

    }

    @Override
    public void addChild(Node child) {
        this.children.add(child);
    }
}
