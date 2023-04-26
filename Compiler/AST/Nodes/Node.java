package AST.Nodes;

import AST.SymbolTableFilling.SymbolTableFilling;
import AST.Types.Type;
import AST.Visitor;

public abstract class Node {
    public abstract void accept(Visitor v);
    public abstract Type getType(SymbolTableFilling symbolTable);
}
