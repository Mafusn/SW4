package AST;

public class FloatDcl extends Node {
    String id;

    public FloatDcl(String id) {
        this.id = id;
    }

    public void accept(Visitor v){
        v.visit(this);
    }
}
