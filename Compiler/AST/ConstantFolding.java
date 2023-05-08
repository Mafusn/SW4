package AST;

import AST.Nodes.*;

public class ConstantFolding implements Visitor {
    @Override
    public void visit(AssignmentOp node) {
        node.getLeft().setParent(node);
        node.getLeft().accept(this);
        node.getRight().setParent(node);
        node.getRight().accept(this);
    }

    @Override
    public void visit(ComparisonOp node) {
        node.getLeft().setParent(node);
        node.getLeft().accept(this);
        node.getRight().setParent(node);
        node.getRight().accept(this);

        boolean foldedValue = false;
        String operator = node.getOperator();

        if (node.getLeft() instanceof IntNum && node.getRight() instanceof IntNum) {
            int leftValueInt = ((IntNum) node.getLeft()).getValue();
            int rightValueInt = ((IntNum) node.getRight()).getValue();

            if (operator.equals(OperationSet.LESSTHAN.getOperation())) {
                foldedValue = leftValueInt < rightValueInt;
            } else if (operator.equals(OperationSet.GREATERTHAN.getOperation())) {
                foldedValue = leftValueInt > rightValueInt;
            } else if (operator.equals(OperationSet.GREATEREQUAL.getOperation())) {
                foldedValue = leftValueInt >= rightValueInt;
            } else if (operator.equals(OperationSet.LESSEQUAL.getOperation())) {
                foldedValue = leftValueInt <= rightValueInt;
            } else if (operator.equals(OperationSet.EQUAL.getOperation())) {
                foldedValue = leftValueInt == rightValueInt;
            } else if (operator.equals(OperationSet.NOTEQUAL.getOperation())) {
                foldedValue = leftValueInt != rightValueInt;
            } else {
                return;
            }

            Node newChild = new Bool(foldedValue);
            Node parent = node.getParent();
            if (parent instanceof AssignmentOp) {
                parent.setRight(newChild);
            }
            else if (!(parent.getLeft() instanceof Bool)) {
                parent.setLeft(newChild);
            } else {
                parent.setRight(newChild);
            }

        } else if (node.getLeft() instanceof FloatNum && node.getRight() instanceof FloatNum) {
            float leftValueFloat = ((FloatNum) node.getLeft()).getValue();
            float rightValueFloat = ((FloatNum) node.getRight()).getValue();

            if (operator.equals(OperationSet.LESSTHAN.getOperation())) {
                foldedValue = leftValueFloat < rightValueFloat;
            } else if (operator.equals(OperationSet.GREATERTHAN.getOperation())) {
                foldedValue = leftValueFloat > rightValueFloat;
            } else if (operator.equals(OperationSet.GREATEREQUAL.getOperation())) {
                foldedValue = leftValueFloat >= rightValueFloat;
            } else if (operator.equals(OperationSet.LESSEQUAL.getOperation())) {
                foldedValue = leftValueFloat <= rightValueFloat;
            } else if (operator.equals(OperationSet.EQUAL.getOperation())) {
                foldedValue = leftValueFloat == rightValueFloat;
            } else if (operator.equals(OperationSet.NOTEQUAL.getOperation())) {
                foldedValue = leftValueFloat != rightValueFloat;
            } else {
                return;
            }

            Node newChild = new Bool(foldedValue);
            Node parent = node.getParent();
            if (parent instanceof AssignmentOp) {
                parent.setRight(newChild);
            }
            else if (!(parent.getLeft() instanceof Bool)) {
                parent.setLeft(newChild);
            } else {
                parent.setRight(newChild);
            }

        } else if (node.getLeft() instanceof Bool || node.getRight() instanceof Bool) {
            boolean leftValueBool = ((Bool) node.getLeft()).getValue();
            boolean rightValueBool = ((Bool) node.getRight()).getValue();

            if (operator.equals(OperationSet.EQUAL.getOperation())) {
                foldedValue = leftValueBool == rightValueBool;
            } else if (operator.equals(OperationSet.NOTEQUAL.getOperation())) {
                foldedValue = leftValueBool != rightValueBool;
            } else if (operator.equals(OperationSet.OR.getOperation())) {
                foldedValue = leftValueBool || rightValueBool;
            } else if (operator.equals(OperationSet.AND.getOperation())) {
                foldedValue = leftValueBool && rightValueBool;
            } else {
                return;
            }

            Node newChild = new Bool(foldedValue);
            Node parent = node.getParent();
            if (parent instanceof AssignmentOp) {
                parent.setRight(newChild);
            }
            else if (!(parent.getLeft() instanceof Bool)) {
                parent.setLeft(newChild);
            } else {
                parent.setRight(newChild);
            }
        }
    }

