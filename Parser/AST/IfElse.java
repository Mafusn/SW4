package AST;

import AST.Types.Type;

public class IfElse extends Node {
    Node condition;
    Node child1;
    Node child2;

    public IfElse(Node condition, Node child1, Node child2){
        this.condition = condition;
        this.child1 = child1;
        this.child2 = child2;
    }

    public Type accept(Visitor v){v.visit(this);
        return null;
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
