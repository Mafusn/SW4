package AST;

import AST.Types.Type;

import java.util.ArrayList;;
public class Prog extends Node {
    ArrayList<Node> children;

    public Prog() {
        this.children = new ArrayList<>();
    }

    public void addChild(Node child) {
        this.children.add(child);
    }

    public Type accept(Visitor v){v.visit(this);
        return null;
    }

    @Override
    public Type getType(SymbolTableFilling symbolTable) {
        return null;
    }
}
