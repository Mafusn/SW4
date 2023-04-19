package AST.Nodes;

import AST.Nodes.Node;
import AST.SymbolTableFilling;
import AST.Types.Type;
import AST.Visitor;

import java.util.ArrayList;;
public class Prog extends Node {
    private ArrayList<Node> children;

    public Prog() {
        this.children = new ArrayList<>();
    }

    public void addChild(Node child) {
        this.children.add(child);
    }

    public void accept(Visitor v){
        v.visit(this);
    }

    @Override
    public Type getType(SymbolTableFilling symbolTable) {
        return null;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }
}
