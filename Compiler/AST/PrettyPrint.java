package AST;

import AST.Nodes.*;

public class PrettyPrint implements Visitor {
    private StringBuilder sb = new StringBuilder();
    private int indentLevel = 0;

    private void indent() {
        this.indentLevel++;
    }

    private void unindent() {
        this.indentLevel--;
    }

    private void printIndent() {
        for (int i = 0; i < this.indentLevel; i++) {
            sb.append("    ");
        }
    }

    public String getResult() {
        return sb.toString();
    }

    @Override
    public void visit(AssignmentOp node) {
        switch (node.getDeclaration().getClass().getSimpleName()) {
            case "FloatDcl", "IntDcl", "BoolDcl" -> {
                node.getDeclaration().accept(this);
                sb.append(" = ");
                node.getExpression().accept(this);
            }
            default -> {
                sb.append("\n");
                printIndent();
                node.getDeclaration().accept(this);
                sb.append(" = ");
                node.getExpression().accept(this);
            }
        }
    }

    @Override
    public void visit(ComparisonOp node) {
        node.getLeftOperand().accept(this);
        sb.append(" " + node.getOperator() + " ");
        node.getRightOperand().accept(this);
    }

    @Override
    public void visit(Block node) {
        for(Node n : node.getChildren()){
            indent();
            n.accept(this);
            unindent();
        }
    }

    @Override
    public void visit(Bool node) {
        sb.append(node.getValue());
    }

    @Override
    public void visit(BoolDcl node) {
        sb.append("\n");
        printIndent();
        sb.append("boolean " + node.getId());
    }

    @Override
    public void visit(ArithmeticOp node) {
        node.getLeftOperand().accept(this);
        sb.append(" " + node.getOperator() + " ");
        node.getRightOperand().accept(this);
    }

    @Override
    public void visit(FloatDcl node) {
        sb.append("\n");
        printIndent();
        sb.append("float " + node.getId());
    }

    @Override
    public void visit(FloatNum node) {
        sb.append(node.getValue());
    }

    @Override
    public void visit(Id node) {
        sb.append(node.getName());
    }

    @Override
    public void visit(IfStmt node) {
        sb.append("\n");
        printIndent();
        sb.append("if(");
        node.getCondition().accept(this);
        sb.append(")");
        node.getThenBlock().accept(this);
    }

    @Override
    public void visit(IfElseStmt node) {
        sb.append("\n");
        printIndent();
        sb.append("if(");
        node.getCondition().accept(this);
        sb.append(")");
        node.getThenBlock().accept(this);
        sb.append("\n");
        printIndent();
        sb.append("else");
        node.getElseBlock().accept(this);
    }

    @Override
    public void visit(IntDcl node) {
        sb.append("\n");
        printIndent();
        sb.append("int " + node.getId());
    }

    @Override
    public void visit(IntNum node) {
        sb.append(node.getValue());
    }

    @Override
    public void visit(NegationOp node) {
        sb.append("!(");
        node.getExpression().accept(this);
        sb.append(")");
    }

    @Override
    public void visit(Prog node) {
        for(Node n : node.getChildren()){
            n.accept(this);
        }
    }
}
