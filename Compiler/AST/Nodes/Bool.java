package AST.Nodes;

import AST.SymbolTableFilling.SymbolTableFilling;
import AST.Types.BooleanType;
import AST.Types.Type;
import AST.Visitor;

public class Bool extends Node {
    private boolean value;
    private Type type;

    public Bool(String value){
        this.value = Boolean.parseBoolean(value);
        this.type = new BooleanType();
    }

    public void accept(Visitor v){
        v.visit(this);
    }

    @Override
    public Type getType(SymbolTableFilling symbolTable) {
        return this.type;
    }

    public boolean getValue() {
        return value;
    }
}
