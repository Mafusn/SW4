package AST;

public class IntNum extends Node {
    String value;

    public IntNum(String value){
        this.value = value;
    }

    public void accept(Visitor v){v.visit(this);}
}
