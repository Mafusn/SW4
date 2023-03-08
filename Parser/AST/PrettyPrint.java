package AST;

public class PrettyPrint extends Visitor {
    public void visit(ProgNode v) {
        System.out.println("ProgNode");
    }
    public void visit(DclNode v) {
        System.out.println("  DclNode");
    }
    public void visit(StmtNode v) {
        System.out.println("  StmtNode");
    }
    public void visit(ExprNode v) {
        System.out.println("    ExprNode");
    }
    public void visit(ValNode v) {
        System.out.println("    ValNode");
    }


    public PrettyPrint() {
    }
}