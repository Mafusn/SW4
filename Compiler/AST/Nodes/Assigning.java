package AST.Nodes;

import AST.SymbolTableFilling.SymbolTableFilling;
import AST.Types.Type;
import AST.Visitor;

public class Assigning extends Node {
    private String name;
    private Node child1;
    private Node child2;
    private Type type;

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
        //************** when is this being used? ****************

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
        setType(targetType);
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
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
}
