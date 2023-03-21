package AST;

public class Bool extends Node {
    String value;

    public Bool(String value){
        this.value = value;
    }

    public void accept(Visitor v){v.visit(this);}
}
