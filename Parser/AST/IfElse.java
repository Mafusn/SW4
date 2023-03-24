package AST;

public class IfElse extends Node {
    Node condition;
    Node child1;
    Node child2;

    public IfElse(Node condition, Node child1, Node child2){
        this.condition = condition;
        this.child1 = child1;
        this.child2 = child2;
    }

    public void accept(Visitor v){v.visit(this);}
}
