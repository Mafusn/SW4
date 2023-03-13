package AST;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ConditionNode extends Node {
    private ArrayList<Node> children;

    public ConditionNode(Node comparison, Node conditionTail) {
        this.children = new ArrayList<>();

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
    public void addChild(Node child) {
        this.children.add(child);
    }
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
