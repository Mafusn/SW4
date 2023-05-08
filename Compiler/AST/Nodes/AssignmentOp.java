package AST.Nodes;

import AST.OperationSet;
import AST.SymbolTableFilling.SymbolTableFilling;
import AST.Types.Type;
import AST.Visitor;

import java.util.Objects;

public class AssignmentOp extends Node {
    private String name;
    private String compAssOp;

    public AssignmentOp(String name, Node left, Node right){
        this.name = name;
        this.left = left;
        this.right = right;
        this.compAssOp = null;
    }

    public AssignmentOp(String name, Node child1, Node child2, String compAssOp){
        this.name = name;
        this.left = child1;
        this.right = child2;

        if (compAssOp.equals(OperationSet.PLUS.getOperation())){
            this.compAssOp = OperationSet.COMPASSPLUS.getOperation();
        }
        else if (compAssOp.equals("-")){
            this.compAssOp = OperationSet.COMPASSMINUS.getOperation();
        }
    }

    public void accept(Visitor v){
        v.visit(this);
    }

    @Override
    public Type getType(SymbolTableFilling symbolTable) {
        return null;
    }

    public String getVariable() {
        return name;
    }

    public String getCompAssOp() {
        return compAssOp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssignmentOp assignmentOp = (AssignmentOp) o;
        return name.equals(assignmentOp.name) && left.equals(assignmentOp.left) && right.equals(assignmentOp.right) && compAssOp.equals(assignmentOp.compAssOp);
    }
}
