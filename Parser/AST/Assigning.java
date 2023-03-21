package AST;

public class Assigning extends Node {
    Node child1;
    Node child2;

    public Assigning(Node child1, Node child2){
        this.child1 = child1;
        this.child2 = child2;
    }

    public void accept(Visitor v){v.visit(this);}
}
