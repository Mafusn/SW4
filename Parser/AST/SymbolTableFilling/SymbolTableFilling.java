package AST.SymbolTableFilling;

import AST.Nodes.*;
import AST.Types.*;
import AST.Visitor;

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
        node.getDeclaration().accept(this);
        node.getExpression().accept(this);
    }

    @Override
    public void visit(BinOperator node) {
        node.getLeftOperand().accept(this);
        node.getRightOperand().accept(this);
    }

    @Override
    public void visit(Block node) {
        for(Node n : node.getChildren()){
            n.accept(this);
        }
    }

    @Override
    public void visit(Bool node) {

    }

    @Override
    public void visit(BoolDcl node) {
        if (symbolTable.get(node.getId()) == null) {
            symbolTable.put(node.getId(), new Symbol(node.getId(), new BooleanType(), scopeLevel));
        } else {
            error("variable " + node.getId() + " is already declared");
        }
    }

    @Override
    public void visit(Computing node) {
        node.getLeftOperand().accept(this);
        node.getRightOperand().accept(this);
    }

    @Override
    public void visit(FloatDcl node) {
        if (symbolTable.get(node.getId()) == null) {
            symbolTable.put(node.getId(), new Symbol(node.getId(), new FloatType(), scopeLevel));
        } else {
            error("variable " + node.getId() + " is already declared");
        }
    }

    @Override
    public void visit(FloatNum node) {

    }

    @Override
    public void visit(Id node) {
        if (symbolTable.get(node.getName()) == null) {
            error("variable " + node.getName() + " is not declared");
        }
    }

    @Override
    public void visit(If node) {
        node.getCondition().accept(this);
        node.getThenBlock().accept(this);
    }

    @Override
    public void visit(IfElse node) {
        node.getCondition().accept(this);
        node.getThenBlock().accept(this);
        node.getElseBlock().accept(this);
    }

    @Override
    public void visit(IntDcl node) {
        if (symbolTable.get(node.getId()) == null) {
            symbolTable.put(node.getId(), new Symbol(node.getId(), new IntType(), scopeLevel));
        } else {
            error("variable " + node.getId() + " is already declared");
        }
    }

    @Override
    public void visit(IntNum node) {

    }

    @Override
    public void visit(Not node) {
        node.getExpression().accept(this);
    }

    @Override
    public void visit(Print node) {

    }

    @Override
    public void visit(Prog node) {
        for(Node n : node.getChildren()){
            n.accept(this);
        }
        System.out.println();
    }

    private void error(String message) {
        throw new Error(message);
    }

}
