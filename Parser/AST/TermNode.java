package AST;

import java.util.ArrayList;

public class TermNode extends Node {
    private ArrayList<Node> children;

    public TermNode(FactorNode factor, TermTailNode termTail) {
        this.children = new ArrayList<>();

        if (factor != null) {
            this.addChild(factor);
        }
        if (termTail != null) {
            this.addChild(termTail);
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
