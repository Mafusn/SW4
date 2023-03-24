package AST;

public class BinOperator extends Node {
    String operation;
    Node child1;
    Node child2;

    public BinOperator(String operation, Node child1, Node child2){
        this.operation = operation;
        this.child1 = child1;
        this.child2 = child2;
    }

    public void accept(Visitor v){v.visit(this);}
}
