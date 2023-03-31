package AST;

import AST.Types.BooleanType;
import AST.Types.FloatType;
import AST.Types.IntType;
import AST.Types.Type;

import java.util.HashMap;
import java.util.Map;

public class SymbolTableFilling implements Visitor {

    private Map<String,Symbol> symbolTable = new HashMap<>();

    public Map<String, Symbol> getSymbolTable() {
        return symbolTable;
    }

    public Symbol lookup(String id) {
        return symbolTable.get(id);
    }

    private int scopeLevel = 0;

    @Override
    public void visit(Assigning node) {
        node.child1.accept(this);
        node.child2.accept(this);
    }

    @Override
    public Type visit(BinOperator node) {

        return null;
    }

    @Override
    public void visit(Block node) {
        for(Node n : node.children){
            n.accept(this);
        }
    }

    @Override
    public void visit(Bool node) {

    }

    @Override
    public void visit(BoolDcl node) {
        if (symbolTable.get(node.id) == null) {
            symbolTable.put(node.id, new Symbol(node.id, new BooleanType(), scopeLevel));
        } else {
            error("variable " + node.id + " is already declared");
        }
    }

    @Override
    public Type visit(Computing node) {
        return null;
    }

    @Override
    public void visit(FloatDcl node) {
        if (symbolTable.get(node.id) == null) {
            symbolTable.put(node.id, new Symbol(node.id, new FloatType(), scopeLevel));
        } else {
            error("variable " + node.id + " is already declared");
        }
    }

    @Override
    public void visit(FloatNum node) {

    }

    @Override
    public void visit(Id node) {

    }

    @Override
    public void visit(If node) {
        node.child1.accept(this);
    }

    @Override
    public void visit(IfElse node) {
        node.child1.accept(this);
        node.child2.accept(this);
    }

    @Override
    public void visit(IntDcl node) {
        if (symbolTable.get(node.id) == null) {
            symbolTable.put(node.id, new Symbol(node.id, new IntType(), scopeLevel));
        } else {
            error("variable " + node.id + " is already declared");
        }
    }

    @Override
    public void visit(IntNum node) {

    }

    @Override
    public void visit(Not node) {

    }

    @Override
    public void visit(Print node) {

    }

    @Override
    public void visit(Prog node) {
        for(Node n : node.children){
            n.accept(this);
        }
        System.out.println();
    }

    private void error(String message) {
        throw new Error(message);
    }

}
