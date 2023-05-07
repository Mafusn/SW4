package AST.Nodes;

import AST.SymbolTableFilling.SymbolTableFilling;
import AST.Types.Type;
import AST.Visitor;

public class IfStmt extends Node {

    public IfStmt(Node left, Node right){
        this.left = left;
        this.right = right;
    }

    public void accept(Visitor v){
        v.visit(this);
    }

    @Override
    public Type getType(SymbolTableFilling symbolTable) {
        return null;
    }

}
