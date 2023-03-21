package AST;

public class FloatNum extends Node {
    String value;

    public FloatNum(String value){
        this.value = value;
    }

    public void accept(Visitor v){v.visit(this);}
}