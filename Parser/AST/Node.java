package AST;

import java.util.ArrayList;

public abstract class Node {
    public abstract void addChild(Node child);

    public abstract void accept(Visitor v);
}

