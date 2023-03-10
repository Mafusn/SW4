package AST;

import java.util.ArrayList;

public class TermTailNode extends Node {
    private ArrayList<Node> children;

    public TermTailNode(PlusNode plus, MinusNode minus, FactorNode factor, TermTailNode termTail) {
        this.children = new ArrayList<>();

        if (plus != null) {
            this.addChild(plus);
        }
        if (minus != null) {
            this.addChild(minus);
        }
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
