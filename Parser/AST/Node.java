package AST;

import java.util.Hashtable;

public abstract class Node {
    public final static int
    FLTTYPE   = 0,
    INTTYPE   = 1,
    BOOLTYPE  = 2;

    public abstract void accept(Visitor v);
}
