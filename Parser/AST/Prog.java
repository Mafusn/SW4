package AST;

import java.util.ArrayList;;
public class Prog extends Node {
    ArrayList<Node> children;

    public Prog() {
        this.children = new ArrayList<>();
    }

    public void addChild(Node child) {
        this.children.add(child);
    }

    public void accept(Visitor v){v.visit(this);}
}
