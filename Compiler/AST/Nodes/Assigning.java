package AST.Nodes;

import AST.SymbolTableFilling.SymbolTableFilling;
import AST.Types.Type;
import AST.Visitor;

public class Assigning extends Node {
    private String name;
    private Node child1;
    private Node child2;

    public Assigning(String name, Node child1, Node child2){
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
        Assigning assigning = (Assigning) o;
        return name.equals(assigning.name) && child1.equals(assigning.child1) && child2.equals(assigning.child2);
    }
}
