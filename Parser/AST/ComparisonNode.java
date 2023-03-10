package AST;

import java.util.ArrayList;

public class ComparisonNode extends Node {
    private ArrayList<Node> children;

    public ComparisonNode(TermNode term, ComparisonTailNode comparisonTail) {
        this.children = new ArrayList<>();

        if (term != null) {
            this.addChild(term);
        }
        if (comparisonTail != null) {
            this.addChild(comparisonTail);
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
