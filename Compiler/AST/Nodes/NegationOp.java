package AST.Nodes;

import AST.SymbolTableFilling.SymbolTableFilling;
import AST.Types.Type;
import AST.Visitor;

public class NegationOp extends Node{
    private Node child;
    private Type type;

    public NegationOp(Node child) {
        this.child = child;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public Type getType(SymbolTableFilling symbolTable) {
        if (this.type == null) {
            setType(child.getType(symbolTable));
        }
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Node getExpression() {
        return child;
    }
}
