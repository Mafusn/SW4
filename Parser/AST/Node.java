package AST;

public abstract class Node {
    public Node() {
    }
    public abstract void accept(Visitor v);
}

