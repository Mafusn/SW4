package AST;

import AST.Types.FloatType;
import AST.Types.Type;

public class FloatDcl extends Node {
    String id;

    public FloatDcl(String id) {
        this.id = id;
    }

    public Type accept(Visitor v){
        v.visit(this);
        return new FloatType();
    }

    @Override
    public Type getType(SymbolTableFilling symbolTable) {
        return symbolTable.lookup(id).getType();
    }
}
