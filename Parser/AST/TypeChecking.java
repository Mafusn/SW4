package AST;

import AST.Types.*;

public class TypeChecking implements Visitor {
    private SymbolTableFilling symbolTable;

    public TypeChecking(SymbolTableFilling SymbolTable) {
        this.symbolTable = SymbolTable;
    }
    @Override
    public void visit(Assigning node) {
        // Get the type of the expression on the right-hand side of the assignment
        Type rhsType = node.getExpression().getType(symbolTable);

        // Get the symbol for the variable being assigned to
        Symbol symbol = symbolTable.lookup(node.getVariable());

        // Check that the types match
        if (!symbol.getType().isAssignable(rhsType)) {
            //throw new TypeMismatchException("Type mismatch in assignment");
            System.out.println("Type mismatch in assignment: " + node.getVariable());
        }
    }

    @Override
    public Type visit(BinOperator node) {
        Type leftType = node.getLeftOperand().accept(this);
        Type rightType = node.getRightOperand().accept(this);

        if (!(leftType.isEqual(rightType))) {
            System.out.println("Binary operator used with incompatible types");
            return null;
        }

        return leftType.getResultType(node.getOperator(), rightType);
    }

    @Override
    public void visit(Block node) {
        for (Node child : node.children) {
            child.accept(this);
        }
    }

    @Override
    public void visit(Bool node) {

    }

    @Override
    public void visit(BoolDcl node) {

    }

    @Override
    public Type visit(Computing node) {
        Type leftType = node.getLeftOperand().accept(this);
        Type rightType = node.getRightOperand().accept(this);

        if (!(leftType.isEqual(rightType))) {
            System.out.println("Computing operator used with incompatible types");
            return null;
        }

        if (leftType instanceof IntType && rightType instanceof IntType) {
            return new IntType();
        } else if (leftType instanceof FloatType && rightType instanceof FloatType) {
            return new FloatType();
        } else {
            System.out.println("Computing operator used with incompatible types");
            return null;
        }
    }

    @Override
    public void visit(FloatDcl node) {

    }

    @Override
    public void visit(FloatNum node) {

    }

    @Override
    public Type visit(Id node) {
        Symbol symbol = symbolTable.lookup(node.getName());
        if (symbol == null) {
            System.out.println("Undeclared variable: " + node.getName());
        } else {
            return symbol.getType();
        }
        return null;
    }

    @Override
    public void visit(If node) {
        Type conditionType = node.getCondition().accept(this);

        if (!(conditionType instanceof BooleanType)) {
            System.out.println("If statement condition must be boolean");
        }

        node.getThenBlock().accept(this);
    }

    @Override
    public void visit(IfElse node) {
        Type conditionType = node.getCondition().accept(this);

        if (!(conditionType instanceof BooleanType)) {
            System.out.println("If-else statement condition must be boolean");
        }

        node.getThenBlock().accept(this);
        node.getElseBlock().accept(this);
    }

    @Override
    public void visit(IntDcl node) {

    }

    @Override
    public void visit(IntNum node) {

    }

    @Override
    public Type visit(Not node) {
        Type exprType = node.getExpression().getType(symbolTable);
        if (!(exprType instanceof BooleanType)) {
            System.out.println("Type mismatch in Not operator");
        }
        return exprType;
    }

    @Override
    public void visit(Print node) {
        // System.out.println("Print er ikke blevet implementeret endnu");
    }

    @Override
    public void visit(Prog node) {
        for(Node n : node.children){
            n.accept(this);
        }
    }
}
