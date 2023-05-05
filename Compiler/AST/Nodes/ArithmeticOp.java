package AST.Nodes;

import AST.SymbolTableFilling.SymbolTableFilling;
import AST.Types.Type;
import AST.Visitor;

public class ArithmeticOp extends Node {
    private Node left;
    private String operation;
    private Node right;
    private Type type;

    public ArithmeticOp(Node left, String operation, Node right){
        this.left = left;
        this.operation = operation;
        this.right = right;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public Type getType(SymbolTableFilling symbolTable) {
        if ((this.type == null)) {
            Type leftType = left.getType(symbolTable);
            Type rightType = right.getType(symbolTable);
            setType(leftType.getResultType(operation, rightType));
        }
        return this.type;
    }

    public Node getLeftOperand() {
        return left;
    }

    public Node getRightOperand() {
        return right;
    }

    public String getOperator() {
        return operation;
    }
}
