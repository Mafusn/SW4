package AST.Nodes;

import AST.SymbolTableFilling.SymbolTableFilling;
import AST.Types.Type;
import AST.Visitor;

public class WhileLoop extends Node {
    Node condition;
    Node block;

    public WhileLoop(Node condition, Node block) {
        this.condition = condition;
        this.block = block;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public Type getType(SymbolTableFilling symbolTable) {
        return null;
    }

    public Node getCondition() {
        return condition;
    }

    public Node getBlock() {
        return block;
    }
}
