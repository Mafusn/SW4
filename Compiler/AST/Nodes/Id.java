package AST.Nodes;

import AST.SymbolTableFilling.SymbolTableFilling;
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
        if (this.type == null) {
            setType(symbolTable.lookup(id).getType());
        }
        return this.type;
    }

    public String getName() {
        return id;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
