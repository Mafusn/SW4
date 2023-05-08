package AST.Nodes;

import AST.SymbolTableFilling.SymbolTableFilling;
import AST.Types.Type;
import AST.Visitor;

public class NegationOp extends Node{
    private Type type;

    public NegationOp(Node left) {
        this.left = left;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public Type getType(SymbolTableFilling symbolTable) {
        if (this.type == null) {
            setType(left.getType(symbolTable));
        }
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
