package AST;

import AST.Types.BooleanType;
import AST.Types.Type;

public class BoolDcl extends Node {
    String id;

    public BoolDcl(String id) {
        this.id = id;
    }

    public Type accept(Visitor v){
        v.visit(this);
        return new BooleanType();
    }

    @Override
    public Type getType(SymbolTableFilling symbolTable) {
        return symbolTable.lookup(id).getType();
    }
}