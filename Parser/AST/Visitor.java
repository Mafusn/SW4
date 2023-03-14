package AST;

public interface Visitor {
    void visit(DclNode v);
    void visit(ProgNode v);
    void visit(StmtNode v);
    void visit(ExprNode v);
    void visit(ValNode v);
    void visit(IfNode v);
}
