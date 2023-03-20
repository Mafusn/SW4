package AST;

public class Print extends Node {
    String id;

    public Print(String i){
        id = i;
    }

    public void accept(Visitor v){v.visit(this);}
}
