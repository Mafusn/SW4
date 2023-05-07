package AST.Nodes;

import AST.SymbolTableFilling.SymbolTableFilling;
import AST.Types.BooleanType;
import AST.Types.Type;
import AST.Visitor;

public class ComparisonOp extends Node {
    private String operation;
    private Type type;

    public ComparisonOp(Node left, String operation, Node right) {
        this.left = left;
        this.operation = operation;
        this.right = right;
        this.type = new BooleanType();
    }

    public void accept(Visitor v){
        v.visit(this);
    }

    @Override
    public Type getType(SymbolTableFilling symbolTable) {
        if (this.type == null) {
            Type leftType = left.getType(symbolTable);
            Type rightType = right.getType(symbolTable);
            setType(leftType.getResultType(operation, rightType));
        }
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getOperator() {
        return operation;
    }
}
