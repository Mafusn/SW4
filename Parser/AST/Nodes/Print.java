package AST.Nodes;

import AST.Nodes.Node;
import AST.SymbolTableFilling;
import AST.Types.Type;
import AST.Visitor;

public class Print extends Node {
    private String id;

    public Print(String i){
        id = i;
    }

    public void accept(Visitor v){
        v.visit(this);
    }

    @Override
    public Type getType(SymbolTableFilling symbolTable) {
        return null;
    }

    public String getId() {
        return id;
    }
}
