package AST;

public class Id extends Node {
    String id;

    public Id(String id){
        this.id = id;
    }

    public void accept(Visitor v){v.visit(this);}
}
