package AST;

public class Not extends Node{
    Node child;

    public Not(Node child) {
        this.child = child;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }


}
