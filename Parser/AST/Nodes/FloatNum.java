package AST.Nodes;

import AST.SymbolTableFilling.SymbolTableFilling;
import AST.Types.FloatType;
import AST.Types.Type;
import AST.Visitor;

public class FloatNum extends Node {
    private float value;
    private Type type;

    public FloatNum(String value){
        this.value = Float.parseFloat(value);
        this.type = new FloatType();
    }

    public float getValue() {
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