    @Override
    public void visit(Block node) {
        for (Node n : node.getChildren()) {
            n.accept(this);
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
        node.getLeft().setParent(node);
        node.getLeft().accept(this);
        node.getRight().setParent(node);
        node.getRight().accept(this);

        Node newChild;
        String operator = node.getOperator();

        if (node.getLeft() instanceof IntNum && node.getRight() instanceof IntNum) {
            int leftValue = ((IntNum) node.getLeft()).getValue();
            int rightValue = ((IntNum) node.getRight()).getValue();

            int foldedValue;

            if (operator.equals(OperationSet.PLUS.getOperation())) {
                foldedValue = leftValue + rightValue;
            } else if (operator.equals(OperationSet.MINUS.getOperation())) {
                foldedValue = leftValue - rightValue;
            } else {
                return;
            }

            newChild = new IntNum(foldedValue);
            Node parent = node.getParent();
            if (parent instanceof AssignmentOp) {
                parent.setRight(newChild);
            }
            else if (!(parent.getLeft() instanceof IntNum)) {
                parent.setLeft(newChild);
            } else {
                parent.setRight(newChild);
            }
        }
        if (node.getLeft() instanceof FloatNum && node.getRight() instanceof FloatNum) {
            float leftValue = ((FloatNum) node.getLeft()).getValue();
            float rightValue = ((FloatNum) node.getRight()).getValue();

            float foldedValue;

            if (operator.equals(OperationSet.PLUS.getOperation())) {
                foldedValue = leftValue + rightValue;
            } else if (operator.equals(OperationSet.MINUS.getOperation())) {
                foldedValue = leftValue - rightValue;
            } else {
                return;
            }

            newChild = new FloatNum(foldedValue);
            Node parent = node.getParent();
            if (parent instanceof AssignmentOp) {
                parent.setRight(newChild);
            }
            else if (!(parent.getLeft() instanceof FloatNum)) {
                parent.setLeft(newChild);
            } else {
                parent.setRight(newChild);
            }
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

    }

    @Override
    public void visit(IfStmt node) {
        node.getLeft().setParent(node);
        node.getLeft().accept(this);
        node.getRight().setParent(node);
        node.getRight().accept(this);
    }

    @Override
    public void visit(IfElseStmt node) {
        node.getCondition().setParent(node);
        node.getCondition().accept(this);
        node.getLeft().setParent(node);
        node.getLeft().accept(this);
        node.getRight().setParent(node);
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
        if (node.getLeft() instanceof Bool) {
            boolean leftValue = ((Bool) node.getLeft()).getValue();

            Node newChild = new Bool(!leftValue);
            Node parent = node.getParent();
            if (parent instanceof AssignmentOp) {
                parent.setRight(newChild);
            }
            else if (!(parent.getLeft() instanceof Bool)) {
                parent.setLeft(newChild);
            } else {
                parent.setRight(newChild);
            }
        }
    }

    @Override
    public void visit(Prog node) {
        for (Node n : node.getChildren()) {
            n.accept(this);
        }
    }

    @Override
    public void visit(WhileLoop node) {
        node.getLeft().setParent(node);
        node.getLeft().accept(this);
        node.getRight().setParent(node);
        node.getRight().accept(this);
    }

    @Override
    public void visit(PointerDcl node) {

    }
}
