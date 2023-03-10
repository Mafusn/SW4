package AST;

public class PrettyPrint implements Visitor {
    private int indentLevel = 0;

    public PrettyPrint() {
    }

    private void indent() {
        indentLevel++;
    }

    private void unindent() {
        indentLevel--;
    }

    private void printIndent() {
        for (int i = 0; i < indentLevel; i++) {
            System.out.print("    ");
        }
    }

    public void visit(ProgNode node) {
        System.out.println("ProgNode");
        indent();
        for (Node child : node.getChildren()) {
            child.accept(this);
            System.out.println();
        }
        unindent();
    }
    public void visit(DclNode node) {
        for (Node child : node.getChildren()) {
            printIndent();
            System.out.print(child.getClass().getSimpleName());
            System.out.print(" ");
            child.accept(this);
            System.out.println();
        }
    }

    public void visit(TypeNode node) {
        indent();
        node.getChild().accept(this);
        unindent();
    }
    public void visit(StmtNode v) {

    }
    public void visit(ExprNode v) {

    }
    public void visit(PrintStmtNode v) {

    }
    public void visit(ValNode v) {

    }
    public void visit(IfStmtNode v) {

    }
    public void visit(ConditionNode v) {

    }
    public void visit(ConditionTailNode v) {

    }
    public void visit(ComparisonNode v) {

    }
    public void visit(ComparisonTailNode v) {

    }
    public void visit(TermNode v) {

    }
    public void visit(TermTailNode v) {

    }
    public void visit(FactorNode v) {

    }

    /* Terminal Nodes */

    public void visit(LParenNode v) {

    }
    public void visit(RParenNode v) {

    }
    public void visit(LBraceNode v) {

    }
    public void visit(RBraceNode v) {

    }
    public void visit(PrintNode v) {

    }
    public void visit(ANDNode v) {

    }
    public void visit(ORNode v) {

    }
    public void visit(EQNode v) {

    }
    public void visit(NENode v) {

    }
    public void visit(GTNode v) {

    }
    public void visit(LTNode v) {

    }
    public void visit(GENode v) {

    }
    public void visit(LENode v) {

    }
    public void visit(MinusNode v) {

    }
    public void visit(PlusNode v) {

    }
    public void visit(IfNode v) {

    }
    public void visit(ElseNode v) {

    }
    public void visit(IntDclNode node) {
        System.out.println();
        printIndent();
        System.out.print(node.getClass().getSimpleName());
        System.out.print(" ");
        System.out.print(node.getToken());
    }
    public void visit(FloatDclNode node) {
        System.out.print(node.getToken());
    }
    public void visit(BoolDclNode node) {
        System.out.print(node.getToken());
    }
    public void visit(IntNode node) {

    }
    public void visit(FloatNode v) {

    }
    public void visit(TrueNode v) {

    }
    public void visit(FalseNode v) {

    }
    public void visit(IdNode node) {
        System.out.print(node.getId());
    }
    public void visit(AssignNode v) {

    }
    public void visit(EOLNode v) {

    }
    public void visit(EOFNode v) {

    }
}