package AST.Nodes;

import AST.SymbolTableFilling;
import AST.Types.Type;
import AST.Visitor;

public class Id extends Node {
    private String id;
    private Type type;

    public Id(String id){
        this.id = id;
    }

    public void accept(Visitor v){
        v.visit(this);
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
