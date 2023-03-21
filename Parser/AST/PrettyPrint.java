package AST;

/*
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
        System.out.println(node.getClass().getSimpleName());
        indent();
        for (Node child : node.getChildren()) {
            printIndent();
            child.accept(this);
        }
        unindent();
        System.out.println();
    }
    public void visit(DclNode node) {

    }

    public void visit(StmtNode v) {

    }

    public void visit(ExprNode v) {

    }

    public void visit(ConditionTailNode v) {

    }

    public void visit(AssignNode node) {
        System.out.println(node.getClass().getSimpleName());
        indent();
        for (Node child : node.getChildren()) {
            printIndent();
            child.accept(this);
        }
        unindent();
    }

    public void visit(ValNode v) {

    }

    public void visit(IfStmtNode node) {
        System.out.println(node.getClass().getSimpleName());
        indent();
        for (Node child : node.getChildren()) {
            printIndent();
            child.accept(this);
        }
        unindent();
    }

    public void visit(ConditionNode node) {
        System.out.println(node.getClass().getSimpleName());
        indent();
        for (Node child : node.getChildren()) {
            printIndent();
            child.accept(this);
        }
        unindent();
    }

    public void visit(NOTNode node) {
        System.out.println(node.getClass().getSimpleName());
        indent();
        for (Node child : node.getChildren()) {
            printIndent();
            child.accept(this);

        }
        unindent();
    }

    public void visit(BlockNode node) {
        System.out.println(node.getClass().getSimpleName());
        indent();
        for (Node child : node.getChildren()) {
            printIndent();
            child.accept(this);
        }
        unindent();
    }

    public void visit(PrintNode node) {
        System.out.print(node.getClass().getSimpleName());
        System.out.print(" ");
        System.out.println(node.getToken());
    }

    public void visit(ANDNode node) {
        System.out.println(node.getClass().getSimpleName());
        indent();
        for (Node child : node.getChildren()) {
            printIndent();
            child.accept(this);
        }
        unindent();
    }

    public void visit(ORNode node) {
        System.out.println(node.getClass().getSimpleName());
        indent();
        for (Node child : node.getChildren()) {
            printIndent();
            child.accept(this);
        }
        unindent();
    }

    public void visit(EQNode node) {
        System.out.println(node.getClass().getSimpleName());
        indent();
        for (Node child : node.getChildren()) {
            printIndent();
            child.accept(this);
        }
        unindent();
    }

    public void visit(NENode node) {
        System.out.println(node.getClass().getSimpleName());
        indent();
        for (Node child : node.getChildren()) {
            printIndent();
            child.accept(this);
        }
        unindent();
    }

    public void visit(GTNode node) {
        System.out.println(node.getClass().getSimpleName());
        indent();
        for (Node child : node.getChildren()) {
            printIndent();
            child.accept(this);
        }
        unindent();
    }

    public void visit(LTNode node) {
        System.out.println(node.getClass().getSimpleName());
        indent();
        for (Node child : node.getChildren()) {
            printIndent();
            child.accept(this);
        }
        unindent();
    }

    public void visit(GENode node) {
        System.out.println(node.getClass().getSimpleName());
        indent();
        for (Node child : node.getChildren()) {
            printIndent();
            child.accept(this);
        }
        unindent();
    }

    public void visit(LENode node) {
        System.out.println(node.getClass().getSimpleName());
        indent();
        for (Node child : node.getChildren()) {
            printIndent();
            child.accept(this);
        }
        unindent();
    }

    public void visit(MinusNode node) {
        System.out.println(node.getClass().getSimpleName());
        indent();
        for (Node child : node.getChildren()) {
            printIndent();
            child.accept(this);
        }
        unindent();
    }

    public void visit(PlusNode node) {
        System.out.println(node.getClass().getSimpleName());
        indent();
        for (Node child : node.getChildren()) {
            printIndent();
            child.accept(this);
        }
        unindent();
    }

    public void visit(IntDclNode node) {
        System.out.print(node.getClass().getSimpleName());
        System.out.print(" ");
        System.out.println(node.getToken());
    }

    public void visit(FloatDclNode node) {
        System.out.print(node.getClass().getSimpleName());
        System.out.print(" ");
        System.out.println(node.getToken());
    }

    public void visit(BoolDclNode node) {
        System.out.print(node.getClass().getSimpleName());
        System.out.print(" ");
        System.out.println(node.getToken());
    }

    public void visit(IntNode node) {
        System.out.print(node.getClass().getSimpleName());
        System.out.print(" ");
        System.out.println(node.getToken());
    }

    public void visit(FloatNode node) {
        System.out.print(node.getClass().getSimpleName());
        System.out.print(" ");
        System.out.println(node.getToken());
    }

    public void visit(TrueNode node) {
        System.out.print(node.getClass().getSimpleName());
        System.out.print(" ");
        System.out.println(node.getToken());
    }
    public void visit(FalseNode node) {
        System.out.print(node.getClass().getSimpleName());
        System.out.print(" ");
        System.out.println(node.getToken());
    }
    public void visit(IdNode node) {
        System.out.print(node.getClass().getSimpleName());
        System.out.print(" ");
        System.out.println(node.getToken());
    }

    public void visit(IfElseStmtNode node) {
        System.out.println(node.getClass().getSimpleName());
        indent();
        for (Node child : node.getChildren()) {
            printIndent();
            child.accept(this);
        }
        unindent();
    }
}
 */