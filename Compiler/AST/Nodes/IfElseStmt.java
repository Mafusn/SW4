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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IfElseStmt ifElse = (IfElseStmt) o;
        return condition.equals(ifElse.condition) && child1.equals(ifElse.child1) && child2.equals(ifElse.child2);
    }

}
