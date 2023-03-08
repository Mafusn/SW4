package AST;

public abstract class Visitor {
    public Visitor() {
    }

    public void visit(DclNode v) {
        v.accept(this);
    }
    public void visit(ProgNode v) {
        v.accept(this);
    }
    public void visit(StmtNode v) {
        v.accept(this);
    }
    public void visit(ExprNode v) {
        v.accept(this);
    }
    public void visit(ValNode v) {
        v.accept(this);
    }
}
