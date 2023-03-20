package AST;

public class BoolDcl extends Node {
    String id;

    public BoolDcl(String id) {
        this.id = id;
    }

    public void accept(Visitor v){
        v.visit(this);
    }
}