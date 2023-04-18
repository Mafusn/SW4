package AST;

import AST.Types.Type;

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
    public void visit(Assigning node) {
        if (node.child1.getClass().getName().equals("AST.FloatDcl")) {
            node.child1.accept(this);
            sb.append(" = ");
            node.child2.accept(this);
        } else if (node.child1.getClass().getName().equals("AST.IntDcl")) {
            node.child1.accept(this);
            sb.append(" = ");
            node.child2.accept(this);
        } else if (node.child1.getClass().getName().equals("AST.BoolDcl")) {
            node.child1.accept(this);
            sb.append(" = ");
            node.child2.accept(this);
        } else {
            sb.append("\n");
            printIndent();
            node.child1.accept(this);
            sb.append(" = ");
            node.child2.accept(this);
        }
    }

    @Override
    public Type visit(BinOperator node) {
        node.child1.accept(this);
        sb.append(" " + node.operation + " ");
        node.child2.accept(this);
        return null;
    }

    @Override
    public void visit(Block node) {
        for(Node n : node.children){
            indent();
            n.accept(this);
            unindent();
        }
    }

    @Override
    public void visit(Bool node) {
        sb.append(node.value);
    }

    @Override
    public void visit(BoolDcl node) {
        sb.append("\n");
        printIndent();
        sb.append("boolean " + node.id);
    }

    @Override
    public Type visit(Computing node) {
        node.child1.accept(this);
        sb.append(" " + node.operation + " ");
        node.child2.accept(this);
        return null;
    }

    @Override
    public void visit(FloatDcl node) {
        sb.append("\n");
        printIndent();
        sb.append("float " + node.id);
    }

    @Override
    public void visit(FloatNum node) {
        sb.append(node.value);
    }

    @Override
    public Type visit(Id node) {
        sb.append(node.id);
        return null;
    }

    @Override
    public void visit(If node) {
        sb.append("\n");
        printIndent();
        sb.append("if (");
        node.condition.accept(this);
        sb.append(")");
        node.child1.accept(this);
    }

    @Override
    public void visit(IfElse node) {
        sb.append("\n");
        printIndent();
        sb.append("if (");
        node.condition.accept(this);
        sb.append(") {");
        node.child1.accept(this);
        sb.append("\n");
        printIndent();
        sb.append("} else {");
        node.child2.accept(this);
        sb.append("\n}");
    }

    @Override
    public void visit(IntDcl node) {
        sb.append("\n");
        printIndent();
        sb.append("int " + node.id);
    }

    @Override
    public void visit(IntNum node) {
        sb.append(node.value);
    }

    @Override
    public Type visit(Not node) {
        sb.append("!(");
        node.child.accept(this);
        sb.append(")");
        return null;
    }

    @Override
    public void visit(Print node) {
        sb.append("\n");
        printIndent();
        sb.append("print(" + node.id + ")");
    }

    @Override
    public void visit(Prog node) {
        for(Node n : node.children){
            n.accept(this);
        }
    }
}
