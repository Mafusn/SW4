package AST;

import AST.Types.Type;

public abstract class Node {
    public abstract Type accept(Visitor v);
    public abstract Type getType(SymbolTableFilling symbolTable);
}
