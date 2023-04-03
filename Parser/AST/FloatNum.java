package AST;

import AST.Types.FloatType;
import AST.Types.IntType;
import AST.Types.Type;

public class FloatNum extends Node {
    String value;

    public FloatNum(String value){
        this.value = value;
    }

    public Type accept(Visitor v){v.visit(this);
        return new FloatType();
    }

    @Override
    public Type getType(SymbolTableFilling symbolTable) {
        return new FloatType();
    }
}