package AST;

public class If extends Node {
    Node condition;
    Node child1;

    public If(Node condition, Node child1){
        this.condition = condition;
        this.child1 = child1;
    }

    public void accept(Visitor v){v.visit(this);}
}
