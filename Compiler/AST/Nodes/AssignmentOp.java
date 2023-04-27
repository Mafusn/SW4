package AST.Nodes;

import AST.SymbolTableFilling.SymbolTableFilling;
import AST.Types.Type;
import AST.Visitor;

public class AssignmentOp extends Node {
    private String name;
    private Node child1;
    private Node child2;

    public AssignmentOp(String name, Node child1, Node child2){
        this.name = name;
        this.child1 = child1;
        this.child2 = child2;
    }

    public void accept(Visitor v){
        v.visit(this);
    }

    @Override
    public Type getType(SymbolTableFilling symbolTable) {
        return null;
    }

    public Node getDeclaration() {
        return child1;
    }

    public Node getExpression() {
        return child2;
    }

    public String getVariable() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssignmentOp assignmentOp = (AssignmentOp) o;
        return name.equals(assignmentOp.name) && child1.equals(assignmentOp.child1) && child2.equals(assignmentOp.child2);
    }
}
