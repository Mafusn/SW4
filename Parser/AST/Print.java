package AST;

import AST.Types.Type;

public class Print extends Node {
    String id;

    public Print(String i){
        id = i;
    }

    public Type accept(Visitor v){v.visit(this);
        return null;
    }

    @Override
    public Type getType(SymbolTableFilling symbolTable) {
        return null;
    }

}
