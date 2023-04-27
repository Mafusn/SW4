package AST.Nodes;

import AST.SymbolTableFilling.SymbolTableFilling;
import AST.Types.Type;
import AST.Visitor;

public class IfElseStmt extends Node {
    private Node condition;
    private Node child1;
    private Node child2;

    public IfElseStmt(Node condition, Node child1, Node child2){
        this.condition = condition;
        this.child1 = child1;
        this.child2 = child2;
    }

    public void accept(Visitor v){
        v.visit(this);
    }

    @Override
    public Type getType(SymbolTableFilling symbolTable) {
        return null;
    }

    public Node getCondition() {
        return condition;
    }

    public Node getThenBlock() {
        return child1;
    }

    public Node getElseBlock() {
        return child2;
    }
}
