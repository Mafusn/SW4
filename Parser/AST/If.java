package AST;

import AST.Types.Type;

public class If extends Node {
    Node condition;
    Node child1;

    public If(Node condition, Node child1){
        this.condition = condition;
        this.child1 = child1;
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
}
