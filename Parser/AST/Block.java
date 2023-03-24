package AST;

import java.util.ArrayList;

public class Block extends Node {
    ArrayList<Node> children;

    public Block() {
        this.children = new ArrayList<>();
    }

    public void addChild(Node child) {
        this.children.add(child);
    }

    public void accept(Visitor v){v.visit(this);}
}
