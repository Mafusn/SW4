package AST;

public class StmtNode extends Node {
    public void accept(Visitor v) {
        v.visit(this);
    }

    public StmtNode() {
    }
}
