package AST;

import AST.Nodes.*;
import AST.SymbolTableFilling.Symbol;
import AST.SymbolTableFilling.SymbolTableFilling;
import AST.Types.*;

import java.util.ArrayList;

public class TypeChecking implements Visitor {
    private final ArrayList<SymbolTableFilling> symbolTables;
    private int scopeLevel = 0;

    public TypeChecking(ArrayList<SymbolTableFilling> SymbolTables) {
        this.symbolTables = SymbolTables;
    }

    @Override
    public void visit(AssignmentOp node) {
        // Get the type of the expression on the right-hand side of the assignment
        Type rhsType = node.getRight().getType(symbolTables.get(scopeLevel));

        // Get the symbol for the variable being assigned to
        Symbol symbol = symbolTables.get(scopeLevel).lookup(node.getVariable());

        // Check that the types match
        if (!symbol.getType().isAssignable(rhsType)) {
            //throw new TypeMismatchException("Type mismatch in assignment");
            error("Type mismatch in assignment " + node.getVariable());
        }
    }

    @Override
    public void visit(ComparisonOp node) {
        Type leftType = node.getLeft().getType(symbolTables.get(scopeLevel));
        Type rightType = node.getRight().getType(symbolTables.get(scopeLevel));

        if (!(leftType.isEqual(rightType))) {
            error("Binary operator used with incompatible types");
        }

        leftType.getResultType(node.getOperator(), rightType);
    }

    @Override
    public void visit(Block node) {
        scopeLevel++;
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
    public void visit(ArithmeticOp node) {
        Type leftType = node.getLeft().getType(symbolTables.get(scopeLevel));
        Type rightType = node.getRight().getType(symbolTables.get(scopeLevel));

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
        Symbol symbol = symbolTables.get(scopeLevel).lookup(node.getName());
        if (symbol == null) {
            error("Undeclared variable " + node.getName());
        } else {
            node.setType(symbol.getType());
        }
    }

    @Override
    public void visit(IfStmt node) {
        Type conditionType = node.getLeft().getType(symbolTables.get(scopeLevel));

        if (!(conditionType instanceof BooleanType)) {
            error("If statement condition must be boolean");
        }

        node.getRight().accept(this);
    }

    @Override
    public void visit(IfElseStmt node) {
        Type conditionType = node.getCondition().getType(symbolTables.get(scopeLevel));

        if (!(conditionType instanceof BooleanType)) {
            error("If-else statement condition must be boolean");
        }

        node.getLeft().accept(this);
        node.getRight().accept(this);
    }

    @Override
    public void visit(IntDcl node) {

    }

    @Override
    public void visit(IntNum node) {

    }

    @Override
    public void visit(NegationOp node) {
        Type exprType = node.getLeft().getType(symbolTables.get(scopeLevel));
        if (!(exprType instanceof BooleanType)) {
            error("Type mismatch in Not operator");
        }
        node.setType(exprType);
    }

    @Override
    public void visit(Prog node) {
        for(Node n : node.getChildren()){
            n.accept(this);
        }
    }

    @Override
    public void visit(WhileLoop node) {
        Type conditionType = node.getLeft().getType(symbolTables.get(scopeLevel));

        if (!(conditionType instanceof BooleanType)) {
            error("While-loop statement is not of type boolean");
        }

        node.getRight().accept(this);
    }

    private void error(String message) {
        throw new Error(message);
    }
}
