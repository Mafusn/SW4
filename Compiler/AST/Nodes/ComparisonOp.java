package AST.Nodes;

import AST.SymbolTableFilling.SymbolTableFilling;
import AST.Types.BooleanType;
import AST.Types.Type;
import AST.Visitor;
public class ComparisonOp extends Node {
    private String operation;
    private Node child1;
    private Node child2;
    private Type type;

    public ComparisonOp(String operation, Node child1, Node child2){
        this.operation = operation;
        this.child1 = child1;
        this.child2 = child2;
        this.type = new BooleanType();
    }

    public void accept(Visitor v){
        v.visit(this);
    }

    @Override
    public Type getType(SymbolTableFilling symbolTable) {
        if (this.type == null) {
            Type leftType = child1.getType(symbolTable);
            Type rightType = child2.getType(symbolTable);
            setType(leftType.getResultType(operation, rightType));
        }
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComparisonOp comparisonOp = (ComparisonOp) o;
        return operation.equals(comparisonOp.operation) && child1.equals(comparisonOp.child1) && child2.equals(comparisonOp.child2);
    }
}
