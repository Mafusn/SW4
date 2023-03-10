package AST;

import java.util.ArrayList;

public class ComparisonTailNode extends Node {
    private ArrayList<Node> children;

    public ComparisonTailNode(EQNode eq, NENode ne, LTNode lt, LENode le, GTNode gt, GENode ge, TermNode term, ComparisonTailNode comparisonTail) {
        this.children = new ArrayList<>();

        if (eq != null) {
            this.addChild(eq);
        }
        if (ne != null) {
            this.addChild(ne);
        }
        if (lt != null) {
            this.addChild(lt);
        }
        if (le != null) {
            this.addChild(le);
        }
        if (gt != null) {
            this.addChild(gt);
        }
        if (ge != null) {
            this.addChild(ge);
        }
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
