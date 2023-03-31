package AST;

import AST.Types.Type;

public class Computing extends Node {
    String operation;
    Node child1;
    Node child2;

    public Computing(String operation, Node child1, Node child2){
        this.child1 = child1;
        this.child2 = child2;
        this.operation = operation;
    }

    public Type accept(Visitor v) {
        v.visit(this);
        return null;
    }

    @Override
    public Type getType(SymbolTableFilling symbolTable) {
        System.out.println(child1.getType(symbolTable) + " " + operation + " " + child2.getType(symbolTable));
        return child1.getType(symbolTable).getResultType(operation, child2.getType(symbolTable));
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
