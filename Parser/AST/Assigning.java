package AST;

import AST.Types.Type;

public class Assigning extends Node {
    private String name;
    Node child1;
    Node child2;

    public Assigning(String name, Node child1, Node child2){
        this.name = name;
        this.child1 = child1;
        this.child2 = child2;
    }

    public Type accept(Visitor v){v.visit(this);
        return null;
    }

    @Override
    public Type getType(SymbolTableFilling symbolTable) {
        // Get the type of the expression being assigned
        Type exprType = child2.getType(symbolTable);

        // Get the type of the target of the assignment
        Type targetType = symbolTable.lookup(name).getType();

        // Check if the types are compatible
        if (!exprType.isAssignable(targetType)) {
            //throw new TypeCheckException("Incompatible types in assignment: " + exprType + " cannot be assigned to " + targetType);
            System.out.println("Incompatible types in assignment: " + exprType + " cannot be assigned to " + targetType);
        }

        // Return the type of the assignment
        return targetType;
    }

    public Node getExpression() {
        return child2;
    }

    public String getVariable() {
        return name;
    }
}
