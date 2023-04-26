package AST;

import AST.Nodes.*;
import AST.SymbolTableFilling.Symbol;
import AST.SymbolTableFilling.SymbolTableFilling;
import AST.Types.*;

public class TypeChecking implements Visitor {
    private final SymbolTableFilling symbolTable;

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
            error("Type mismatch in assignment " + node.getVariable());
        }
    }

    @Override
    public void visit(BinOperator node) {
        Type leftType = node.getLeftOperand().getType(this.symbolTable);
        Type rightType = node.getRightOperand().getType(this.symbolTable);

        if (!(leftType.isEqual(rightType))) {
            error("Binary operator used with incompatible types");
        }

        leftType.getResultType(node.getOperator(), rightType);
    }

    @Override
    public void visit(Block node) {
        for (Node child : node.getChildren()) {
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
    public void visit(Computing node) {
        Type leftType = node.getLeftOperand().getType(this.symbolTable);
        Type rightType = node.getRightOperand().getType(this.symbolTable);

        if (!(leftType.isEqual(rightType))) {
            error("Computing operator used with incompatible types");
        }

        if (leftType instanceof IntType && rightType instanceof IntType) {
            node.setType(new IntType());
        } else if (leftType instanceof FloatType && rightType instanceof FloatType) {
            node.setType(new FloatType());
        } else {
            error("Computing operator used with incompatible types");
        }
    }

    @Override
    public void visit(FloatDcl node) {

    }

    @Override
    public void visit(FloatNum node) {

    }

    @Override
    public void visit(Id node) {
        Symbol symbol = symbolTable.lookup(node.getName());
        if (symbol == null) {
            error("Undeclared variable " + node.getName());
        } else {
            node.setType(symbol.getType());
        }
    }

    @Override
    public void visit(If node) {
        Type conditionType = node.getCondition().getType(this.symbolTable);

        if (!(conditionType instanceof BooleanType)) {
            error("If statement condition must be boolean");
        }

        node.getThenBlock().accept(this);
    }

    @Override
    public void visit(IfElse node) {
        Type conditionType = node.getCondition().getType(this.symbolTable);

        if (!(conditionType instanceof BooleanType)) {
            error("If-else statement condition must be boolean");
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
    public void visit(Not node) {
        Type exprType = node.getExpression().getType(symbolTable);
        if (!(exprType instanceof BooleanType)) {
            error("Type mismatch in Not operator");
        }
        node.setType(exprType);
    }

    @Override
    public void visit(Print node) {
        // System.out.println("Print er ikke blevet implementeret endnu");
    }

    @Override
    public void visit(Prog node) {
        for(Node n : node.getChildren()){
            n.accept(this);
        }
    }

    private void error(String message) {
        throw new Error(message);
    }
}
