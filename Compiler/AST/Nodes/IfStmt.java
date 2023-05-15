package AST.Nodes;

import AST.SymbolTableFilling.SymbolTableFilling;
import AST.Types.Type;
import AST.Visitor;

public class IfStmt extends Node {

    private Node condition;
    private Node child1;

    public IfStmt(Node condition, Node child1){
        this.condition = condition;
        this.child1 = child1;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IfStmt anIf = (IfStmt) o;
        return condition.equals(anIf.getCondition()) && child1.equals(anIf.child1);
    }

}
