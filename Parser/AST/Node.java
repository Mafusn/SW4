package AST;

public abstract class Node {

    //public abstract void addChild(Node child);

    public abstract void accept(Visitor v);
}
