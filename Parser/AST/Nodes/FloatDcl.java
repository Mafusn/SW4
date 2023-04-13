package AST.Nodes;

import AST.SymbolTableFilling;
import AST.Types.FloatType;
import AST.Types.Type;
import AST.Visitor;

public class FloatDcl extends Node {
    private String id;
    private Type type;

    public FloatDcl(String id) {
        this.id = id;
        this.type = new FloatType();
    }

    public void accept(Visitor v){
        v.visit(this);
    }

    public String getId() {
        return id;
    }

    @Override
    public Type getType(SymbolTableFilling symbolTable) {
        return this.type;
    }
}
