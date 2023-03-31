package AST;

import AST.Types.Type;

public class Id extends Node {
    String id;
    Type type;

    public Id(String id){
        this.id = id;
    }

    public Type accept(Visitor v){v.visit(this);
        return null;
    }

    @Override
    public Type getType(SymbolTableFilling symbolTable) {
        return symbolTable.lookup(id).getType();
    }

    public String getName() {
        return id;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
