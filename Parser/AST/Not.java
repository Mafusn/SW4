package AST;

import AST.Types.Type;

public class Not extends Node{
    Node child;

    public Not(Node child) {
        this.child = child;
    }

    @Override
    public Type accept(Visitor v) {
        return v.visit(this);
    }

    @Override
    public Type getType(SymbolTableFilling symbolTable) {
        return null;
    }


    public Node getExpression() {
        return child;
    }
}
