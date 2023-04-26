package AST.Nodes;

import AST.SymbolTableFilling.SymbolTableFilling;
import AST.Types.Type;
import AST.Visitor;

import java.util.Objects;

public class If extends Node {
    private Node condition;
    private Node child1;

    public If(Node condition, Node child1){
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
        If anIf = (If) o;
        return condition.equals(anIf.condition) && child1.equals(anIf.child1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(condition, child1);
    }
}
