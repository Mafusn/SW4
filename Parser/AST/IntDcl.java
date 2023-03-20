package AST;

public class IntDcl extends Node {
    String id;

    public IntDcl(String id) {
        this.id = id;
    }

    public void accept(Visitor v){
        v.visit(this);
    }
}
