package AST;

public class PrettyPrint implements Visitor {
    @Override
    public void visit(Assigning node) {
        node.child1.accept(this);
        System.out.print(" = ");
        node.child2.accept(this);
        System.out.print(" ");
    }

    @Override
    public void visit(BinOperator node) {
        node.child1.accept(this);
        System.out.print(" " + node.operation + " ");
        node.child2.accept(this);
    }

    @Override
    public void visit(Block node) {
        System.out.print("block ");
        for(Node n : node.children){
            n.accept(this);
        }
        System.out.print("block-end ");
    }

    @Override
    public void visit(Bool node) {
        System.out.print(node.value);
    }

    @Override
    public void visit(BoolDcl node) {
        System.out.print("boolean " + node.id + " ");
    }

    @Override
    public void visit(Computing node) {
        node.child1.accept(this);
        System.out.print(" " + node.operation + " ");
        node.child2.accept(this);
    }

    @Override
    public void visit(FloatDcl node) {
        System.out.print("float " + node.id + " ");
    }

    @Override
    public void visit(FloatNum node) {
        System.out.print(node.value);
    }

    @Override
    public void visit(Id node) {
        System.out.print(node.id);
    }

    @Override
    public void visit(If node) {
        System.out.print("if ");
        node.condition.accept(this);
        System.out.print(" ");
        node.child1.accept(this);
    }

    @Override
    public void visit(IfElse node) {
        System.out.print("if ");
        node.condition.accept(this);
        System.out.print(" ");
        node.child1.accept(this);
        System.out.print("else ");
        node.child2.accept(this);
    }

    @Override
    public void visit(IntDcl node) {
        System.out.print("int " + node.id + " ");
    }

    @Override
    public void visit(IntNum node) {
        System.out.print(node.value);
    }

    @Override
    public void visit(Not node) {
        System.out.print("not ");
        node.child.accept(this);
        System.out.print(" ");
    }

    @Override
    public void visit(Print node) {
        System.out.print("print " + node.id + " ");
    }

    @Override
    public void visit(Prog node) {
        for(Node n : node.children){
            n.accept(this);
        }
        System.out.println();
    }
    /*
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

     */
}
