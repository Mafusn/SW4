package AST;

import AST.Types.BooleanType;
import AST.Types.Type;

public class Bool extends Node {
    String value;

    public Bool(String value){
        this.value = value;
    }

    public Type accept(Visitor v){v.visit(this);
        return new BooleanType();
    }

    @Override
    public Type getType(SymbolTableFilling symbolTable) {
        return new BooleanType();
    }
}
