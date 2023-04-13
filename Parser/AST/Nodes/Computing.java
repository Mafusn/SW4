package AST.Nodes;

import AST.SymbolTableFilling;
import AST.Types.Type;
import AST.Visitor;

public class Computing extends Node {
    private String operation;
    private Node child1;
    private Node child2;
    private Type type;

    public Computing(String operation, Node child1, Node child2){
        this.child1 = child1;
        this.child2 = child2;
        this.operation = operation;
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
            return child1.getType(symbolTable).getResultType(operation, child2.getType(symbolTable));
        } else {
            return this.type;
        }
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
}
