package AST;

import AST.Types.IntType;
import AST.Types.Type;

public class IntNum extends Node {
    String value;

    public IntNum(String value){
        this.value = value;
    }

    public Type accept(Visitor v){v.visit(this);
        return new IntType();
    }

    @Override
    public Type getType(SymbolTableFilling symbolTable) {
        return new IntType();
    }
}
