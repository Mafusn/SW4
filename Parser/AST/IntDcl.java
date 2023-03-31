package AST;

import AST.Types.IntType;
import AST.Types.Type;

public class IntDcl extends Node {
    String id;

    public IntDcl(String id) {
        this.id = id;
    }

    public Type accept(Visitor v){
        v.visit(this);
        return new IntType();
    }

    @Override
    public Type getType(SymbolTableFilling symbolTable) {
        return symbolTable.lookup(id).getType();
    }
}
