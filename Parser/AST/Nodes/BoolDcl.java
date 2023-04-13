package AST.Nodes;

import AST.SymbolTableFilling;
import AST.Types.BooleanType;
import AST.Types.Type;
import AST.Visitor;

public class BoolDcl extends Node {
    private String id;
    private Type type;

    public BoolDcl(String id) {
        this.id = id;
        this.type = new BooleanType();
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