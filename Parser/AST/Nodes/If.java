package AST.Nodes;

import AST.SymbolTableFilling.SymbolTableFilling;
import AST.Types.Type;
import AST.Visitor;

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
}
