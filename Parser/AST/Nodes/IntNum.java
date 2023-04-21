package AST.Nodes;

import AST.SymbolTableFilling;
import AST.Types.IntType;
import AST.Types.Type;
import AST.Visitor;

public class IntNum extends Node {
    private int value;
    private Type type;

    public IntNum(String value){
        this.value = Integer.parseInt(value);
        this.type = new IntType();
    }

    public int getValue() {
        return value;
    }

    public void accept(Visitor v){
        v.visit(this);
    }

    @Override
    public Type getType(SymbolTableFilling symbolTable) {
        return this.type;
    }
}