package AST;

import AST.Types.BooleanType;
import AST.Types.Type;

public class BinOperator extends Node {
    String operation;
    Node child1;
    Node child2;

    public BinOperator(String operation, Node child1, Node child2){
        this.operation = operation;
        this.child1 = child1;
        this.child2 = child2;
    }

    public Type accept(Visitor v){v.visit(this);
        return new BooleanType();
    }

    @Override
    public Type getType(SymbolTableFilling symbolTable) {
        Type leftType = child1.getType(symbolTable);
        Type rightType = child2.getType(symbolTable);
        return leftType.getResultType(operation, rightType);
    }

    public Node getLeftOperand() {
        return child1;
    }

    public Node getRightOperand() {
        return child2;
    }

    public String getOperator() {
        return operation;
    }
}
